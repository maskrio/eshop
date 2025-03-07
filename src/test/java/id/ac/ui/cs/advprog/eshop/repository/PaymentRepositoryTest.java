package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PaymentRepositoryTest {

    @InjectMocks
    private PaymentRepository paymentRepository;

    private List<Payment> payments;

    @BeforeEach
    void setUp() {
        payments = new ArrayList<>();
        List<Product> products = new ArrayList<>();
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(2);
        products.add(product1);

        Order order = new Order("13652556-012a-4c07-b546-54eb1396d79b",
                products, 1708560000L, "Safira Sudrajat");
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234ABC5678");
        Payment payment = new Payment("192241bd-04c4-46b9-9959-489cd360117b",
                PaymentMethod.VOUCHER.getValue(), order, paymentData);
        payments.add(payment);

        Order order2 = new Order("cc97d0f9-5df2-4eda-b524-85203381b16e",
                products, 1708560000L, "Bambang");
        Map<String, String> paymentData2 = new HashMap<>();
        paymentData2.put("bankName", "Bank XYZ");
        paymentData2.put("referenceCode", "TRANS1234567890");
        Payment payment2 = new Payment("ee74bd22-fae2-45e9-93f1-7b863660ed64",
                PaymentMethod.BANK_TRANSFER.getValue(), order2, paymentData2);
        payments.add(payment2);
    }

    @Test
    void testAddPayment() {
        Payment newPayment = paymentRepository.save(payments.get(1));
        assertNotNull(newPayment);
        assertEquals(PaymentMethod.BANK_TRANSFER.getValue(), newPayment.getMethod());
        assertEquals(payments.get(1).getOrder(), newPayment.getOrder());
    }

    @Test
    void testUpdatePayment() {
        Payment payment = payments.get(0);
        Payment newPayment = paymentRepository.save(payment);
        newPayment.setStatus(PaymentStatus.FAILED.getValue());
        Payment updated = paymentRepository.save(newPayment);
        assertNotNull(updated);
        assertEquals(newPayment.getStatus(), updated.getStatus());
    }

    @Test
    void testFindById() {
        Payment payment = payments.get(0);
        paymentRepository.save(payment);
        Payment found = paymentRepository.findById(payment.getId());
        assertNotNull(found);
        assertEquals(payment, found);
    }

    @Test
    void testFindByIdNotFound() {
        Payment payment = payments.get(0);
        paymentRepository.save(payment);
        Payment found = paymentRepository.findById(payments.get(1).getId());
        assertNull(found);
    }

    @Test
    void testGetAllPayments() {
        paymentRepository.save(payments.get(0));
        paymentRepository.save(payments.get(1));
        List<Payment> result = paymentRepository.getAllPayments();
        assertNotNull(result);
        assertEquals(payments.size(), result.size());
    }
}
