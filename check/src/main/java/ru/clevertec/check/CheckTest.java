package main.java.ru.clevertec.check;

import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;


public class CheckTest {
    InputData inputData;
    private Check check = new Check();

    @Test
    public void testTotalDiscount() throws  IncorrectDataException, SQLException, InsufficientBalanceException, ClassNotFoundException {
        String[] argsTest = {"10-7", "4-5", "2-5", "5-5", "balanceDebitCard=300",
                "saveToFile=./result.csv",
                "datasource.url=jdbc:postgresql://localhost:5432/check",
                "datasource.username=postgres", "datasource.password=postgres",
        };
        InputData inputData = new InputData(argsTest);
        check = Creator.createCheck(inputData);
        // печать чека
        CheckPrinter printer = new CheckPrinter(check);
        CheckPrinter.setFileName(inputData.getSaveToFile());
        printer.printCheck();
        // проверка результата
        assertEquals(136, check.getTotalDiscount()); // пример проверки
    }

    @Test
    public void testTotalPriceWithoutDiscount() throws  IncorrectDataException, SQLException, InsufficientBalanceException, ClassNotFoundException {
        String[] argsTest = {"10-7", "balanceDebitCard=300",
                "saveToFile=./result.csv",
                "datasource.url=jdbc:postgresql://localhost:5432/check",
                "datasource.username=postgres", "datasource.password=postgres",
        };
        InputData inputData = new InputData(argsTest);
        check = Creator.createCheck(inputData);
        // проверка результата
        assertEquals(8960, check.getTotalPriceWithDiscount()); // пример проверки
    }
    @Test
    public void testDiscountRate() throws  IncorrectDataException, SQLException, InsufficientBalanceException, ClassNotFoundException {
        String[] argsTest = {"1-5", "balanceDebitCard=300",
                "saveToFile=./result.csv",
                "datasource.url=jdbc:postgresql://localhost:5432/check",
                "datasource.username=postgres", "datasource.password=postgres",
        };
        InputData inputData = new InputData(argsTest);
        check = Creator.createCheck(inputData);
        System.out.println("real - " + check.getItems().getFirst().getDiscount().getDiscountRate()+"; expected - 10");
        // проверка результата
        assertEquals(10, check.getItems().getFirst().getDiscount().getDiscountRate());
        // пример проверки
    }
    @Test
    public void testTotalPrice() throws  IncorrectDataException, SQLException, ClassNotFoundException, InsufficientBalanceException {
        String[] argsTest = {"3-1", "2-5", "5-5", "balanceDebitCard=300",
                "discountCard=4444",
                "saveToFile=./result.csv",
                "datasource.url=jdbc:postgresql://localhost:5432/check",
                "datasource.username=postgres", "datasource.password=postgres",
        };
        InputData inputData = new InputData(argsTest);
        check = Creator.createCheck(inputData);

        // проверка результата
        assertEquals(2160, check.getTotalPrice()); // пример проверки
    }

    @Test
    public void testCorrectInput() throws  IncorrectDataException {
        String[] argsTest = {"3-1", "2-5", "5-5", "balanceDebitCard=300",
                "discountCard=4444",
                "discountCard=12345",
                "saveToFile=./result.csv",
                "datasource.url=jdbc:postgresql://localhost:5432/check",
                "datasource.username=postgres", "datasource.password=postgres",
        };
        InputData inputData = new InputData(argsTest);

        // проверка результата
        assertEquals(30000, inputData.getBalanceCard()); // пример проверки
    }

    @Test(expected = IncorrectDataException.class)
    public void TestIncorrectInput() throws IncorrectDataException {

        String[] argsTest = {"3-1", "2-5", "5-5", "balanceDebitCard=300",
                "discountCard=4444",
                "saveToFile=./result.csv",
                "datasource.username=postgres", "datasource.password=postgres",
        };
        InputData inputData = new InputData(argsTest);
    }
    @Test(expected = InsufficientBalanceException.class)
    public void TestIncorrectBalance() throws  IncorrectDataException, SQLException, InsufficientBalanceException, ClassNotFoundException {

        String[] argsTest = {"3-1", "2-5", "5-5", "balanceDebitCard=3",
                "discountCard=4444",
                "discountCard=12345",
                "saveToFile=./result.csv",
                "datasource.url=jdbc:postgresql://localhost:5432/check",
                "datasource.username=postgres", "datasource.password=postgres",
        };
        InputData inputData = new InputData(argsTest);
        check = Creator.createCheck(inputData);
    }
}