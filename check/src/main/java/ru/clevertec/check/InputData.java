package main.java.ru.clevertec.check;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InputData {
    private int balanceCard;
    private int discountCard;
    private Map<Integer, Integer> productQuantities;
    private String pathToFile;
    private String saveToFile;
    private String url;
    private String username;
    private String password;

    public InputData(String[] args) throws  IncorrectDataException {
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
            } else if (arg.startsWith("datasource.url=")) {
                String[] keyValue = arg.split("=", 2);
                if (keyValue.length == 2) {
                    url = keyValue[1];
                }
            } else if (arg.startsWith("datasource.username=")) {
                String[] keyValue = arg.split("=", 2);
                if (keyValue.length == 2) {
                    username = keyValue[1];
                }
            } else if (arg.startsWith("datasource.password=")) {
                String[] keyValue = arg.split("=", 2);
                if (keyValue.length == 2) {
                    password = keyValue[1];
                }
            } else if (arg.startsWith("saveToFile=")) {
                String[] keyValue = arg.split("=", 2);
                if (keyValue.length == 2) {
                    saveToFile = keyValue[1];
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

    public String getPathToFile() {
        return pathToFile;
    }

    public String getSaveToFile() {
        return saveToFile;
    }

    public String getUrl() { return url; }

    public String getUsername() { return username; }

    public String getPassword() { return password; }


    public Map<Integer, Integer> getProductQuantities() {
        return productQuantities;
    }

    private void validateInput(List<String> argsList) throws  IncorrectDataException {

        // Проверяем наличие обязательного аргумента "balanceDebitCard"
        if (argsList.stream().noneMatch(arg -> arg.startsWith("balanceDebitCard="))) {
            throw new IncorrectDataException();
        }

        // Проверяем наличие хотя бы одного товара ("id-quantity")
        if (argsList.stream().noneMatch(arg -> arg.contains("-"))) {
            throw new IncorrectDataException();
        }
        // Проверяем наличие обязательного аргумента "balanceDebitCard"
        if (argsList.stream().noneMatch(arg -> arg.startsWith("saveToFile="))) {
            throw new IncorrectDataException();
        }
        if (argsList.stream().noneMatch(arg -> arg.startsWith("datasource.url="))) {
            throw new IncorrectDataException();
        }
        if (argsList.stream().noneMatch(arg -> arg.startsWith("datasource.username="))) {
            throw new IncorrectDataException();
        }
        if (argsList.stream().noneMatch(arg -> arg.startsWith("datasource.password="))) {
            throw new IncorrectDataException();
        }
    }
}
