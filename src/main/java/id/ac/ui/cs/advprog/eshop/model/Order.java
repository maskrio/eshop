package id.ac.ui.cs.advprog.eshop.model;

import java.util.Arrays;
import java.util.List;
import id.ac.ui.cs.advprog.eshop.enums.OrderStatus;

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
        this.status = OrderStatus.WAITING_PAYMENT.getValue();
    }

    public Order(String id, List<Product> products, Long orderTime, String author, String status) {
        this(id, products, orderTime, author);
        this.setStatus(status);
    }

    public void setStatus(String status) {
        if (OrderStatus.contains(status)) {
            this.status = status;
        } else {
            throw new IllegalArgumentException();
        }
    }
}