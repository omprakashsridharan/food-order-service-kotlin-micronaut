package order.applicationservice.ports.input.message.listener.restaurantapproval

import order.applicationservice.dto.message.RestaurantApprovalResponse

interface RestaurantApprovalResponseMessageListener {
    fun orderApproved(restaurantApprovalResponse: RestaurantApprovalResponse)
    fun orderRejected(restaurantApprovalResponse: RestaurantApprovalResponse)
}