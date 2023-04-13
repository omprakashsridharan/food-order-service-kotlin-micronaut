package order.applicationservice.ports.output.message.publisher.restaurantapproval

import common.event.publisher.DomainEventPublisher
import order.core.entity.Order
import order.core.event.OrderPaidEvent

interface OrderPaidRestaurantRequestMessagePublisher : DomainEventPublisher<OrderPaidEvent, Order> {
}