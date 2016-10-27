package ua.rd.pizza.domain.discount;

import ua.rd.pizza.domain.Cart;
import ua.rd.pizza.domain.Order;

import java.math.BigDecimal;

/**
 * Created by Artem_Pryzhkov on 27-Oct-16.
 */
public class AccumulativeCardDiscount implements Discount{

    @Override
    public BigDecimal discountValue(Cart cart) {
        return cart.getCustomer().getAccumulativeCard().multiply(new BigDecimal(DISCOUNT_CARD_VALUE));
    }
}
