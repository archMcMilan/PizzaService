package ua.rd.pizza.domain.discount;

import ua.rd.pizza.domain.Cart;
import ua.rd.pizza.domain.Order;

import java.math.BigDecimal;

/**
 * Created by Artem_Pryzhkov on 27-Oct-16.
 */
public interface Discount {
    String DISCOUNT_VALUE = "0.3";
    String DISCOUNT_CARD_VALUE ="0.1";
    int PIZZAS_FOR_DISCOUNT = 4;
    BigDecimal discountValue(Cart cart);
}
