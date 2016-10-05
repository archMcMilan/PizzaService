package ua.rd.pizza.domain;

/**
 * Created by Artem_Pryzhkov on 10/4/2016.
 */
public class Customer {
    private Long id;
    private String name;

    public Customer(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
