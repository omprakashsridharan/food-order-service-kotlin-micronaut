package order.core.entity

import common.entity.AggregateRoot
import common.valueobject.RestaurantId

class Restaurant(val id: RestaurantId, val products: List<Product>, val active: Boolean = true) :
    AggregateRoot<RestaurantId>(id) {
}