package ua.rd.pizza;

import ua.rd.pizza.domain.customer.Customer;
import ua.rd.pizza.domain.Order;
import ua.rd.pizza.infrastructure.ApplicationContext;
import ua.rd.pizza.infrastructure.Context;
import ua.rd.pizza.infrastructure.JavaConfig;
import ua.rd.pizza.repository.PizzaRepository;
import ua.rd.pizza.services.OrderService;

/**
 * Created by Artem_Pryzhkov on 10/4/2016.
 */
public class AppRunner {
    public static void main(String[] args) {
//        Customer customer = null;
//        Order order;
//
//        SimpleOrderService orderService = new SimpleOrderService();
//        order = orderService.placeNewOrder(customer, 0, 1, 2);
//
//        System.out.println(order);

        //PizzaService pizzaService=new SimplePizzaService();
        //System.out.println(pizzaService.getPizzaById(1));

        Context context=new ApplicationContext(new JavaConfig());
        PizzaRepository pizzaRepository=context.getBean("pizzaRepository");
        //System.out.println(pizzaRepository.find(1));

        Customer customer = null;
        Order order;
        OrderService orderService =context.getBean("orderService");
        order = orderService.placeNewOrder(customer, 0, 1, 2);
        System.out.println(order);
    }
}
