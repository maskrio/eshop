package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductRepositoryTest {

    @InjectMocks
    ProductRepository productRepository;

    @Test
    void testCreateAndFind() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product.getProductId(), savedProduct.getProductId());
        assertEquals(product.getProductName(), savedProduct.getProductName());
        assertEquals(product.getProductQuantity(), savedProduct.getProductQuantity());
    }

    @Test
    void testFindAllEmpty() {
        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }
    @Test
    void testFindAllIfMoreThanOneProduct() {
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setProductId("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        product2.setProductName("Sampo Cap Usep");
        product2.setProductQuantity(50);
        productRepository.create(product2);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product1.getProductId(), savedProduct.getProductId());
        savedProduct = productIterator.next();
        assertEquals(product2.getProductId(), savedProduct.getProductId());
        assertFalse(productIterator.hasNext());
    }

    @Nested
    class findByIdTests {
        @Test
        void testFindById() {
            Product product1 = new Product();
            product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
            product1.setProductName("Sampo Cap Bambang");
            product1.setProductQuantity(100);
            productRepository.create(product1);

            Product product2 = new Product();
            product2.setProductId("a0f9de46-90b1-437d-a0bf-d0821dde9096");
            product2.setProductName("Sampo Cap Usep");
            product2.setProductQuantity(50);
            productRepository.create(product2);

            Product savedProduct = productRepository.findById("eb558e9f-1c39-460e-8860-71af6af63bd6");
            assertEquals(product1.getProductId(), savedProduct.getProductId());
            assertEquals(product1.getProductName(), savedProduct.getProductName());
            assertEquals(product1.getProductQuantity(), savedProduct.getProductQuantity());

            savedProduct = productRepository.findById("a0f9de46-90b1-437d-a0bf-d0821dde9096");
            assertEquals(product2.getProductId(), savedProduct.getProductId());
            assertEquals(product2.getProductName(), savedProduct.getProductName());
            assertEquals(product2.getProductQuantity(), savedProduct.getProductQuantity());
        }
        @Test
        void testFindByIdNotFound() {
            Product product1 = new Product();
            product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
            product1.setProductName("Sampo Cap Bambang");
            product1.setProductQuantity(100);
            productRepository.create(product1);

            Product savedProduct = productRepository.findById("a0f9de46-90b1-437d-a0bf-d0821dde9096");
            assertNull(savedProduct);
        }
    }
    @Nested
    class editTests {
        @Test
        void testEdit() {
            Product product1 = new Product();
            product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
            product1.setProductName("Sampo Cap Bambang");
            product1.setProductQuantity(100);
            productRepository.create(product1);

            Product product2 = new Product();
            product2.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
            product2.setProductName("Sampo Cap Usep");
            product2.setProductQuantity(50);
            productRepository.edit(product2);

            Product savedProduct = productRepository.findById("eb558e9f-1c39-460e-8860-71af6af63bd6");
            assertEquals(product2.getProductId(), savedProduct.getProductId());
            assertEquals(product2.getProductName(), savedProduct.getProductName());
            assertEquals(product2.getProductQuantity(), savedProduct.getProductQuantity());
        }
        @Test
        void testEditNotFound() {
            Product product1 = new Product();
            product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
            product1.setProductName("Sampo Cap Bambang");
            product1.setProductQuantity(100);
            productRepository.create(product1);

            Product product2 = new Product();
            product2.setProductId("a0f9de46-90b1-437d-a0bf-d0821dde9096");
            product2.setProductName("Sampo Cap Usep");
            product2.setProductQuantity(50);
            productRepository.edit(product2);

            Product savedProduct = productRepository.findById("a0f9de46-90b1-437d-a0bf-d0821dde9096");
            assertNull(savedProduct);
        }
    }
    @Nested
    class deleteTests {
        @Test
        void testDelete() {
            Product product1 = new Product();
            product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
            product1.setProductName("Sampo Cap Bambang");
            product1.setProductQuantity(100);
            productRepository.create(product1);

            Product product2 = new Product();
            product2.setProductId("a0f9de46-90b1-437d-a0bf-d0821dde9096");
            product2.setProductName("Sampo Cap Usep");
            product2.setProductQuantity(50);
            productRepository.create(product2);

            productRepository.delete("eb558e9f-1c39-460e-8860-71af6af63bd6");
            Iterator<Product> productIterator = productRepository.findAll();
            assertTrue(productIterator.hasNext());
            Product savedProduct = productIterator.next();
            assertEquals(product2.getProductId(), savedProduct.getProductId());
            assertFalse(productIterator.hasNext());
        }
        @Test
        void testDeleteNotFound() {
            Product product1 = new Product();
            product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
            product1.setProductName("Sampo Cap Bambang");
            product1.setProductQuantity(100);
            productRepository.create(product1);

            Product product2 = new Product();
            product2.setProductId("a0f9de46-90b1-437d-a0bf-d0821dde9096");
            product2.setProductName("Sampo Cap Usep");
            product2.setProductQuantity(50);
            productRepository.create(product2);

            productRepository.delete("a0f9de46-90b1-437d-a0bf-d0821dde9096");
            Iterator<Product> productIterator = productRepository.findAll();
            assertTrue(productIterator.hasNext());
            Product savedProduct = productIterator.next();
            assertEquals(product1.getProductId(), savedProduct.getProductId());
            assertFalse(productIterator.hasNext());
        }
    }
}