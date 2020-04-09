package com.cms.repositories;

import com.cms.model.Conference;
import com.cms.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConferenceJpaRepository extends JpaRepository<Conference, Long> {
}
