package id.ac.ui.cs.advprog.eshop.model;

import java.util.Map;

import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.HashMap;

@Setter
@Getter
public class Payment {
    String id;
    String method;
    Order order;
    String status;
    Map<String, String> paymentData;

    public Payment(String id, String method, Order order) {
        this.id = id;
        this.setMethod(method);
        this.order = order;
        this.setStatus("PENDING");
        this.paymentData = new HashMap<>();
    }

    public Payment(String id, String method, Order order, String status, Map<String, String> paymentData) {
        this(id, method, order);
        this.setStatus(status);
        this.paymentData = paymentData;
    }

    public void setStatus(String status) {
        String[] statusList = { "REJECTED", "FAILED", "SUCCESS", "PENDING" };
        if (Arrays.stream(statusList).noneMatch(status::equals)) {
            throw new IllegalArgumentException("Invalid status");
        } else {
            this.status = status;
        }
    }
    public void setMethod(String method) {
        String[] methodList = { "VOUCHER", "BANK_TRANSFER"};
        if (Arrays.stream(methodList).noneMatch(method::equals)) {
            throw new IllegalArgumentException("Invalid method");
        } else {
            this.method = method;
        }
    }
}
