package food.order.service.kotlin.micronaut.common.domain.exception

open class DomainException(override val message: String, override val cause: Throwable?) : RuntimeException(message) {
}