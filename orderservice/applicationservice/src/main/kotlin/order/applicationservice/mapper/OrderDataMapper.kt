package order.applicationservice.mapper

import common.valueobject.CustomerId
import common.valueobject.Money
import order.applicationservice.dto.create.CreateOrderCommand
import order.applicationservice.dto.create.CreateOrderResponse
import order.applicationservice.dto.create.OrderAddress
import order.applicationservice.dto.create.OrderItem
import order.applicationservice.dto.track.TrackOrderResponse
import common.valueobject.ProductId
import common.valueobject.RestaurantId
import jakarta.inject.Singleton
import order.core.entity.Order
import order.core.entity.Product
import order.core.entity.Restaurant
import order.core.valueobject.OrderItemId
import order.core.valueobject.StreetAddress
import java.util.*
import java.util.stream.Collectors

@Singleton
class OrderDataMapper {
    fun createOrderCommandToRestaurant(createOrderCommand: CreateOrderCommand): Restaurant {
        val products =
            createOrderCommand.items.stream().map { Product(ProductId(it.productId)) }.collect(Collectors.toList())
        val restaurantId = RestaurantId(createOrderCommand.restaurantId)
        return Restaurant(id = restaurantId, products = products)
    }

    fun createOrderCommandToOrder(createOrderCommand: CreateOrderCommand): Order {
        val customerId = CustomerId(createOrderCommand.customerId)
        val restaurantId = RestaurantId(createOrderCommand.restaurantId)
        val deliveryAddress = orderAddressToStreetAddress(createOrderCommand.address)
        val price = Money(createOrderCommand.price)
        val items = orderItemsToOrderItemEntities(createOrderCommand.items)
        return Order(
            customerId = customerId,
            restaurantId = restaurantId,
            streetAddress = deliveryAddress,
            price = price,
            items = items
        )
    }

    private fun orderItemsToOrderItemEntities(items: List<OrderItem>): List<order.core.entity.OrderItem> {
        return items.mapIndexed { index, it ->
            val product = Product(ProductId(it.productId))
            val price = Money(it.price)
            val quantity = it.quantity
            val subTotal = Money(it.subTotal)
            // TODO: REFACTOR LATER
            val orderItemId = OrderItemId(index.toLong())
            return@mapIndexed order.core.entity.OrderItem (
                product = product,
                price = price,
                quantity = quantity,
                subTotal = subTotal,
                id = orderItemId
            )
        }
    }

    private fun orderAddressToStreetAddress(address: OrderAddress): StreetAddress {
        return StreetAddress(
            id = UUID.randomUUID(),
            street = address.street,
            postalCode = address.postalCode,
            city = address.city
        )
    }

    fun orderToCreateOrderResponse(order: Order, message: String): CreateOrderResponse {
        return CreateOrderResponse(orderTrackingId = order.trackingId.value, orderStatus = order.orderStatus, message = message)
    }

    fun orderToTrackOrderResponse(order: Order): TrackOrderResponse {
        return TrackOrderResponse(
            orderTrackingId = order.trackingId.value,
            orderStatus = order.orderStatus,
            failureMessages = order.failureMessages
        )
    }
}