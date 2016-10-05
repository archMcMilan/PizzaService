package ua.rd.pizza.domain;

import java.util.List;

/**
 * Created by Artem_Pryzhkov on 10/4/2016.
 */
public class Order {
    private Long id;
    private List<Pizza> pizzas;
    private Customer customer;

    public Order(Customer customer,List<Pizza> pizzas) {
        this.pizzas = pizzas;
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", pizzas=" + pizzas +
                ", customer=" + customer +
                '}';
    }
}
