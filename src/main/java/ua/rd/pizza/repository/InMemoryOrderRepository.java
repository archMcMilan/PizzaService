package ua.rd.pizza.repository;

import org.springframework.stereotype.Repository;
import ua.rd.pizza.domain.Booking;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Artem_Pryzhkov on 10/4/2016.
 */
@Repository
public class InMemoryOrderRepository implements OrderRepository {
    private List<Booking> orders=new ArrayList<>();
    @Override
    public void saveOrder(Booking newOrder) {
        orders.add(newOrder);
    }
}
