package order.applicationservice.ports.output.repository

import order.core.entity.Customer
import java.util.*

interface CustomerRepository {
    fun findCustomer(customerId: UUID): Optional<Customer>
}