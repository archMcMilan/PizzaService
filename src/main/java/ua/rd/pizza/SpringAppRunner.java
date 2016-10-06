package ua.rd.pizza;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ua.rd.pizza.domain.Order;
import ua.rd.pizza.repository.OrderRepository;
import ua.rd.pizza.repository.PizzaRepository;
import ua.rd.pizza.services.OrderService;

import java.util.Arrays;

/**
 * Created by Artem_Pryzhkov on 10/6/2016.
 */
public class SpringAppRunner {
    public static void main(String[] args) {
        ConfigurableApplicationContext context=new ClassPathXmlApplicationContext("appContext.xml");
        System.out.println(Arrays.toString(context.getBeanDefinitionNames()));
        PizzaRepository pizzaRepository= (PizzaRepository) context.getBean("pizzaRepository");
        System.out.println(pizzaRepository.get(1));
        //OrderRepository orderRepository= (OrderRepository) context.getBean("orderRepository");

        OrderService orderService= (OrderService) context.getBean("orderService");
        Order order=orderService.placeNewOrder(null,1,2,3);
        System.out.println(order);
        context.close();
    }
}
