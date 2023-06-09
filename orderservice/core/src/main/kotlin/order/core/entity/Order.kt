package order.core.entity

import common.entity.AggregateRoot
import common.exception.OrderDomainException
import common.valueobject.*
import order.core.valueobject.StreetAddress
import order.core.valueobject.TrackingId
import java.util.*

class Order(
    orderId: OrderId = OrderId(UUID.randomUUID()),
    val customerId: CustomerId,
    val restaurantId: RestaurantId,
    val streetAddress: StreetAddress,
    val price: Money,
    val items: List<OrderItem>,
    var trackingId: TrackingId = TrackingId(UUID.randomUUID()),
    var orderStatus: OrderStatus = OrderStatus.PENDING,
    var failureMessages: MutableList<String> = mutableListOf()
) :
    AggregateRoot<OrderId>(orderId) {
    fun validateOrder() {
        validateTotalPrice()
        validateItemsPrice()
    }

    fun pay() {
        if (orderStatus != OrderStatus.PENDING) {
            throw OrderDomainException("Order is not in correct state for pay operation", null)
        }
        orderStatus = OrderStatus.PAID
    }

    fun approve() {
        if (orderStatus != OrderStatus.PAID) {
            throw OrderDomainException("Order is not in correct state for approve operation", null)
        }
        orderStatus = OrderStatus.APPROVED
    }

    fun initCancel(failureMessages: List<String>) {
        if (orderStatus != OrderStatus.PAID) {
            throw OrderDomainException("Order is not in correct state for init cancel operation", null)
        }
        orderStatus = OrderStatus.CANCELLING
        updateFailureMessages(failureMessages)
    }

    fun cancel(failureMessages: List<String>) {
        if (!(orderStatus == OrderStatus.CANCELLING || orderStatus == OrderStatus.PENDING)) {
            throw OrderDomainException("Order is not in correct state for cancel operation", null)
        }
        orderStatus = OrderStatus.CANCELLED
        updateFailureMessages(failureMessages)
    }

    private fun updateFailureMessages(messages: List<String>) {
        failureMessages.addAll(messages)
    }

    private fun validateItemsPrice() {
        val orderItemsTotal = items.stream().map {
            it?.let {
                validateItemPrice(it)
                return@map it.subTotal
            }
            return@map Money.ZERO
        }.reduce(Money.ZERO, Money::add)

        if (price != orderItemsTotal) {
            throw OrderDomainException("Total does not match the individual items total", null)
        }
    }

    private fun validateItemPrice(orderItem: OrderItem) {
        if (!orderItem.isPriceValid()) {
            throw OrderDomainException("Order item price invalid", null)
        }
    }

    private fun validateTotalPrice() {
        if (!price.isGreaterThanZero()) {
            throw OrderDomainException("Total price must be greater than zero", null)
        }
    }


}