package main.java.ru.clevertec.check;

public class CheckItem {
    private Product product;
    private int quantity;
    private Discount discount;
    private int discountAmount;
    private int totalAmount;


    public CheckItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Discount getDiscount() {
        return discount;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public int getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
    }

    public void calculateAmounts() {
        this.totalAmount = quantity * product.getPrice();
        this.discountAmount = discount.getDiscountAmount(totalAmount);
    }

    @Override
    public String toString() {
        return quantity + ";" +
                product.getDescription() + ";" +
                CheckPrinter.convertToMoney(product.getPrice()) + ";" +
                CheckPrinter.convertToMoney(discountAmount) + ";" +
                CheckPrinter.convertToMoney(totalAmount);
    }

}
