package ua.rd.pizza.repository;

import ua.rd.pizza.domain.Order;

/**
 * Created by Artem_Pryzhkov on 10/4/2016.
 */
public interface OrderRepository {
    void saveOrder(Order newOrder);
}
