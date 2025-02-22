package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;

import java.util.Iterator;


@Repository
public interface ProductRepository {
    public Product create(Product product);
    public Iterator<Product> findAll();
    public Product findById(String productId);
    public Product edit(Product product);
    public Product delete(String productId);
}
