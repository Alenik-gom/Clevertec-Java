package main.java.ru.clevertec.check;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class CheckRunner {

    public static void main(String[] args) {
        try {
            String[] argsTest = {"3-1", "2-5", "5-5", "balanceDebitCard=300",
                    "saveToFile=./error_result.csv", "pathToFile=./products.csv"};
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
        } catch (EmptyPathProductsException e) {
            CheckPrinter.setFileName(e.getSaveToFile());
            CheckPrinter.printError(e.getMessage());
        } catch (Exception e) {
            CheckPrinter.printError("INTERNAL SERVER ERROR");
        }
    }
}