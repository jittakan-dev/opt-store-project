package dev.jittakan.services;

import dev.jittakan.models.entities.Category;
import dev.jittakan.models.entities.Product;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

//@Alternative
public class ProductServiceListImpl implements ProductService {
    @Override
    public List<Product> listing() {
        return Arrays.asList(new Product(1L, "notebook", "computing", 175000),
                new Product(2L, "desk table", "office", 10000),
                new Product(3L, "mechanical keyboard", "computing", 40000));
    }

    @Override
    public Optional<Product> byId(Long id) {
        return listing().stream().filter(p -> p.getId().equals(id)).findAny();
    }

    @Override
    public void saveProduct(Product product) {

    }

    @Override
    public void eliminate(Long id) {

    }

    @Override
    public List<Category> listingCategory() {
        return null;
    }

    @Override
    public Optional<Category> byCategoryId(Long id) {
        return Optional.empty();
    }
}
