package food.order.service.kotlin.micronaut.common.domain.exception


class OrderNotFoundException(message: String, cause: Throwable?) : DomainException(message, cause) {
}