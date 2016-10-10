package ua.rd.pizza.domain;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Artem_Pryzhkov on 10-Oct-16.
 */
public class OrderTest {
    Order order;
    List<Pizza> pizzas=new ArrayList<>();
    @Before
    public void setUp(){
        Customer customer=new Customer(1L,"Daniil");
        pizzas.add(new Pizza(1L, "Hawaii", BigDecimal.valueOf(98.00),Pizza.Type.MEAT));
        pizzas.add(new Pizza(2L, "Falafel", BigDecimal.valueOf(57.90),Pizza.Type.VEGETARIAN));
        pizzas.add(new Pizza(3L, "Sea", BigDecimal.valueOf(120.10),Pizza.Type.SEA));

        order=new Order(customer,new ArrayList<>());
        order.setId(0L);
    }
    @Test
    public void addPizzasWithAmountIntoOrder(){
        order.addPizza(pizzas.get(0),3);
        Assert.assertEquals(3,order.getPizzas().size());
        Assert.assertEquals(pizzas.get(0),order.getPizzas().get(0));
    }

    @Test
    public void addTwoPizzasWithAmountIntoOrder(){
        order.addPizza(pizzas.get(0),3);
        order.addPizza(pizzas.get(1),2);
        Assert.assertEquals(5,order.getPizzas().size());
        Assert.assertEquals(pizzas.get(1),order.getPizzas().get(4));
    }



}