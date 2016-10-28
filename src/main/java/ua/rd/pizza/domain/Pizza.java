package ua.rd.pizza.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by Artem_Pryzhkov on 10/4/2016.
 */
@Data
@EqualsAndHashCode(exclude = "id")
@Entity
public class Pizza implements Serializable{
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private BigDecimal price;
    private Type type;

    public enum Type {
        VEGETARIAN,SEA,MEAT;
    }

    public Pizza() {
    }

    public Pizza(Long id, String name, BigDecimal price, Type type) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.type = type;
    }

    @Override
    public String toString() {
        return "Pizza{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", type=" + type +
                '}';
    }
}