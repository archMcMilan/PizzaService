package ua.rd.pizza.domain.customer;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by Artem_Pryzhkov on 10/4/2016.
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(exclude={"id"})
@Entity
public class Customer implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private Address address;
    private LoyaltyCard loyaltyCard;

//    public Customer(Long id, String name, String address) {
//        this.id = id;
//        this.name = name;
//        this.address = address;
//        loyaltyCard=new BigDecimal("0.00");
//    }


    public BigDecimal getLoyaltyCard() {
        return loyaltyCard.getAccumulatedValue();
    }


    public boolean addPriceToCard(BigDecimal price){
        try{
            loyaltyCard.add(price);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}

