package com.cms.repositories;

import com.cms.model.Conference;
import com.cms.model.Submission;
import com.cms.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface SubmissionJpaRepository extends JpaRepository<Submission, String> {
}
