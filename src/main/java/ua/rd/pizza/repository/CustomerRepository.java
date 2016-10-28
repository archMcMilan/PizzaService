package ua.rd.pizza.repository;

import ua.rd.pizza.domain.customer.Customer;

/**
 * Created by Artem_Pryzhkov on 11-Oct-16.
 */
public interface CustomerRepository {
    Customer getById(long id);
}
