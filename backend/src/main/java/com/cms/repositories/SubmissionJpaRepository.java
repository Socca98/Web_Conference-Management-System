package com.cms.repositories;

import com.cms.model.Submission;
import org.springframework.data.jpa.repository.JpaRepository;


public interface SubmissionJpaRepository extends JpaRepository<Submission, String> {
}
