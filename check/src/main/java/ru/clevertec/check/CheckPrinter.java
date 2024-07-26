package main.java.ru.clevertec.check;

import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class CheckPrinter {
    private Check check;
    private static String currencyDollar = "$";
    private static String fileName = "result.csv";

    public CheckPrinter(Check check) {
        this.check = check;
    }

    public static String convertToMoney(int amount) {
        // тобраать 2 знака после запятой всегда
        BigDecimal amountBigDecimal = BigDecimal.valueOf(amount).divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);
        return amountBigDecimal + currencyDollar;
    }

    private String printHeader() {
        //  форматируем дату
        LocalDate date = LocalDate.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String formattedDate = date.format(dateFormatter);

        // форматируем время
        LocalTime time = LocalTime.now();
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String formattedTime = time.format(timeFormatter);
        return "Date;Time" + "\n" + formattedDate + ";" + formattedTime + "\n";
    }

    private String printLine() {
        return " " + "\n";
    }

    private String printFooter() {

        return "TOTAL PRICE;TOTAL DISCOUNT; TOTAL WITH DISCOUNT" + "\n" +
                convertToMoney(check.getTotalPrice()) + ";" +
                convertToMoney(check.getTotalDiscount()) + ";" +
                convertToMoney(check.getTotalPriceWithDiscount());
    }

    private String printItems() {
        String stringOutpit = "QTY;DESCRIPTION;PRICE;DISCOUNT;TOTAL" + "\n";
        for (CheckItem item : check.getItems()) {
            stringOutpit += item.toString() + "\n";
        }
        return stringOutpit;
    }

    private String printDiscountCard() {
        // сли карта редъявлена -показать
        String stringOutpit = "";
        if (check.getDiscountCard().getNumber() != 0 ) {
            stringOutpit ="\n"+ "DISCOUNT CARD;DISCOUNT PERCENTAGE" + "\n" +
                    check.getDiscountCard().toString() + "\n";
        }
        return stringOutpit;
    }

    public void printCheck() {
        String csvOutput = printHeader() +
                           printLine() +
                           printItems() +
                           printDiscountCard() +
                           printLine() +
                           printFooter();
        System.out.println(csvOutput);
        writeToCSV(csvOutput);
    }

    private static void writeToCSV(String csvOutput) {
        try (FileWriter fileWriter = new FileWriter(fileName)) {
            fileWriter.write(csvOutput);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void printError(String error) {
        String csvOutput = "ERROR" + "\n" + error;
        System.out.println(csvOutput);
        writeToCSV(csvOutput);
    }
}
