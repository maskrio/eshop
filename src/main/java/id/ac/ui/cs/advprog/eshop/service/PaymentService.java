package id.ac.ui.cs.advprog.eshop.service;

import java.util.Map;

import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.model.Order;

public interface PaymentService {
    public Payment addPayment(Order order, String method, Map<String, String> paymentData);
    public Payment setStatus(Payment payment, String status);
    public Payment getPayment(String paymentId);
    public Payment getAllPayments();
}
