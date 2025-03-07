package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Payment;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.ArrayList;

@Repository
public class PaymentRepository {
    private List<Payment> paymentData = new ArrayList<>();
    public Payment save(Payment payment) {
        int index = findIndexById(payment.getId());
        if(index != -1) {
            paymentData.remove(index);
        }
        paymentData.add(payment);
        return payment;
    }

    public Payment findById(String id) {
        int index = findIndexById(id);
        if(index == -1) return null;
        return paymentData.get(index);
    }

    public List<Payment> getAllPayments() { 
        return paymentData;
    }

    private int findIndexById(String id) {
        int i = 0;
        for (Payment savedPayment : paymentData) {
            if (savedPayment.getId().equals(id)) {
                return i;
            }
        }
        return -1;
    }
}