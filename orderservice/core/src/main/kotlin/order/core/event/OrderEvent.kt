package order.core.event

import common.event.DomainEvent
import order.core.entity.Order
import java.time.ZonedDateTime

abstract class OrderEvent(val order: Order, var createdAt: ZonedDateTime): DomainEvent<Order> {
}