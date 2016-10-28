package ua.rd.pizza.domain;

import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ua.rd.pizza.domain.customer.Customer;
import ua.rd.pizza.domain.discount.DiscountCounter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;

/**
 * Created by Artem_Pryzhkov on 10/4/2016.
 */
@Data
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
        cart=new Cart();
    }

    public BigDecimal countPriceWithDiscount(){
        return new DiscountCounter(this).discountValue();
    }

    public void payForOrder() {
        if(status.equals(Status.IN_PROGRESS)){
            setStatus(Status.DONE);
        }else {
            throw new RuntimeException();
        }
        customer.addPriceToCard(countPriceWithDiscount());
    }

}
