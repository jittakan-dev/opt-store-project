package dev.jittakan.repositories;

import dev.jittakan.configs.Repository;
import dev.jittakan.models.entities.Product;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

import java.util.List;

@RepositoryJpa
@Repository
public class ProductRepositoryJpaImpl implements CrudRepository<Product> {

    @Inject
    private EntityManager em;

    @Override
    public List<Product> listing() throws Exception {
        return em.createQuery("select p from Product p left outer join fetch p.category", Product.class).getResultList();
    }

    @Override
    public Product byId(Long id) throws Exception {
        return em.find(Product.class, id);
    }

    @Override
    public void save(Product product) throws Exception {
        if(product.getId() != null && product.getId() > 0){
            em.merge(product);
        } else {
            em.persist(product);
        }
    }

    @Override
    public void eliminate(Long id) throws Exception {
        Product product = this.byId(id);
        em.remove(product);
    }
}
