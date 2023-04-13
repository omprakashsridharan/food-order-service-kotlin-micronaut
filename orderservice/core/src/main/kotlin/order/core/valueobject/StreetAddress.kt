package order.core.valueobject

import java.util.*

data class StreetAddress(val id: UUID, val street: String, val postalCode: String, val city: String) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is StreetAddress) return false

        if (street != other.street) return false
        if (postalCode != other.postalCode) return false
        return city == other.city
    }

    override fun hashCode(): Int {
        return Objects.hash(street, postalCode, city)
    }
}