package org.hson.helloWorld.repository;

import org.hson.helloWorld.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {

    User findUserByName(String name);
}
