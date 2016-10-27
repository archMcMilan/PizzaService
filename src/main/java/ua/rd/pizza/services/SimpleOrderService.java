package ua.rd.pizza.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import ua.rd.pizza.domain.Customer;
import ua.rd.pizza.domain.Order;
import ua.rd.pizza.domain.Pizza;
import ua.rd.pizza.repository.OrderRepository;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Artem_Pryzhkov on 10/4/2016.
 */
public class SimpleOrderService implements OrderService/*, ApplicationContextAware*/ {

    OrderRepository orderRepository;
    PizzaService pizzaService;
//    private ApplicationContext applicationContext;

//    public SimpleOrderService(PizzaService pizzaService) {
//        orderRepository=null;
//        this.pizzaService=pizzaService;
//    }
    @Autowired
    public SimpleOrderService(OrderRepository orderRepository, PizzaService pizzaService) {
        this.orderRepository = orderRepository;
        this.pizzaService = pizzaService;
    }

    @Override
    public Order placeNewOrder(Customer customer, Integer... pizzasId) {
        Map<Pizza,Integer> pizzas = new HashMap<>();

        for(Integer id : pizzasId){
            pizzas.merge(pizzaService.getPizzaById(id),pizzas.get(pizzaService.getPizzaById(id)),Integer::sum);  // find Pizza from predifined in-memory list
        }
//        Order newOrder = new Order(customer, pizzas);
        Order newOrder=createNewOrder();
        newOrder.setCustomer(customer);
        newOrder.setPizzas(pizzas);

        orderRepository.saveOrder(newOrder);  // set Order Id and save Order to in-memory list
        return newOrder;
    }

    @Lookup
    protected Order createNewOrder() {
//        return (Order) applicationContext.getBean("order");
        //throw new IllegalStateException();
        return null;
    }

//    public void setContext(ApplicationContext context) {
//        this.applicationContext = context;
//    }

//    @Override
//    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
//        this.applicationContext = applicationContext;
//    }
}
