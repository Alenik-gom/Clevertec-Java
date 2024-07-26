package main.java.ru.clevertec.check;

public class EmptyPathProductsException extends Exception {
    private String SaveToFile;
    public EmptyPathProductsException(String SaveToFile) {
        super("BAD REQUEST");
        this.SaveToFile = SaveToFile;
    }
    public String getSaveToFile() { return SaveToFile; }
}