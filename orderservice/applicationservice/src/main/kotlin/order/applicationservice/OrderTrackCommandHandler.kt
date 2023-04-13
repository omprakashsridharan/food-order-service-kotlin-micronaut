package order.applicationservice

import common.exception.OrderNotFoundException
import jakarta.inject.Inject
import order.applicationservice.dto.track.TrackOrderQuery
import order.applicationservice.dto.track.TrackOrderResponse
import order.applicationservice.mapper.OrderDataMapper
import order.applicationservice.ports.output.repository.OrderRepository
import jakarta.inject.Singleton
import order.core.valueobject.TrackingId

@Singleton
class OrderTrackCommandHandler(
    @Inject
    val orderRepository: OrderRepository,
    @Inject
    val orderDataMapper: OrderDataMapper
) {
    fun trackOrder(trackOrderQuery: TrackOrderQuery): TrackOrderResponse {
        val optionalOrder = orderRepository.findByTrackingId(TrackingId(trackOrderQuery.orderTrackingId))
        if (optionalOrder.isEmpty) {
            throw OrderNotFoundException("Order not found", null)
        }
        return orderDataMapper.orderToTrackOrderResponse(optionalOrder.get())
    }
}