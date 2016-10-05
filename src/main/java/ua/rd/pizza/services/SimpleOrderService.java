package ua.rd.pizza.services;

import ua.rd.pizza.domain.Customer;
import ua.rd.pizza.domain.Order;
import ua.rd.pizza.domain.Pizza;
import ua.rd.pizza.repository.InMemoryOrderRepository;
import ua.rd.pizza.repository.OrderRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Artem_Pryzhkov on 10/4/2016.
 */
public class SimpleOrderService implements OrderService {

    OrderRepository orderRepository;
    PizzaService pizzaService;

//    public SimpleOrderService() {
////        orderRepository=new InMemoryOrderRepository();
////        pizzaService=new SimplePizzaService();
//    }

    public SimpleOrderService(OrderRepository orderRepository, PizzaService pizzaService) {
        this.orderRepository = orderRepository;
        this.pizzaService = pizzaService;
    }

    @Override
    public Order placeNewOrder(Customer customer, Integer... pizzasId) {
        List<Pizza> pizzas = new ArrayList<>();

        for(Integer id : pizzasId){
            pizzas.add(pizzaService.getPizzaById(id));  // get Pizza from predifined in-memory list
        }
        Order newOrder = new Order(customer, pizzas);

        orderRepository.saveOrder(newOrder);  // set Order Id and save Order to in-memory list
        return newOrder;
    }


}
