package ua.rd.pizza.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.rd.pizza.domain.Order;
import ua.rd.pizza.domain.Pizza;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by Artem_Pryzhkov on 28-Oct-16.
 */
@Repository("orderRepository")
public class JPAOrderRepository implements OrderRepository{
    @PersistenceContext
    private EntityManager em;


    @Override
    public Order find(Long id) {
        return em.find(Order.class,id);
    }

    @Override
    @Transactional
    public Order save(Order order) {
        Order newOrder=em.merge(order);
        return newOrder;
    }
}
