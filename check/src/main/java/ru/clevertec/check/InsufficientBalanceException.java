package main.java.ru.clevertec.check;

public class InsufficientBalanceException extends Exception {
    public InsufficientBalanceException() {
        super("NOT ENOUGH MONEY");
    }
}