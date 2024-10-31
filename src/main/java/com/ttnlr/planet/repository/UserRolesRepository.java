package com.ttnlr.planet.repository;

import com.ttnlr.planet.model.UserRole;
import org.springframework.data.repository.CrudRepository;

public interface UserRolesRepository extends CrudRepository<UserRole, Long> {
}
