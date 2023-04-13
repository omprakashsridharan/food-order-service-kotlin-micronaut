package order.core.entity

import common.entity.BaseEntity
import common.valueobject.Money
import common.valueobject.OrderId
import order.core.valueobject.OrderItemId

class OrderItem(
    val id: OrderItemId,
    val orderId: OrderId? = null,
    val product: Product,
    val quantity: Int,
    val price: Money,
    val subTotal: Money
) : BaseEntity<OrderItemId>(id) {
    fun isPriceValid(): Boolean {
        return price.isGreaterThanZero() && price == product.price && price.multiply(quantity) == subTotal
    }
}