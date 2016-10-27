package ua.rd.pizza.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by Artem_Pryzhkov on 10/4/2016.
 */
@Entity
public class Customer implements Serializable {
    @Id
    @GeneratedValue
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Customer customer = (Customer) o;

        if (name != null ? !name.equals(customer.name) : customer.name != null) return false;
        if (address != null ? !address.equals(customer.address) : customer.address != null) return false;
        return accumulativeCard != null ? accumulativeCard.equals(customer.accumulativeCard) : customer.accumulativeCard == null;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (accumulativeCard != null ? accumulativeCard.hashCode() : 0);
        return result;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setAccumulativeCard(BigDecimal accumulativeCard) {
        this.accumulativeCard = accumulativeCard;
    }
}

