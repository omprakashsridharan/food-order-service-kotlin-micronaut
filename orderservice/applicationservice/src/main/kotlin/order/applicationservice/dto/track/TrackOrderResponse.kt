package order.applicationservice.dto.track

import common.valueobject.OrderStatus
import jakarta.validation.constraints.NotNull
import java.util.*

data class TrackOrderResponse(
    @NotNull val orderTrackingId: UUID,
    @NotNull val orderStatus: OrderStatus,
    val failureMessages: List<String>
) {
}