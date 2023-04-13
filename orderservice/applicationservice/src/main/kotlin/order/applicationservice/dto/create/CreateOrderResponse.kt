package order.applicationservice.dto.create

import common.valueobject.OrderStatus
import jakarta.validation.constraints.NotNull
import java.util.*

data class CreateOrderResponse(
    @NotNull val orderTrackingId: UUID,
    @NotNull val orderStatus: OrderStatus,
    @NotNull val message: String = ""
)