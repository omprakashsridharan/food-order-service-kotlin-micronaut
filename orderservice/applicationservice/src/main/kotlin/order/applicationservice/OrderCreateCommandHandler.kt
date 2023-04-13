package order.applicationservice

import jakarta.inject.Inject
import jakarta.inject.Singleton
import order.applicationservice.dto.create.CreateOrderCommand
import order.applicationservice.dto.create.CreateOrderResponse
import order.applicationservice.mapper.OrderDataMapper
import order.applicationservice.ports.output.message.publisher.payment.OrderCreatedPaymentRequestMessagePublisher

@Singleton
class OrderCreateCommandHandler(
    @Inject
    val orderCreateHelper: OrderCreateHelper,
    @Inject
    val orderDataMapper: OrderDataMapper,
    @Inject
    val orderCreatedPaymentRequestMessagePublisher: OrderCreatedPaymentRequestMessagePublisher
) {

    fun createOrder(createOrderCommand: CreateOrderCommand): CreateOrderResponse {
        val orderCreatedEvent = orderCreateHelper.persistOrder(createOrderCommand)
        orderCreatedPaymentRequestMessagePublisher.publish(orderCreatedEvent)
        return orderDataMapper.orderToCreateOrderResponse(orderCreatedEvent.order, "order created successfully")
    }

}