package ua.rd.pizza.domain;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Artem_Pryzhkov on 10-Oct-16.
 */
public class OrderTest {
    Order order;
    List<Pizza> pizzas=new ArrayList<>();
    List<Customer> customers=new ArrayList<>();
    @Before
    public void setUp(){
        pizzas.add(new Pizza(1L, "Hawaii", BigDecimal.valueOf(100.00),Pizza.Type.MEAT));
        pizzas.add(new Pizza(2L, "Falafel", BigDecimal.valueOf(57.70),Pizza.Type.VEGETARIAN));
        pizzas.add(new Pizza(3L, "Sea", BigDecimal.valueOf(120.10),Pizza.Type.SEA));

        customers.add(new Customer(0L, "Artem", "Pushkina st"));
        customers.add(new Customer(1L, "Daniil", "Nezalezhnosti av"));
        customers.add(new Customer(2L, "Olya", "Pochmelnyka blvd"));

        order=new Order(customers.get(0),new HashMap<>());
        order.setId(0L);
    }
    @Test
    public void addPizzasWithAmountIntoOrder(){
        initOrder();
        int amount=0;
        for(Pizza pizza:order.getPizzas().keySet()){
            amount+=order.getPizzas().get(pizza);
        }
        Assert.assertEquals(3,amount);
        Assert.assertTrue(order.getPizzas().containsKey(pizzas.get(0)));
    }

    @Test
    public void addTwoPizzasWithAmountIntoOrder(){
        initOrder();
        order.addPizza(pizzas.get(1),2);
        int amount=0;
        for(Pizza pizza:order.getPizzas().keySet()){
            amount+=order.getPizzas().get(pizza);
        }
        Assert.assertEquals(5,amount);
        Assert.assertTrue(order.getPizzas().containsKey(pizzas.get(1)));
    }

    @Test
    public void addUnavailableAmountOfPizzas_returnFalse(){
        Assert.assertEquals(false, order.addPizza(pizzas.get(0),0));
        order.addPizza(pizzas.get(0),7);
        Assert.assertEquals(false, order.addPizza(pizzas.get(1),4));
    }

    @Test
    public void countOrderPrice(){
        initOrder();
        Assert.assertEquals(new BigDecimal("300.00"),order.getOrderPrice());
    }

    @Test
    public void discountCase(){
        initOrder();
        order.addPizza(pizzas.get(2),1);
        order.addPizza(pizzas.get(1),1);
        Assert.assertEquals(new BigDecimal("441.77"),order.countOrderPrice());
    }

    @Test
    public void notEnoughPizzasForDiscount(){
        initOrder();
        order.addPizza(pizzas.get(2),1);
        Assert.assertEquals(new BigDecimal("420.1"),order.countOrderPrice());
    }

    @Test
    public void jumpIntoStatus(){
        initOrder();
        Assert.assertTrue(order.getStatus().jumpIntoStatus(Order.Status.DONE));
        Assert.assertTrue(order.getStatus().jumpIntoStatus(Order.Status.CANCELED));
        Assert.assertFalse(order.getStatus().jumpIntoStatus(Order.Status.NEW));
    }

    @Test
    public void checkSetStatus(){
        initOrder();
        order.setStatus(Order.Status.IN_PROGRESS);
        Assert.assertEquals(Order.Status.IN_PROGRESS,order.getStatus());
    }

    @Test
    public void jumpIntoStatusFromStatusThatHasNoWayToJump(){
        order.setStatus(Order.Status.CANCELED);
        Assert.assertFalse(order.getStatus().jumpIntoStatus(Order.Status.DONE));
    }

    @Test
    public void addOrderPriceToCustomerCard(){
        order.addPizza(pizzas.get(1),3);
        order.payForOrder();
        Assert.assertEquals(Order.Status.DONE,order.getStatus());
        Assert.assertEquals(new BigDecimal("173.10"),order.getCustomer().getAccumulativeCard());
        Order order2=new Order(customers.get(0),new HashMap<>());
        order2.addPizza(pizzas.get(0),2);
        order2.payForOrder();
        Assert.assertEquals(new BigDecimal("355.79"),customers.get(0).getAccumulativeCard());
    }


    @Test()
    public void AccCardValueHigherThanDiscountLevelPercentOfOrderPrice(){
        initOrder();
        initOrder();
        order.payForOrder();
        Assert.assertEquals(new BigDecimal("570.00"),order.getCustomer().getAccumulativeCard());
        Order order2=new Order(customers.get(0),new HashMap<>());
        order2.addPizza(pizzas.get(1),1);
        order2.payForOrder();
        Assert.assertEquals(new BigDecimal("40.39"),order2.countOrderPrice());
    }

    private void initOrder() {
        order.addPizza(pizzas.get(0),3);
    }


}