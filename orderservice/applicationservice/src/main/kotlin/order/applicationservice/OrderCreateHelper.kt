package order.applicationservice

import common.exception.OrderDomainException
import order.applicationservice.dto.create.CreateOrderCommand
import jakarta.inject.Inject
import jakarta.inject.Singleton
import order.applicationservice.mapper.OrderDataMapper
import order.applicationservice.ports.output.repository.CustomerRepository
import order.applicationservice.ports.output.repository.OrderRepository
import order.applicationservice.ports.output.repository.RestaurantRepository
import order.core.OrderDomainService
import order.core.entity.Order
import order.core.entity.Restaurant
import order.core.event.OrderCreatedEvent
import java.util.*

@Singleton
class OrderCreateHelper(
    @Inject
    val orderDomainService: OrderDomainService,
    @Inject
    val orderRepository: OrderRepository,
    @Inject
    val customerRepository: CustomerRepository,
    @Inject
    val restaurantRepository: RestaurantRepository,
    @Inject
    val orderDataMapper: OrderDataMapper
) {
    fun persistOrder(createOrderCommand: CreateOrderCommand): OrderCreatedEvent {
        checkCustomer(createOrderCommand.customerId)
        val restaurant = checkRestaurant(createOrderCommand);
        val order = orderDataMapper.createOrderCommandToOrder(createOrderCommand)
        val orderCreatedEvent = orderDomainService.validateAndInitiateOrder(order, restaurant)
        saveOrder(order)
        return orderCreatedEvent
    }

    private fun saveOrder(order: Order): Order {
        val orderResult = orderRepository.save(order)
        if (orderResult.isEmpty) {
            throw OrderDomainException("Could not save order", null);
        }
        return orderResult.get()
    }

    private fun checkRestaurant(createOrderCommand: CreateOrderCommand): Restaurant {
        val restaurant = orderDataMapper.createOrderCommandToRestaurant(createOrderCommand)
        val optionalRestaurant = restaurantRepository.findRestaurantInfo(restaurant)
        if (optionalRestaurant.isEmpty) {
            throw OrderDomainException(
                "Could not find restaurant with restaurant id ${createOrderCommand.restaurantId}",
                null
            )
        }
        return optionalRestaurant.get()
    }

    private fun checkCustomer(customerId: UUID) {
        val optionalCustomer = customerRepository.findCustomer(customerId)
        if (optionalCustomer.isEmpty) {
            throw OrderDomainException("Could not find customer with customer id $customerId", null)
        }
    }
}