package org.hson.app.repository;

import org.hson.app.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {

    User findUserByFirstName(String name);
}
