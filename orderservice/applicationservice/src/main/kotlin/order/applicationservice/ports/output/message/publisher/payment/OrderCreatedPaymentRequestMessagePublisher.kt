package order.applicationservice.ports.output.message.publisher.payment

import common.event.publisher.DomainEventPublisher
import order.core.entity.Order
import order.core.event.OrderCreatedEvent

interface OrderCreatedPaymentRequestMessagePublisher : DomainEventPublisher<OrderCreatedEvent, Order> {
}
