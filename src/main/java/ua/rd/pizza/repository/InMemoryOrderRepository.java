package ua.rd.pizza.repository;

import ua.rd.pizza.domain.Order;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Artem_Pryzhkov on 10/4/2016.
 */
public class InMemoryOrderRepository implements OrderRepository {
    private List<Order> orders=new ArrayList<>();
    @Override
    public void saveOrder(Order newOrder) {
        orders.add(newOrder);
    }
}
