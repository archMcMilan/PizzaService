package ua.rd.pizza.domain.discount;

import ua.rd.pizza.domain.Order;
import ua.rd.pizza.domain.Pizza;

import java.math.BigDecimal;

/**
 * Created by Artem_Pryzhkov on 27-Oct-16.
 */
public class MostExpensiveFrom4Discount implements Discount{

    private boolean condition(Order order){
        if(order.getCart().countPizzasAmount()>=PIZZAS_FOR_DISCOUNT){
            return true;
        }
        return false;
    }

    @Override
    public BigDecimal discountValue(Order order) {
        if(condition(order)){
            Pizza mostExpensivePizza=null;
            for(Pizza pizza : order.getCart().getPizzas().keySet()){
                mostExpensivePizza=pizza;
                break;
            }
            for (Pizza pizza : order.getCart().getPizzas().keySet()) {
                if (mostExpensivePizza.getPrice().compareTo(pizza.getPrice()) < 0) {
                    mostExpensivePizza = pizza;
                }
            }
            return mostExpensivePizza.getPrice().multiply(new BigDecimal(DISCOUNT_VALUE_FOR_MOST_EXPENSIVE_PIZZA));
        }
        return null;
    }
}
