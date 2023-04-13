package order.applicationservice.ports.output.repository

import order.core.entity.Restaurant
import java.util.Optional

interface RestaurantRepository {
    fun findRestaurantInfo(restaurant: Restaurant): Optional<Restaurant>
}