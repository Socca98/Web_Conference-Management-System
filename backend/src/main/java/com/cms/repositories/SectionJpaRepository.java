package com.cms.repositories;

import com.cms.model.Section;
import com.cms.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SectionJpaRepository extends JpaRepository<Section, Long> {
}
