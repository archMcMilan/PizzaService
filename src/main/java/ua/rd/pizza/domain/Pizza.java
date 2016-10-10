package ua.rd.pizza.domain;

import java.math.BigDecimal;

/**
 * Created by Artem_Pryzhkov on 10/4/2016.
 */
public class Pizza {
    private Long id;
    private String name;
    private BigDecimal price;
    private Type type;

    public enum Type {
        VEGETARIAN,SEA,MEAT;
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

    public BigDecimal getPrice() {
        return price;
    }
}