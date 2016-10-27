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
@Table(name="Booking")
public class Order implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    private Cart cart;
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
        status = Status.NEW;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
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

    public Cart getCart() {
        return cart;
    }

    public Status getStatus() {
        return status;
    }

    public Customer getCustomer() {
        return customer;
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
        BigDecimal ceilingOfTheDiscountValue= getOrderPrice().multiply(new BigDecimal(DISCOUNT_VALUE));
        BigDecimal accCardDiscountValue=customer.getAccumulativeCard().multiply(new BigDecimal(DISCOUNT_CARD_VALUE));
        if(accCardDiscountCondition(ceilingOfTheDiscountValue, accCardDiscountValue)){
            return getOrderPrice().subtract(ceilingOfTheDiscountValue);
        }else{
            return  getOrderPrice().subtract(accCardDiscountValue);
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
