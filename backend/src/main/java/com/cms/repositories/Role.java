package com.cms.repositories;

import com.cms.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Role extends JpaRepository<Role, Long> {
}
