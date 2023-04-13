package common.event.publisher

import common.event.DomainEvent

interface DomainEventPublisher<T, E> where T : DomainEvent<E> {
    fun publish(domainEvent: T)
}