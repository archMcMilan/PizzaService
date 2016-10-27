package ua.rd.pizza.domain.discount;

import ua.rd.pizza.domain.Cart;
import ua.rd.pizza.domain.Order;
import ua.rd.pizza.domain.Pizza;

import java.math.BigDecimal;

/**
 * Created by Artem_Pryzhkov on 27-Oct-16.
 */
public class MostExpensiveFrom4Discount implements Discount{


    @Override
    public BigDecimal discountValue(Cart cart) {
        Pizza mostExpensivePizza=null;
        for(Pizza pizza : cart.getOrder().getPizzas().keySet()){
            mostExpensivePizza=pizza;
            break;
        }
        for (Pizza pizza : cart.getOrder().getPizzas().keySet()) {
            if (mostExpensivePizza.getPrice().compareTo(pizza.getPrice()) < 0) {
                mostExpensivePizza = pizza;
            }
        }
        return mostExpensivePizza.getPrice().multiply(new BigDecimal(DISCOUNT_VALUE));
    }
}
