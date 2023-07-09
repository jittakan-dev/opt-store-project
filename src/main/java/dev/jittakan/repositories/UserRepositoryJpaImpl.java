package dev.jittakan.repositories;

import dev.jittakan.configs.Repository;
import dev.jittakan.models.entities.User;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

import java.util.List;

@RepositoryJpa
@Repository
public class UserRepositoryJpaImpl implements UserRepository {

    @Inject
    private EntityManager em;

    @Override
    public List<User> listing() throws Exception {
        return em.createQuery("from User", User.class).getResultList();
    }

    @Override
    public User byId(Long id) throws Exception {
        return em.find(User.class, id);
    }

    @Override
    public void save(User user) throws Exception {
        if(user.getId() != null && user.getId() > 0){
            em.merge(user);
        } else {
            em.persist(user);
        }
    }

    @Override
    public void eliminate(Long id) throws Exception {
        em.remove(byId(id));
    }

    @Override
    public User byUsername(String username) throws Exception {
        return em.createQuery("select u from User u where u.username = :username", User.class)
                .setParameter("username", username)
                .getSingleResult();
    }
}