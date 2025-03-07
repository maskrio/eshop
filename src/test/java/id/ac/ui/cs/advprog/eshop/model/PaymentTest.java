package id.ac.ui.cs.advprog.eshop.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;

public class PaymentTest {
    private Map<String, String> paymentData;
    private List<Product> products;
    private Order order;

    @BeforeEach
    void setUp() {
        this.products = new ArrayList<>();
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(2);
        Product product2 = new Product();
        product2.setProductId("a2b3e9f-1c39-460e-8860-71af6af63bd6");
        product2.setProductName("Sampo Cap Usep");
        product2.setProductQuantity(1);
        this.products.add(product1);
        this.products.add(product2);
        this.order = new Order("13652556-012a-4c07-b546-54eb1396d79b",
                this.products, 1708560000L, "Safira Sudrajat");

        paymentData = new HashMap<>();
        paymentData.put("accountNumber", "1234567890");
        paymentData.put("accountName", "Mana A");
    }

    @Test
    void testCreatePaymentEmptyMethod() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Payment("1234", "", order, paymentData, PaymentStatus.PENDING.getValue());
        });
    }

    @Test
    void testCreatePaymentDefaultStatus() {
        Payment payment = new Payment("1234", PaymentMethod.VOUCHER.getValue(), order, paymentData);
        assertEquals(PaymentStatus.PENDING.getValue(), payment.getStatus());
        assertEquals("1234", payment.getId());
        assertEquals(PaymentMethod.VOUCHER.getValue(), payment.getMethod());
        assertEquals(paymentData, payment.getPaymentData());
    }

    @Test
    void testCreatePaymentSuccessStatus() {
        Payment payment = new Payment("1234", PaymentMethod.VOUCHER.getValue(), order, paymentData, PaymentStatus.SUCCESS.getValue());
        assertEquals(PaymentStatus.SUCCESS.getValue(), payment.getStatus());
        assertEquals(paymentData, payment.getPaymentData());
    }

    @Test
    void testCreatePaymentInvalidStatus() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Payment("1234", PaymentMethod.VOUCHER.getValue(), order, paymentData, "MEOW");
        });
    }

    @Test
    void testSetStatusToFailed() {
        Payment payment = new Payment("1234", PaymentMethod.VOUCHER.getValue(), order, paymentData, PaymentStatus.SUCCESS.getValue());
        payment.setStatus(PaymentStatus.FAILED.getValue());
        assertEquals(PaymentStatus.FAILED.getValue(), payment.getStatus());
    }

    @Test
    void testSetStatusToInvalidStatus() {
        Payment payment = new Payment("1234", PaymentMethod.VOUCHER.getValue(), order, paymentData, PaymentStatus.PENDING.getValue());
        assertThrows(IllegalArgumentException.class, () -> payment.setStatus("MEOW"));
    }

    @Test 
    void testEmptyPaymentData() {
        paymentData.clear();
        assertThrows(IllegalArgumentException.class, () -> {
            Payment payment = new Payment("1234", PaymentMethod.VOUCHER.getValue(), order, paymentData);
        });
    }
}
