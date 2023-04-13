package order.core.entity

import common.entity.BaseEntity
import common.valueobject.Money
import common.valueobject.ProductId

class Product(id: ProductId, var name: String = "", var price: Money = Money.ZERO) : BaseEntity<ProductId>(id) {
    fun updateWithConfirmedNameAndPrice(name: String, price: Money) {
        this.name = name
        this.price = price
    }
}