package ua.rd.pizza.domain;

import java.util.List;

/**
 * Created by Artem_Pryzhkov on 10/4/2016.
 */
public class Order {
    private Long id;
    private List<Pizza> pizzas;
    private Customer customer;

    public Order(Customer customer,List<Pizza> pizzas) {
        this.pizzas = pizzas;
        this.customer = customer;
    }

    public boolean addPizza(Pizza pizza, int amount) {
        if(amount<=0 || amount>10){
            return false;
        }
        for(int i=0;i<amount;i++){
            if(!pizzas.add(pizza)){
               return false;
            }
        }
        return true;
    }



    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", pizzas=" + pizzas +
                ", customer=" + customer +
                '}';
    }

    public Long getId() {
        return id;
    }

    public List<Pizza> getPizzas() {
        return pizzas;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPizzas(List<Pizza> pizzas) {
        this.pizzas = pizzas;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
