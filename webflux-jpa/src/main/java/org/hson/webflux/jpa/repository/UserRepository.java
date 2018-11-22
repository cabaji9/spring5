package org.hson.webflux.jpa.repository;

import org.hson.webflux.jpa.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {

    User findUserByName(String name);
}
