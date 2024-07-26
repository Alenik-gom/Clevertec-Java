package main.java.ru.clevertec.check;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InputData {
    private int balanceCard;
    private int discountCard;
    private Map<Integer, Integer> productQuantities;

    public InputData(String[] args) throws IncorrectDataException {
        validateInput(Arrays.asList(args));

        productQuantities = new HashMap<>();
        // парсим параметры
        for (String arg : args) {
            if (arg.startsWith("balanceDebitCard=")) {
                String[] keyValue = arg.split("=", 2);
                if (keyValue.length == 2) {
                    try {
                        balanceCard = (int) Math.round(Double.parseDouble(keyValue[1]) * 100);
                    } catch (NumberFormatException e) {
                        throw new IncorrectDataException();
                    }
                }
            } else if (arg.contains("-")) {
                String[] keyValue = arg.split("-");
                if (keyValue.length == 2) {
                    try {
                        int productId = Integer.parseInt(keyValue[0]);
                        int quantity = Integer.parseInt(keyValue[1]);
                        if (productId < 0 || quantity < 0) {
                            throw new IncorrectDataException();
                        }
                        productQuantities.put(productId, quantity);
                    } catch (NumberFormatException e) {
                        throw new IncorrectDataException();
                    }

                }
            } else if (arg.startsWith("discountCard=")) {
                String[] keyValue = arg.split("=", 2);
                if (keyValue.length == 2) {
                    try {
                        discountCard = Integer.parseInt(keyValue[1]);
                    } catch (NumberFormatException e) {
                        throw new IncorrectDataException();
                    }
                }
            }

        }
    }

    public int getBalanceCard() {
        return balanceCard;
    }

    public int getDiscountCard() {
        return discountCard;
    }

    public Map<Integer, Integer> getProductQuantities() {
        return productQuantities;
    }

    private void validateInput(List<String> argsList) throws IncorrectDataException {

        // Проверяем наличие обязательного аргумента "balanceDebitCard"
        if (argsList.stream().noneMatch(arg -> arg.startsWith("balanceDebitCard="))) {
            throw new IncorrectDataException();
        }

        // Проверяем наличие хотя бы одного товара ("id-quantity")
        if (argsList.stream().noneMatch(arg -> arg.contains("-"))) {
            throw new IncorrectDataException();
        }

    }

}
