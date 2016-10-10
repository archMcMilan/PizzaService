package ua.rd.pizza;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ua.rd.pizza.domain.Order;
import ua.rd.pizza.repository.PizzaRepository;
import ua.rd.pizza.services.OrderService;
import ua.rd.pizza.services.SomeService;

import java.util.Arrays;

/**
 * Created by Artem_Pryzhkov on 10/6/2016.
 */
public class SpringAppRunner {
    public static void main(String[] args) {
        ConfigurableApplicationContext repoContext=new ClassPathXmlApplicationContext("repoContext.xml");
        System.out.println("0 context="+Arrays.toString(repoContext.getBeanDefinitionNames()));

        ConfigurableApplicationContext appContext=new ClassPathXmlApplicationContext(new String[]{"appContext.xml"},
                repoContext);
        System.out.println("1 context="+Arrays.toString(appContext.getBeanDefinitionNames()));
        System.out.println(repoContext.getBean("T1", SomeService.class).getString());
        System.out.println(appContext.getBean("T1", SomeService.class).getString());

//        PizzaRepository pizzaRepository= (PizzaRepository) repoContext.getBean("pizzaRepository");
//        System.out.println(pizzaRepository.get(1));
        //OrderRepository orderRepository= (OrderRepository) context.getBean("orderRepository");

//        OrderService orderService= (OrderService) appContext.getBean("orderService");
//        Order order=orderService.placeNewOrder(null,1,2,3);
//        System.out.println(order);
        repoContext.close();

        //System.out.println(appContext.getBean("T1", SomeService.class).getString());

        appContext.close();
    }
}
