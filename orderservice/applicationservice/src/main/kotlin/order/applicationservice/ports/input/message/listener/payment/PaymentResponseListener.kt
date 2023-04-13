package order.applicationservice.ports.input.message.listener.payment

import order.applicationservice.dto.message.PaymentResponse

interface PaymentResponseListener {
    fun paymentCompleted(paymentResponse: PaymentResponse)
    fun paymentCancelled(paymentResponse: PaymentResponse)
}