package main.java.ru.clevertec.check;

public interface Discount {
    int getDiscountRate();
    int getDiscountAmount(int totalAmount);
}
