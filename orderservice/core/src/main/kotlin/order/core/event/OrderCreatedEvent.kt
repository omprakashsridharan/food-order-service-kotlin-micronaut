package order.core.event

import order.core.entity.Order
import java.time.ZonedDateTime

class OrderCreatedEvent(order: Order, createdAt: ZonedDateTime) : OrderEvent(order, createdAt) {
}