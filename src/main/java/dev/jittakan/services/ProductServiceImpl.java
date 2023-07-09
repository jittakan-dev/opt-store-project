package dev.jittakan.services;

import dev.jittakan.configs.ProductServicePrincipal;
import dev.jittakan.configs.Service;
import dev.jittakan.models.entities.Category;
import dev.jittakan.models.entities.Product;
import dev.jittakan.repositories.CrudRepository;
import dev.jittakan.repositories.RepositoryJpa;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Optional;

@Service
@ProductServicePrincipal
@Stateless
public class ProductServiceImpl implements ProductService {

    @Inject
    @RepositoryJpa
    private CrudRepository<Product> crudRepositoryJdbc;

    @Inject
    @RepositoryJpa
    private CrudRepository<Category> crudRepositoryCategoryJdbc;

    @Override
    public List<Product> listing() {
        try {
            return crudRepositoryJdbc.listing();
        } catch (Exception e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }

    }

    @Override
    public Optional<Product> byId(Long id) {
        try {
            return Optional.ofNullable(crudRepositoryJdbc.byId(id));
        } catch (Exception e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public void saveProduct(Product product) {
        try {
            crudRepositoryJdbc.save(product);
        } catch (Exception e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public void eliminate(Long id) {
        try {
            crudRepositoryJdbc.eliminate(id);
        } catch (Exception e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public List<Category> listingCategory() {
        try {
            return crudRepositoryCategoryJdbc.listing();
        } catch (Exception e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public Optional<Category> byCategoryId(Long id) {
        try {
            return Optional.ofNullable(crudRepositoryCategoryJdbc.byId(id));
        } catch (Exception e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }
}
