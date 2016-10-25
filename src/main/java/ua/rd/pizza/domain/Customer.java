package ua.rd.pizza.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by Artem_Pryzhkov on 10/4/2016.
 */
@Entity
public class Customer implements Serializable {
    @Id
    private Long id;
    private String name;
    private String address;
    private BigDecimal accumulativeCard;

    public Customer(Long id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
        accumulativeCard=new BigDecimal("0.00");
    }

    public Customer(){

    }

    public BigDecimal getAccumulativeCard() {
        return accumulativeCard;
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
