package ua.rd.pizza.domain.discount;

import ua.rd.pizza.domain.Order;

import java.math.BigDecimal;

/**
 * Created by Artem_Pryzhkov on 27-Oct-16.
 */
public class LoyaltyCardDiscount implements Discount{

    @Override
    public BigDecimal discountValue(Order order) {
        BigDecimal maxDiscountByAccCard=order.getCart().getFullPrice().multiply(new BigDecimal(MAX_DISCOUNT_VALUE));
        BigDecimal availableDiscountByAccCard=
                order.getCustomer().getLoyaltyCard().multiply(new BigDecimal(DISCOUNT_CARD_VALUE));
        if(maxDiscountByAccCard.compareTo(availableDiscountByAccCard)>0){
            return maxDiscountByAccCard;
        }
        return availableDiscountByAccCard;

    }
}
