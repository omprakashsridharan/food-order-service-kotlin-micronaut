package food.order.service.kotlin.micronaut.common.domain.entity

abstract class AggregateRoot<ID>(id: ID) : BaseEntity<ID>(id)
