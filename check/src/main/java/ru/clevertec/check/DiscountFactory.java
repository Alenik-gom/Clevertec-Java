package main.java.ru.clevertec.check;

public class DiscountFactory {
    private static int minQuantityWholeSale = 5;

    public static Discount createDiscount(Product product, int totalQuantity, DiscountCard discountCard) {

        if (product.isWholesaleProduct() && totalQuantity >= minQuantityWholeSale) {
            // Если товар является оптовым и покупаемое количество больше или равно минимальному для оптовой сделки,
            // возвращаем оптовую скидку.
            return new DiscountWholeSale();
        } else if (discountCard != null && discountCard.getRate() > 0) {
            // В противном случае возвращаем скидку по карте скидку.
            return new DiscountCustom( discountCard.getRate());
        } else {
            return new DiscountZero();
        }
    }
}
