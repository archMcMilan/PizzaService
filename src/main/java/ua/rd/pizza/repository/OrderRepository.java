package ua.rd.pizza.repository;

import ua.rd.pizza.domain.Order;
import ua.rd.pizza.domain.Pizza;

/**
 * Created by Artem_Pryzhkov on 10/4/2016.
 */
public interface OrderRepository {
    Order find(Long id);
    Order save(Order order);
}
