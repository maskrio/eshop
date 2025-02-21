package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    @Test
    void testCreate() {
        Product product = new Product();
        product.setProductName("Product 1");
        product.setProductQuantity(100);

        Product createdProduct = productService.create(product);

        assertNotNull(createdProduct.getProductId(), "Product ID should not be null after creation");
    }

    @Test
    void testFindAll() {
        Product product1 = new Product("1", "Product 1", 100);
        Product product2 = new Product("2", "Product 2", 200);
        List<Product> productList = Arrays.asList(product1, product2);
        Iterator<Product> iterator = productList.iterator();

        when(productRepository.findAll()).thenReturn(iterator);

        List<Product> result = productService.findAll();
        assertEquals(2, result.size());
        assertTrue(result.contains(product1));
        assertTrue(result.contains(product2));
    }

    @Test
    void testFindById() {
        String productId = "1";
        Product product = new Product(productId, "Product 1", 100);

        when(productRepository.findById(productId)).thenReturn(product);

        Product result = productService.findById(productId);

        assertEquals(product, result);
    }

    @Test
    void testEdit() {
        Product product = new Product("1", "Product 1", 100);
        Product updatedProduct = new Product("1", "Product 1", 150);

        when(productRepository.edit(product)).thenReturn(updatedProduct);

        Product result = productService.edit(product);

        assertEquals(updatedProduct, result);
    }

    @Test
    void testDelete() {
        String productId = "1";
        Product product = new Product("1", "Product 1", 100);

        when(productRepository.delete(productId)).thenReturn(product);

        Product result = productService.delete(productId);

        assertEquals(product, result);
    }
}