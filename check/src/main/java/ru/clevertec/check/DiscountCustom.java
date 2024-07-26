package main.java.ru.clevertec.check;

public class DiscountCustom implements Discount {
    private int discountRate;

    public DiscountCustom(int discountRate) {
        this.discountRate = discountRate;
    }


    @Override
    public int getDiscountRate() {
        return this.discountRate;
    }

    @Override
    public int getDiscountAmount(int totalAmount) {
        double rateDouble = (double) discountRate;
        return (int)Math.round(totalAmount * rateDouble / 100);
    }
}
