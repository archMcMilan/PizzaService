package ua.rd.pizza.domain;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Artem_Pryzhkov on 10/4/2016.
 */
public class Order {
    private Long id;
    private List<Pizza> pizzas;
    private Customer customer;

    public static final String DISCOUNT_VALUE="0.3";
    public static final int PIZZAS_FOR_DISCOUNT=4;

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

    public BigDecimal getOrderPrice() {
        BigDecimal price=new BigDecimal("0.00");
        for(Pizza pizza:pizzas){
            price=price.add(pizza.getPrice());
        }
        return price;
    }

    public BigDecimal getDiscountPrice() {
        BigDecimal price=new BigDecimal("0.00");
        Pizza mostExpensivePizza=pizzas.get(0);
        for(Pizza pizza:pizzas){
            if(mostExpensivePizza.getPrice().compareTo(pizza.getPrice())<0){
                mostExpensivePizza=pizza;
            }
            price=price.add(pizza.getPrice());
        }
        price=price.subtract(mostExpensivePizza.getPrice().multiply(new BigDecimal(DISCOUNT_VALUE)));
        return price;
    }

    public BigDecimal countOrderPrice(){
        if(pizzas.size()>PIZZAS_FOR_DISCOUNT){
            return getDiscountPrice();
        }
        return getOrderPrice();
    }
}
