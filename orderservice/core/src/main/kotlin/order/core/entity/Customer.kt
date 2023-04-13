package order.core.entity

import common.entity.AggregateRoot
import common.valueobject.CustomerId

class Customer(id: CustomerId) : AggregateRoot<CustomerId>(id) {
}