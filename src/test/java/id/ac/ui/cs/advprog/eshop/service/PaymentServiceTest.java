package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PaymentServiceTest {

    @Mock
    private PaymentRepository paymentRepository;

    @InjectMocks
    private PaymentServiceImpl paymentService;

    private Order order;

    List<Payment> payments; 

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
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234ABC5678");
        Payment samplePayment = new Payment("pay1", "VOUCHER", order, paymentData);
        when(paymentRepository.save(any(Payment.class))).thenReturn(samplePayment);

        Payment result = paymentService.addPayment(order, "VOUCHER", paymentData);
        assertNotNull(result);
        verify(paymentRepository, times(1)).save(any(Payment.class));
    }

    @Test
    void testSetStatusSuccess() {
        Payment payment = payments.get(0);
        when(paymentRepository.save(payment)).thenReturn(payment);

        Payment updatedPayment = paymentService.setStatus(payment, PaymentStatus.SUCCESS.getValue());
        assertEquals(PaymentStatus.SUCCESS.getValue(), updatedPayment.getStatus());
        assertEquals("SUCCESS", order.getStatus());
        verify(paymentRepository, times(1)).save(payment);
    }

    @Test
    void testSetStatusRejected() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOPINVALIDCODEAA");
        Payment payment = new Payment("pay1", "VOUCHER", order, paymentData);
        when(paymentRepository.save(payment)).thenReturn(payment);

        Payment updatedPayment = paymentService.setStatus(payment, PaymentStatus.REJECTED.getValue());
        assertEquals(PaymentStatus.REJECTED.getValue(), updatedPayment.getStatus());
        assertEquals("FAILED", order.getStatus());
        verify(paymentRepository, times(1)).save(payment);
    }

    @Test
    void testGetPayment() {
        Payment payment = payments.get(0);
        when(paymentRepository.findById("pay1")).thenReturn(payment);

        
        Payment result = paymentService.getPayment("pay1");
        assertNotNull(result);
        assertEquals("pay1", result.getId());
    }

    @Test
    void testGetAllPayments() {
        Payment payment1 = payments.get(0);
        Payment payment2 = payments.get(1);
        List<Payment> payments = Arrays.asList(payment1, payment2);
        when(paymentRepository.getAllPayments()).thenReturn(payments);

        List<Payment> result = paymentService.getAllPayments();
        assertEquals(2, result.size());
        assertTrue(result.contains(payment1));
        assertTrue(result.contains(payment2));
    }
}