package com.cms.repositories;

import com.cms.model.Conference;
import com.cms.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ReviewJpaRepository extends JpaRepository<Review, String> {
}
