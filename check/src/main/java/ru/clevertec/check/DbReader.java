package main.java.ru.clevertec.check;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DbReader implements Reader {
    private String url;
    private String user;
    private String password;
    private String driver = "org.postgresql.Driver";
    String sqlDiscountCards = "SELECT * FROM discount_card";
    String sqlProducts = "SELECT * FROM product";

    public DbReader(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    private ResultSet readFromDB(String sqlStatement) throws SQLException, ClassNotFoundException {
        ResultSet resultSet;
        try {
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(sqlStatement);
        } catch (SQLException e) {
            throw e;
        }
        return resultSet;
    }

    @Override
    public List<Product> readProducts() throws SQLException, ClassNotFoundException {
        List<Product> products = new ArrayList<>();
        try {
            ResultSet productsSet = readFromDB(sqlProducts);
            while (productsSet.next()) {
                int id = productsSet.getInt("id");
                String description = productsSet.getString("description");
                // 100 - для точности вычислений
                int price = (int) (productsSet.getDouble("price") * 100);
                int quantityInStock = productsSet.getInt("quantity_in_stock");
                boolean wholesaleProduct = productsSet.getBoolean("wholesale_product");
                products.add(new Product(id, description, price, quantityInStock, wholesaleProduct));
            }

        } catch (SQLException e) {
            throw e;
        }
        return products;
    }

    @Override
    public List<DiscountCard> readDiscountCards() throws SQLException, ClassNotFoundException {
        List<DiscountCard> discountCards = new ArrayList<>();
        try {
            ResultSet discountCardsSet = readFromDB(sqlDiscountCards);
            while (discountCardsSet.next()) {
                int id = discountCardsSet.getInt("id");
                int number = discountCardsSet.getInt("number");
                int amount = discountCardsSet.getInt("amount");
                discountCards.add(new DiscountCard(id, number, amount));
            }
        } catch (SQLException e) {
            throw e;
        }
        return discountCards;
    }
}
