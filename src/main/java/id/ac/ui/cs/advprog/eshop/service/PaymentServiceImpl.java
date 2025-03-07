package id.ac.ui.cs.advprog.eshop.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import id.ac.ui.cs.advprog.eshop.model.Order;
import java.util.List;
import java.util.UUID;

public class PaymentServiceImpl {
    private final PaymentRepository paymentRepository;

    @Autowired
    public PaymentServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public Payment addPayment(Order order, String method, Map<String, String> paymentData){
        Payment payment = new Payment(UUID.randomUUID().toString(), method, order, paymentData);
        return paymentRepository.save(payment);
    }

    public Payment setStatus(Payment payment, String status){
        payment.setStatus(status);
        return paymentRepository.save(payment);
    }

    public Payment getPayment(String paymentId){
        return paymentRepository.findById(paymentId);
    }

    public List<Payment> getAllPayments(){
        return paymentRepository.getAllPayments();
    }
}