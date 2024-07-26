package main.java.ru.clevertec.check;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Creator {

    private static String pathDiscountCards = "./src/main/resources/discountCards.csv";
    private static Reader reader;

    private static Reader createReader(String pathProducts, String pathDiscountCards) {
        return reader = new FileReader( pathProducts, pathDiscountCards);
    }

    public void setPathDiscountCards(String path) {
        pathDiscountCards = path;
    }

    private static List<CheckItem> createCheckItems(Map<Integer, Integer> productQuantities) throws IncorrectDataException, SQLException, ClassNotFoundException {
        // считываем список продуктов из файла
        List<Product> productsFile = reader.readProducts();
        // создаем айтемы в чеке из входных параметров
        List<CheckItem> checkLines = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : productQuantities.entrySet()) {
            // если продукта нет в списке - ошибка
            Product targetProduct = productsFile.stream()
                    .filter(product -> product.getId() == entry.getKey())
                    .findFirst()
                    .orElse(null);  // возврат null, если ничего не найдено
            if (targetProduct != null) {
                checkLines.add(new CheckItem(targetProduct, entry.getValue()));
            } else throw new IncorrectDataException();
        }
        return checkLines;
    }

    private static DiscountCard createDiscountCard(int cardNumber) throws SQLException, ClassNotFoundException {
        // читам список доступных дисконтных кард из файла
        List<DiscountCard> discountCards = reader.readDiscountCards();
        // создаем дисконтную карту
        return new DiscountCardBuilder().fromData(discountCards, cardNumber).build();
    }
    public static Check createCheck (InputData inputData) throws SQLException, IncorrectDataException, ClassNotFoundException, InsufficientBalanceException {
        // создаем обьект интерфейса, в нашем случае 1 способ чтения, но может быть и из файла
        reader = createReader(inputData.getPathToFile(), pathDiscountCards);

        Check check = new Check();
        check.setItems( createCheckItems(inputData.getProductQuantities()));
        // проверяем, сходится ли общее количество товаров в чеке и стоке
        check.validateQuantity();
        // устанавливаем вид скидки для каждой строки (даже если товар повторяется)
        check.setDiscountCard(createDiscountCard(inputData.getDiscountCard()));
        // высчитываем скидки и суммы как по айтемам так и в целом
        check.applyDiscount();
        // сверяем итоговую сумму с балансом
        check.validateBalance(inputData.getBalanceCard());
        return check;
    }
}
