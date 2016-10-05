package ua.rd.pizza.infrastructure;

import ua.rd.pizza.repository.InMemoryOrderRepository;
import ua.rd.pizza.repository.InMemoryPizzaRepository;
import ua.rd.pizza.services.SimpleOrderService;
import ua.rd.pizza.services.SimplePizzaService;

import javax.naming.NamingEnumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Artem_Pryzhkov on 10/4/2016.
 */
public class JavaConfig implements Config {

    private Map<String,Class<?>> classes=new HashMap<>();
    {
        classes.put("pizzaRepository", InMemoryPizzaRepository.class);
        classes.put("orderRepository", InMemoryOrderRepository.class);
        classes.put("orderService", SimpleOrderService.class);
        classes.put("pizzaService", SimplePizzaService.class);
    }

    @Override
    public Class<?> getImpl(String name) {
        return classes.get(name);
    }
}
