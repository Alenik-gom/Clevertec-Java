package main.java.ru.clevertec.check;

import java.util.ArrayList;
import java.util.List;

public class Check {
    private DiscountCard discountCard;
    private List<CheckItem> items;
    private int totalPrice;
    private int totalDiscount;
    private int totalPriceWithDiscount;

    public Check() {
        items = new ArrayList<CheckItem>();
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public int getTotalDiscount() {
        return totalDiscount;
    }

    public int getTotalPriceWithDiscount() {
        return totalPriceWithDiscount;
    }

    public void setItems(List<CheckItem> items) {
        this.items = items;
    }

    public DiscountCard getDiscountCard() {
        return discountCard;
    }

    public void setDiscountCard(DiscountCard discountCard) {
        this.discountCard = discountCard;
    }

    public List<CheckItem> getItems() {
        return items;
    }

    public void addCheckItem(Product product, int quantity) {
        items.add(new CheckItem(product, quantity));
    }

    public void validateQuantity() throws IncorrectDataException {
        for (CheckItem item : items) {
            if (getQuantitySumById(item.getProduct().getId()) > item.getProduct().getQuantityInStock()) {
                throw new IncorrectDataException();
            }
        }

    }

    public void applyDiscount() {
        for (CheckItem item : items) {
            Discount newDiscount = DiscountFactory.createDiscount(item.getProduct(), getQuantitySumById(item.getProduct().getId()), discountCard);
            // для кажого айтема свой дисконт
            item.setDiscount(newDiscount);
            // подсчитываем суммы в айтеме
            item.calculateAmounts();
            // подсчитываем суммы по чеку
            totalPrice += item.getTotalAmount();
            totalDiscount += item.getDiscountAmount();
        }
        totalPriceWithDiscount = totalPrice - totalDiscount;
    }

    private int getQuantitySumById(int id) {
        int sum = 0;
        // считаем общее количесво конкретного в чеке
        for (CheckItem item : items) {
            if (item.getProduct().getId() == id) {
                sum += item.getQuantity();
            }
        }
        return sum;
    }

    public void validateBalance(int balance) throws InsufficientBalanceException {
        if (balance < totalPriceWithDiscount) {
            throw new InsufficientBalanceException();
        }
    }

}
