package ua.rd.pizza.repository;

import ua.rd.pizza.domain.Pizza;

/**
 * Created by Artem_Pryzhkov on 10/4/2016.
 */
public interface PizzaRepository {
    Pizza find(Long id);
    Pizza save(Pizza pizza);
}
