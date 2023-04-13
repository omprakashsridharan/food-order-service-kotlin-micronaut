package order.applicationservice

import io.micronaut.validation.Validated
import jakarta.inject.Singleton
import order.applicationservice.dto.message.RestaurantApprovalResponse
import order.applicationservice.ports.input.message.listener.restaurantapproval.RestaurantApprovalResponseMessageListener

@Validated
@Singleton
class RestaurantApprovalResponseMessageListenerImpl : RestaurantApprovalResponseMessageListener {
    override fun orderApproved(restaurantApprovalResponse: RestaurantApprovalResponse) {
        TODO("Not yet implemented")
    }

    override fun orderRejected(restaurantApprovalResponse: RestaurantApprovalResponse) {
        TODO("Not yet implemented")
    }
}