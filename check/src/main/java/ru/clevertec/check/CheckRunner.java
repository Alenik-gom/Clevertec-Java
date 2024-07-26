package main.java.ru.clevertec.check;

import java.util.List;
import java.util.Map;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class CheckRunner {

    public static void main(String[] args) {
        try {
            String[] argsTest = {"3-1", "2-5", "5-5", "balanceDebitCard=300"};
            // обработка входных параметров и сохраняение в нужном формате
            InputData inputData = new InputData(args);
            // создатние чека
            Check check = Creator.createCheck(inputData);
            // печать чека
            CheckPrinter printer = new CheckPrinter(check);
            printer.printCheck();
        } catch (IncorrectDataException | InsufficientBalanceException e) {
            CheckPrinter.printError(e.getMessage());
        } catch (Exception e) {
            CheckPrinter.printError("INTERNAL SERVER ERROR");
        }
    }
}