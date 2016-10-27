package ua.rd.pizza.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.rd.pizza.domain.Pizza;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by Artem_Pryzhkov on 25-Oct-16.
 */
@Repository("pizzaRepository")
public class JPAPizzaRepository implements PizzaRepository{
    @PersistenceContext
    private EntityManager em;


    @Override
    public Pizza find(Long id) {
        return em.find(Pizza.class,id);
    }

    @Override
    @Transactional
    public Pizza save(Pizza pizza) {
        Pizza newPizza=em.merge(pizza);
        return newPizza;
    }
}
