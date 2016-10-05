package ua.rd.pizza.repository;

import ua.rd.pizza.annotation.PostCreate;
import ua.rd.pizza.domain.Pizza;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Artem_Pryzhkov on 10/4/2016.
 */
public class InMemoryPizzaRepository implements PizzaRepository {
    private Map<Integer,Pizza> pizzas=new HashMap<>();

    @PostCreate
    public void init() {
        pizzas.put(1,new Pizza(1L, "Hawaii", BigDecimal.valueOf(98.00),Pizza.Type.MEAT));
        pizzas.put(2,new Pizza(2L, "Falafel", BigDecimal.valueOf(57.90),Pizza.Type.VEGETARIAN));
        pizzas.put(3,new Pizza(3L, "Sea", BigDecimal.valueOf(120.10),Pizza.Type.SEA));
    }

    @Override
    public Pizza get(Integer id) {
        return pizzas.get(id);
    }
}
