package id.ac.ui.cs.advprog.eshop.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import id.ac.ui.cs.advprog.eshop.model.Order;
import java.util.List;
import java.util.UUID;

public class PaymentServiceImpl {
    @Autowired
    private PaymentRepository PaymentRepository;

    public Payment addPayment(Order order, String method, Map<String, String> paymentData){
        Payment payment = new Payment(UUID.randomUUID().toString(), method, order, paymentData);
        return PaymentRepository.save(payment);
    }

    public Payment setStatus(Payment payment, String status){
        payment.setStatus(status);
        return PaymentRepository.save(payment);
    }

    public Payment getPayment(String paymentId){
        return PaymentRepository.findById(paymentId);
    }

    public List<Payment> getAllPayments(){
        return PaymentRepository.getAllPayments();
    }
}