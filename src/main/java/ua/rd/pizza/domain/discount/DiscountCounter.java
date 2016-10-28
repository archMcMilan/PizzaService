package ua.rd.pizza.domain.discount;

import ua.rd.pizza.domain.Order;

import java.math.BigDecimal;

/**
 * Created by Artem_Pryzhkov on 27-Oct-16.
 */
public class DiscountCounter {
    private Order order;

    public DiscountCounter(Order order) {
        this.order = order;
    }

    public BigDecimal discountValue(){
        return selectBiggestDiscount().discountValue(order);
    }

    public Discount selectBiggestDiscount(){
        AvailableDiscounts biggestDiscount=AvailableDiscounts.LOYALTY_CARD;
        for(AvailableDiscounts discounts:AvailableDiscounts.values()){
            if(discounts.getDiscount().discountValue(order).
                    compareTo(biggestDiscount.getDiscount().discountValue(order))>0){
                biggestDiscount=discounts;
            }
        }
        return biggestDiscount.getDiscount();
    }
}

enum AvailableDiscounts{
    LOYALTY_CARD(new LoyaltyCardDiscount()), MOST_EXPENSIVE_FROM_4(new MostExpensiveFrom4Discount());

    private Discount discount;

    AvailableDiscounts(Discount discount) {
        this.discount = discount;
    }

    public Discount getDiscount() {
        return discount;
    }
}