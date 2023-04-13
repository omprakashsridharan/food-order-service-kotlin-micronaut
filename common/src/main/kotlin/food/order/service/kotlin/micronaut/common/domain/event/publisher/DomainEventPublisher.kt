package food.order.service.kotlin.micronaut.common.domain.event.publisher

import food.order.service.kotlin.micronaut.common.domain.event.DomainEvent

interface DomainEventPublisher<T, E> where T : DomainEvent<E> {
    fun publish(domainEvent: T)
}