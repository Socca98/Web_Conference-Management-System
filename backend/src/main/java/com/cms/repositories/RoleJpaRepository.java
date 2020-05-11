package com.cms.repositories;

import com.cms.model.Role;
import com.cms.model.RoleKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleJpaRepository extends JpaRepository<Role, RoleKey> {
}
