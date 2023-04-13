package order.applicationservice.ports.output.repository

import order.core.entity.Order
import order.core.valueobject.TrackingId
import java.util.*

interface OrderRepository {
    fun save(order: Order): Optional<Order>
    fun findByTrackingId(trackingId: TrackingId): Optional<Order>
}