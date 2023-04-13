package order.applicationservice.ports.input.service

import order.applicationservice.dto.create.CreateOrderCommand
import order.applicationservice.dto.create.CreateOrderResponse
import order.applicationservice.dto.track.TrackOrderQuery
import order.applicationservice.dto.track.TrackOrderResponse
import jakarta.validation.Valid

interface OrderApplicationService {
    fun createOrder(@Valid createOrderCommand: CreateOrderCommand): CreateOrderResponse
    fun trackOrder(@Valid trackOrderQuery: TrackOrderQuery): TrackOrderResponse
}