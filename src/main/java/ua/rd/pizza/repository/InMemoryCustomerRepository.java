package ua.rd.pizza.repository;

import ua.rd.pizza.domain.Customer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Artem_Pryzhkov on 11-Oct-16.
 */
public class InMemoryCustomerRepository implements CustomerRepository {
    private List<Customer> customers = new ArrayList<>();

    public void init() {
        customers.add(new Customer(0L, "Artem", "Pushkina st"));
        customers.add(new Customer(1L, "Daniil", "Nezalezhnosti av"));
        customers.add(new Customer(2L, "Olya", "Pochmelnyka blvd"));
    }
}
