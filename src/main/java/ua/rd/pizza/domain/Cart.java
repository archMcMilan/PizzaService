package ua.rd.pizza.domain;

import ua.rd.pizza.domain.Customer;
import ua.rd.pizza.domain.Order;
import ua.rd.pizza.domain.discount.DiscountCounter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Map;

/**
 * Created by Artem_Pryzhkov on 27-Oct-16.
 */
public class Cart {
    @ElementCollection
    @CollectionTable
    @MapKeyClass(Pizza.class)
    @MapKeyColumn
    @Column(name="amount")
    private Map<Pizza,Integer> pizzas;

    private BigDecimal fullPrice;

    public boolean addPizza(Pizza pizza, int amount) {
        int pizzasInOrder=countPizzasAmount();
        if (pizzasInOrder + amount > 10 || pizzasInOrder + amount <= 0) {
            return false;
        }
        pizzas.merge(pizza,amount,Integer::sum);
        return true;
    }

    private int countPizzasAmount(){
        int pizzasInOrder=0;
        for(Pizza p:pizzas.keySet()){
            pizzasInOrder+=pizzas.get(p);
        }
        return pizzasInOrder;
    }

}
