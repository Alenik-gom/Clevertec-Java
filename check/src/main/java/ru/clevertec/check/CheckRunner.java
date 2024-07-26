package main.java.ru.clevertec.check;

import java.sql.SQLException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class CheckRunner {

    public static void main(String[] args) {
        try {
            String[] argsTest = {"3-1", "2-5", "5-1",
                    "discountCard=1111", "balanceDebitCard=100",
                    "saveToFile=./result.csv",
                    "datasource.url=jdbc:postgresql://localhost:5432/check",
                    "datasource.username=postgres",
                    "datasource.password=postgres"
            };
            // обработка входных параметров и сохраняение в нужном формате
            InputData inputData = new InputData(args);
            // создатние чека
            Check check = Creator.createCheck(inputData);
            // печать чека
            CheckPrinter printer = new CheckPrinter(check);
            CheckPrinter.setFileName(inputData.getSaveToFile());
            printer.printCheck();

        } catch (IncorrectDataException | InsufficientBalanceException e) {
            CheckPrinter.printError(e.getMessage());
        } catch (Exception e) {
            CheckPrinter.printError("INTERNAL SERVER ERROR");
        }
    }
}