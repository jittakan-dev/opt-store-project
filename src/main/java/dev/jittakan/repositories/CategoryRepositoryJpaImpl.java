package dev.jittakan.repositories;

import dev.jittakan.configs.Repository;
import dev.jittakan.models.entities.Category;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

import java.util.List;

@RepositoryJpa
@Repository
public class CategoryRepositoryJpaImpl implements CrudRepository<Category> {

    @Inject
    private EntityManager em;

    @Override
    public List<Category> listing() throws Exception {
        return em.createQuery("from Category", Category.class).getResultList();
    }

    @Override
    public Category byId(Long id) throws Exception {
        return em.find(Category.class, id);
    }

    @Override
    public void save(Category category) throws Exception {
        if(category.getId() != null && category.getId() > 0){
            em.merge(category);
        } else {
            em.persist(category);
        }
    }

    @Override
    public void eliminate(Long id) throws Exception {
        em.remove(byId(id));
    }
}
