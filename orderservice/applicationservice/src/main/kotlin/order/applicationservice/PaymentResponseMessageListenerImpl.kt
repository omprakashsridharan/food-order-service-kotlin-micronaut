package order.applicationservice

import io.micronaut.validation.Validated
import jakarta.inject.Singleton
import order.applicationservice.dto.message.PaymentResponse
import order.applicationservice.ports.input.message.listener.payment.PaymentResponseListener

@Validated
@Singleton
class PaymentResponseMessageListenerImpl : PaymentResponseListener {
    override fun paymentCompleted(paymentResponse: PaymentResponse) {
        TODO("Not yet implemented")
    }

    override fun paymentCancelled(paymentResponse: PaymentResponse) {
        TODO("Not yet implemented")
    }
}