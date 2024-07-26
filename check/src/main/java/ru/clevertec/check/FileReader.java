package main.java.ru.clevertec.check;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileReader implements Reader {
    private String cvsSplitBy = ";";
    private String pathProducts;
    private String pathDiscountCards;

    public FileReader(String pathProducts, String pathDiscountCards) {
        this.pathProducts = pathProducts;
        this.pathDiscountCards = pathDiscountCards;
    }

    public List<Product> readProducts() {
        List<Product> products = new ArrayList<>();
        String line;
        try (BufferedReader br = new BufferedReader(new java.io.FileReader(pathProducts))) {
            br.readLine();  // пропустить первую строку
            while ((line = br.readLine()) != null) {
                String[] values = line.split(cvsSplitBy);
                int id = Integer.parseInt(values[0]);
                String description = values[1];
                int price = (int) (Double.parseDouble(values[2].replace(',', '.')) * 100);
                int quantityInStock = Integer.parseInt(values[3]);
                boolean wholesaleProduct = Boolean.parseBoolean(values[4]);
                products.add(new Product(id, description, price, quantityInStock, wholesaleProduct));

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return products;
    }

    public List<DiscountCard> readDiscountCards() {
        List<DiscountCard> discountCards = new ArrayList<>();
        String line;
        try (BufferedReader br = new BufferedReader(new java.io.FileReader(pathDiscountCards))) {
            br.readLine();  // пропустить первую строку
            while ((line = br.readLine()) != null) {
                String[] values = line.split(cvsSplitBy);
                int id = Integer.parseInt(values[0]);
                int number = Integer.parseInt(values[1]);
                int amount = Integer.parseInt(values[2]);
                discountCards.add(new DiscountCard(id, number, amount));

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return discountCards;
    }
}

