package dev.jittakan.services;

import dev.jittakan.models.entities.Category;
import dev.jittakan.models.entities.Product;
import jakarta.ejb.Local;

import java.util.List;
import java.util.Optional;

@Local
public interface ProductService {
    List<Product> listing();
    Optional<Product> byId(Long id);

    void saveProduct(Product product);
    void eliminate(Long id);

    List<Category> listingCategory();
    Optional<Category> byCategoryId(Long id);
}
