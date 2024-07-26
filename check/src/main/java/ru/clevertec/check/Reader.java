package main.java.ru.clevertec.check;

import java.sql.SQLException;
import java.util.List;

public interface Reader {
    public List<Product> readProducts() throws  IncorrectDataException;
    public List<DiscountCard> readDiscountCards() ;
}
