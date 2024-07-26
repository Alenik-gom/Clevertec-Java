package main.java.ru.clevertec.check;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class DiscountWholeSale implements Discount {
    private final int rate = 10;

    @Override
    public int getDiscountRate() {
        return rate;
    }

    @Override
    public int getDiscountAmount(int totalAmount) {
        double rateDouble = (double) rate;
        return (int)Math.round(totalAmount * rateDouble / 100);
    }
}
