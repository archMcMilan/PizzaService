package ua.rd.pizza;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ua.rd.pizza.domain.Customer;
import ua.rd.pizza.domain.Order;
import ua.rd.pizza.repository.PizzaRepository;
import ua.rd.pizza.services.OrderService;
import ua.rd.pizza.services.SimpleOrderService;

import java.util.Arrays;

/**
 * Created by Artem_Pryzhkov on 10/6/2016.
 */
public class SpringAppRunner {
    public static void main(String[] args) {
        ConfigurableApplicationContext repoContext=new ClassPathXmlApplicationContext("repoContext.xml");
        ConfigurableApplicationContext appContext=new ClassPathXmlApplicationContext(new String[]{"appContext.xml"},
                repoContext);

        PizzaRepository pizzaRepository= repoContext.getBean(PizzaRepository.class);
        System.out.println(pizzaRepository.get(1));

        OrderService orderService= (OrderService) appContext.getBean("orderService");
        Order order=orderService.placeNewOrder(new Customer(1L,"Daniil","Pushkina st. 103"),1,2,3);
        System.out.println(order);

        System.out.println(orderService.getClass());
        repoContext.close();
        appContext.close();
    }
}
