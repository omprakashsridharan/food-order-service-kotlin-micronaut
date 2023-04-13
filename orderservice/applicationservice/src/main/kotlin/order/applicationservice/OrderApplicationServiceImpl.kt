package order.applicationservice

import io.micronaut.validation.Validated
import jakarta.inject.Inject
import jakarta.inject.Singleton
import order.applicationservice.dto.create.CreateOrderCommand
import order.applicationservice.dto.create.CreateOrderResponse
import order.applicationservice.dto.track.TrackOrderQuery
import order.applicationservice.dto.track.TrackOrderResponse
import order.applicationservice.ports.input.service.OrderApplicationService

@Validated
@Singleton
private class OrderApplicationServiceImpl(
    @Inject val orderCreateCommandHandler: OrderCreateCommandHandler,
    @Inject val orderTrackCommandHandler: OrderTrackCommandHandler
) : OrderApplicationService {
    override fun createOrder(createOrderCommand: CreateOrderCommand): CreateOrderResponse =
        orderCreateCommandHandler.createOrder(createOrderCommand)

    override fun trackOrder(trackOrderQuery: TrackOrderQuery): TrackOrderResponse =
        orderTrackCommandHandler.trackOrder(trackOrderQuery)
}