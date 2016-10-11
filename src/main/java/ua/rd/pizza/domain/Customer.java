package ua.rd.pizza.domain;

import java.math.BigDecimal;

/**
 * Created by Artem_Pryzhkov on 10/4/2016.
 */
public class Customer {
    private Long id;
    private String name;
    private String address;
    private BigDecimal accumulativeCard;

    public Customer(Long id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public boolean addPriceToCard(BigDecimal price){
        try{
            accumulativeCard=accumulativeCard.add(price);
            return true;
        }catch (Exception e){
            return false;
        }

    }
}
