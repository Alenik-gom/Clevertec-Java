package main.java.ru.clevertec.check;

import java.sql.SQLException;
import java.util.List;

public interface Reader {
    public List<Product> readProducts() throws SQLException, IncorrectDataException, ClassNotFoundException;
    public List<DiscountCard> readDiscountCards() throws SQLException, ClassNotFoundException;
}
