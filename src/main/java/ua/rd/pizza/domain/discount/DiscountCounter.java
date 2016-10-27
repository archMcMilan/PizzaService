package ua.rd.pizza.domain.discount;

import ua.rd.pizza.domain.Cart;

/**
 * Created by Artem_Pryzhkov on 27-Oct-16.
 */
public class DiscountCounter {
    private Cart cart;

    public DiscountCounter(Cart cart) {
        this.cart = cart;
    }

    public Discount selectBiggestDiscount(){
        AvailableDiscounts biggestDiscount=AvailableDiscounts.ACCUMULATIVE_CARD;
        for(AvailableDiscounts discounts:AvailableDiscounts.values()){
            if(discounts.getDiscount().discountValue(cart).
                    compareTo(biggestDiscount.getDiscount().discountValue(cart))>1){
                biggestDiscount=discounts;
            }
        }
        return biggestDiscount.getDiscount();
    }
}

enum AvailableDiscounts{
    ACCUMULATIVE_CARD(new AccumulativeCardDiscount()), MOST_EXPENSIVE_FROM_4(new MostExpensiveFrom4Discount());

    private Discount discount;

    AvailableDiscounts(Discount discount) {
        this.discount = discount;
    }

    public Discount getDiscount() {
        return discount;
    }
}