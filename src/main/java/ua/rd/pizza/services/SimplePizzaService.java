package ua.rd.pizza.services;

import ua.rd.pizza.annotation.Benchmark;
import ua.rd.pizza.domain.Pizza;
import ua.rd.pizza.infrastructure.InitialContext;
import ua.rd.pizza.repository.InMemoryPizzaRepository;
import ua.rd.pizza.repository.PizzaRepository;

/**
 * Created by Artem_Pryzhkov on 10/4/2016.
 */
public class SimplePizzaService implements PizzaService {

    private PizzaRepository pizzaRepository;

    public SimplePizzaService(PizzaRepository pizzaRepository) {
        InitialContext context=new InitialContext();
        //pizzaRepository=new InMemoryPizzaRepository();
        //pizzaRepository=context.getInstance("PizzaRepository");
        this.pizzaRepository=pizzaRepository;
    }

    @Override
    @Benchmark
    public Pizza getPizzaById(Integer id) {
        return pizzaRepository.get(id);
    }
}
