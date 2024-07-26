package main.java.ru.clevertec.check;

public class IncorrectDataException extends Exception {
    public IncorrectDataException() {
        super("BAD REQUEST");
    }
}