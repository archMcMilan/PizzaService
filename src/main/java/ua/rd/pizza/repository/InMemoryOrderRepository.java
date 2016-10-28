package ua.rd.pizza.repository;

import org.springframework.stereotype.Repository;
import ua.rd.pizza.domain.Order;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Artem_Pryzhkov on 10/4/2016.
 */
@Repository
public class InMemoryOrderRepository  {
    private List<Order> orders=new ArrayList<>();

    public void saveOrder(Order newOrder) {
        orders.add(newOrder);
    }
}
