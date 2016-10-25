package ua.rd.pizza.domain;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;

/**
 * Created by Artem_Pryzhkov on 10/4/2016.
 */
@Component
@Scope("prototype")
@Entity
public class Order implements Serializable {
    @Id
    private Long id;

    @ElementCollection
    @CollectionTable
    @MapKeyClass(Pizza.class)
    @MapKeyColumn
    @Column(name="amount")
    private Map<Pizza,Integer> pizzas;

    @ManyToOne
    private Customer customer;
    private Status status;

    public static final String DISCOUNT_VALUE = "0.3";
    public static final String DISCOUNT_CARD_VALUE ="0.1";
    public static final int PIZZAS_FOR_DISCOUNT = 4;

    public enum Status {
        NEW("IN_PROGRESS", "CANCELED"),
        IN_PROGRESS("CANCELED", "DONE"),
        CANCELED(),
        DONE();

        List<String> availableStatusesToJumpIn=new ArrayList<>();

        Status(String... statusNames) {
            availableStatusesToJumpIn = Arrays.asList(statusNames);
        }

        public boolean jumpIntoStatus(Status status) {
            if (this.availableStatusesToJumpIn.contains(status.name())) {
                return true;
            }
            return false;
        }
    }

    public Order() {
    }

    public Order(Customer customer, Map<Pizza,Integer> pizzas) {
        this.pizzas = pizzas;
        if (customer != null) {
            this.customer = customer;
        } else {
            throw new RuntimeException();
        }

        status = Status.NEW;
    }


    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", pizzas=" + pizzas +
                ", customer=" + customer +
                '}';
    }


    public Map<Pizza,Integer> getPizzas() {
        return pizzas;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setStatus(Status status) {
        if (this.status.jumpIntoStatus(status)) {
            this.status = status;
        }
    }

    public void setPizzas(Map<Pizza,Integer> pizzas) {
        this.pizzas = pizzas;
    }

    public Status getStatus() {
        return status;
    }

    public Customer getCustomer() {
        return customer;
    }

    public boolean addPizza(Pizza pizza, int amount) {
        int pizzasInOrder=countPizzasAmount();
        if (pizzasInOrder + amount > 10 || pizzasInOrder + amount <= 0) {
            return false;
        }
        pizzas.merge(pizza,amount,Integer::sum);
        status=Status.IN_PROGRESS;
        return true;
    }

    private int countPizzasAmount(){
        int pizzasInOrder=0;
        for(Pizza p:pizzas.keySet()){
            pizzasInOrder+=pizzas.get(p);
        }
        return pizzasInOrder;
    }


    public BigDecimal getOrderFullPrice() {
        BigDecimal price = new BigDecimal("0.00");
        for(Pizza pizza:pizzas.keySet()){
            price= price.add(pizza.getPrice().multiply(new BigDecimal(pizzas.get(pizza))));
        }
        return price;
    }

    /**
     * Method find the most expensive pizza in order and subtract DISCOUNT_VALUE of it from total price
     * @return new price after subtract
     */
    public BigDecimal getPriceWithDiscountForTheMostExpensivePizza() {
        BigDecimal price = new BigDecimal("0.00");
        Pizza mostExpensivePizza=null;
        for(Pizza pizza : pizzas.keySet()){
            mostExpensivePizza=pizza;
            break;
        }
        for (Pizza pizza : pizzas.keySet()) {
            if (mostExpensivePizza.getPrice().compareTo(pizza.getPrice()) < 0) {
                mostExpensivePizza = pizza;
            }
            price= price.add(pizza.getPrice().multiply(new BigDecimal(pizzas.get(pizza))));
        }
        price = price.subtract(mostExpensivePizza.getPrice().multiply(new BigDecimal(DISCOUNT_VALUE)));
        return price;
    }

    /**
     * Method count order's price considering discount by accumulative card.
     * ceilingOfTheDiscountValue is DISCOUNT_VALUE of the order price
     * accCardDiscountValue is DISCOUNT_CARD_VALUE percent of the accumulative card value
     * @return
     */
    public BigDecimal getPriceWithCardDiscount(){
        BigDecimal ceilingOfTheDiscountValue= getOrderFullPrice().multiply(new BigDecimal(DISCOUNT_VALUE));
        BigDecimal accCardDiscountValue=customer.getAccumulativeCard().multiply(new BigDecimal(DISCOUNT_CARD_VALUE));
        if(accCardDiscountCondition(ceilingOfTheDiscountValue, accCardDiscountValue)){
            return getOrderFullPrice().subtract(ceilingOfTheDiscountValue);
        }else{
            return  getOrderFullPrice().subtract(accCardDiscountValue);
        }
    }

    private boolean accCardDiscountCondition(BigDecimal ceilingOfTheDiscountValue, BigDecimal accCardDiscountValue) {
        return accCardDiscountValue.compareTo(ceilingOfTheDiscountValue)>0;
    }


    public BigDecimal countOrderPrice() {
        BigDecimal price=getPriceWithCardDiscount();
        if (discountForMostExpensiveCondition()) {
            if(price.compareTo(getPriceWithDiscountForTheMostExpensivePizza())>0){
                return getPriceWithDiscountForTheMostExpensivePizza().stripTrailingZeros();
            }
        }
        return price.stripTrailingZeros();
    }

    private boolean discountForMostExpensiveCondition() {
        return countPizzasAmount() > PIZZAS_FOR_DISCOUNT;
    }


    public void payForOrder() {
        if(status.equals(Status.IN_PROGRESS)){
            setStatus(Status.DONE);
        }else {
            throw new RuntimeException();
        }
        customer.addPriceToCard(countOrderPrice());
    }
}
