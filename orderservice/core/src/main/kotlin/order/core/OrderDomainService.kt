package order.core

import order.core.entity.Order
import order.core.entity.Restaurant
import order.core.event.OrderCancelledEvent
import order.core.event.OrderCreatedEvent
import order.core.event.OrderPaidEvent

interface OrderDomainService {
    fun validateAndInitiateOrder(order: Order, restaurant: Restaurant): OrderCreatedEvent
    fun payOrder(order: Order): OrderPaidEvent
    fun approveOrder(order: Order)
    fun cancelOrderPayment(order: Order, failureMessages: List<String>): OrderCancelledEvent
    fun cancelOrder(order: Order, failureMessages: List<String>)
}