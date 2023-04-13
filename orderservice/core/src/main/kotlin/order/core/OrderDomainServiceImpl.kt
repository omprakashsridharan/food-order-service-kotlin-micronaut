package order.core

import common.exception.OrderDomainException
import order.core.entity.Order
import order.core.entity.Restaurant
import order.core.event.OrderCancelledEvent
import order.core.event.OrderCreatedEvent
import order.core.event.OrderPaidEvent
import java.time.ZoneId
import java.time.ZonedDateTime

class OrderDomainServiceImpl : OrderDomainService {
    override fun validateAndInitiateOrder(order: Order, restaurant: Restaurant): OrderCreatedEvent {
        validateRestaurant(restaurant)
        setOrderProductInformation(order, restaurant)
        order.validateOrder()
        return OrderCreatedEvent(order, ZonedDateTime.now(ZoneId.of("UTC")))
    }

    private fun setOrderProductInformation(order: Order, restaurant: Restaurant) {
        order.items.forEach { oi ->
            val currentProduct = oi.product
            restaurant.products.forEach { rp ->
                if (currentProduct == rp) {
                    currentProduct.updateWithConfirmedNameAndPrice(rp.name, rp.price)
                }
            }
        }
    }

    private fun validateRestaurant(restaurant: Restaurant) {
        if (!restaurant.active) {
            throw OrderDomainException("Inactive restaurant", null)
        }
    }

    override fun payOrder(order: Order): OrderPaidEvent {
        order.pay()
        return OrderPaidEvent(order, ZonedDateTime.now(ZoneId.of("UTC")))
    }

    override fun approveOrder(order: Order) {
        order.approve()
    }

    override fun cancelOrderPayment(order: Order, failureMessages: List<String>): OrderCancelledEvent {
        order.initCancel(failureMessages)
        return OrderCancelledEvent(order, ZonedDateTime.now(ZoneId.of("UTC")))
    }

    override fun cancelOrder(order: Order, failureMessages: List<String>) {
        order.cancel(failureMessages)
    }
}