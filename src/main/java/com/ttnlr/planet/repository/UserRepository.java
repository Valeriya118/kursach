package com.ttnlr.planet.repository;

import com.ttnlr.planet.model.User;
import org.springframework.data.repository.CrudRepository;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
