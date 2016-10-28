package ua.rd.pizza.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import ua.rd.pizza.domain.customer.Customer;
import ua.rd.pizza.domain.Order;
import ua.rd.pizza.domain.Pizza;
import ua.rd.pizza.repository.OrderRepository;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Artem_Pryzhkov on 10/4/2016.
 */
public class SimpleOrderService implements OrderService {

    OrderRepository orderRepository;
    PizzaService pizzaService;

    @Autowired
    public SimpleOrderService(OrderRepository orderRepository, PizzaService pizzaService) {
        this.orderRepository = orderRepository;
        this.pizzaService = pizzaService;
    }

    @Override
    public Order placeNewOrder(Customer customer, Integer... pizzasId) {
        Order newOrder = createNewOrder();
        newOrder.setCustomer(customer);
        for (Integer id : pizzasId) {
            newOrder.getCart().addPizza(pizzaService.getPizzaById(id),1);
        }
        saveOrder(newOrder);
        return newOrder;
    }

    private void saveOrder(Order newOrder) {
        orderRepository.save(newOrder);
    }

    @Lookup
    protected Order createNewOrder() {
        return null;
    }
}
