package common.exception


class OrderNotFoundException(message: String, cause: Throwable?) : DomainException(message, cause) {
}