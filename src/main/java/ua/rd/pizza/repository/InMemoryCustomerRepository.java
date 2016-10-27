package ua.rd.pizza.repository;

import org.springframework.stereotype.Repository;
import ua.rd.pizza.domain.Customer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Artem_Pryzhkov on 11-Oct-16.
 */
@Repository
public class InMemoryCustomerRepository implements CustomerRepository {
    private Map<Integer,Customer> customers = new HashMap<>();

    public void init() {
//        customers.add(new Customer(0L, "Artem", "Pushkina st"));
//        customers.add(new Customer(1L, "Daniil", "Nezalezhnosti av"));
//        customers.add(new Customer(2L, "Olya", "Pochmelnyka blvd"));
    }

    @Override
    public Customer getById(long id) {
        return customers.get(id);
    }
}
