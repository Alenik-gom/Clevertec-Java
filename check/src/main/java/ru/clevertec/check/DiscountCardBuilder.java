package main.java.ru.clevertec.check;

import java.util.List;

public class DiscountCardBuilder {
    private int id = 0;
    private int number;
    private int rate = 0;
    private int customRate = 2;

    public DiscountCardBuilder fromData(List<DiscountCard> discountCards, int cardNumber) {
        // если карты нет в списке - 2%
        DiscountCard targetCard = discountCards.stream()
                .filter(card -> card.getNumber() == cardNumber)
                .findFirst()
                .orElse(null);
        if (targetCard != null) {
            this.number = targetCard.getNumber();
            this.rate = targetCard.getRate();
        } else if (cardNumber != 0) {
            this.number = cardNumber;
            this.rate = customRate;
        }
        else {
            this.number = 0;
            this.rate = 0;
        }
        return this;
    }

    public DiscountCard build() {
            return new DiscountCard(this.id, this.number, this.rate);
    }
}