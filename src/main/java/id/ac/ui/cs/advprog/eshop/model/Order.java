package id.ac.ui.cs.advprog.eshop.model;

import java.util.Arrays;
import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Order {
    String id;
    List<Product> products;
    long orderTime;
    String author;
    String status;

    public Order(String id, List<Product> products, long orderTime, String author) {
        if (products.isEmpty()) {
            throw new IllegalArgumentException("Order must have at least one product");
        }
        this.id = id;
        this.products = products;
        this.orderTime = orderTime;
        this.author = author;
        this.status = "WAITING_PAYMENT";
    }

    public Order(String id, List<Product> products, long orderTime, String author, String status) {
        this(id, products, orderTime, author);

        String[] statusList = { "WAITING_PAYMENT", "FAILED", "SUCCESS", "CANCELED" };
        if (Arrays.stream(statusList).noneMatch(status::equals)) {
            throw new IllegalArgumentException("Invalid status");
        } else {
            this.status = status;
        }
    }

    public void setStatus(String status) {
        String[] statusList = { "WAITING_PAYMENT", "FAILED", "SUCCESS", "CANCELED" };
        if (Arrays.stream(statusList).noneMatch(status::equals)) {
            throw new IllegalArgumentException("Invalid status");
        } else {
            this.status = status;
        }
    }
}