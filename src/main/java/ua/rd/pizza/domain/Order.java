package ua.rd.pizza.domain;

import com.sun.org.apache.xpath.internal.operations.Or;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Artem_Pryzhkov on 10/4/2016.
 */
@Component
@Scope("prototype")
public class Order {
    private Long id;
    private List<Pizza> pizzas;
    private Customer customer;
    private Status status;

    public static final String DISCOUNT_VALUE = "0.3";
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

    public Order(Customer customer, List<Pizza> pizzas) {
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


    public List<Pizza> getPizzas() {
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

    public void setPizzas(List<Pizza> pizzas) {
        this.pizzas = pizzas;
    }

    public Status getStatus() {
        return status;
    }

    public Customer getCustomer() {
        return customer;
    }

    public boolean addPizza(Pizza pizza, int amount) {
        if (pizzas.size() + amount > 10 || pizzas.size() + amount <= 0) {
            return false;
        }
        for (int i = 0; i < amount; i++) {
            if (!pizzas.add(pizza)) {
                return false;
            }
        }
        status=Status.IN_PROGRESS;
        return true;
    }


    public BigDecimal getOrderPrice() {
        BigDecimal price = new BigDecimal("0.00");
        for (Pizza pizza : pizzas) {
            price = price.add(pizza.getPrice());
        }
        return price;
    }

    public BigDecimal getDiscountPrice() {
        BigDecimal price = new BigDecimal("0.00");
        Pizza mostExpensivePizza = pizzas.get(0);
        for (Pizza pizza : pizzas) {
            if (mostExpensivePizza.getPrice().compareTo(pizza.getPrice()) < 0) {
                mostExpensivePizza = pizza;
            }
            price = price.add(pizza.getPrice());
        }
        price = price.subtract(mostExpensivePizza.getPrice().multiply(new BigDecimal(DISCOUNT_VALUE)));
        return price;
    }

    public BigDecimal countOrderPrice() {
        if (pizzas.size() > PIZZAS_FOR_DISCOUNT) {
            return getDiscountPrice();
        }
        return getOrderPrice();
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
