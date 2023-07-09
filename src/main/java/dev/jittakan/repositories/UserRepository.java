package dev.jittakan.repositories;

import dev.jittakan.models.entities.User;

public interface UserRepository extends CrudRepository<User> {
    User byUsername(String username) throws Exception;
}
