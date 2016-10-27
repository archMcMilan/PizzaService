package ua.rd.pizza.services;

import ua.rd.pizza.domain.Customer;
import ua.rd.pizza.domain.Order;

/**
 * Created by Artem_Pryzhkov on 10/4/2016.
 */
public interface OrderService {
    Order placeNewOrder(Customer customer, Integer ... pizzaId);
}
