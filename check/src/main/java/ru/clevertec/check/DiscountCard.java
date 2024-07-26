package main.java.ru.clevertec.check;

public class DiscountCard {
    private int id;
    private int number;
    private int rate;

    public DiscountCard(int id, int number, int rate) {
        this.id = id;
        this.number = number;
        this.rate = rate;
    }

    public int getNumber() {
        return number;
    }

    public int getRate() {
        return rate;
    }

    @Override
    public String toString() {
        return number + ";" + rate;
    }
}