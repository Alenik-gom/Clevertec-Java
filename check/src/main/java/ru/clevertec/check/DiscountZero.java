package main.java.ru.clevertec.check;

public class DiscountZero implements Discount  {
    private final int rate = 0;

    @Override
    public int getDiscountRate() {
        return rate;
    }

    @Override
    public int getDiscountAmount(int totalAmount) {
        return 0;
    }
}
