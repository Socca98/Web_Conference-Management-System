package com.cms.repositories;

import com.cms.model.User;
import com.cms.model.UserProposals;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserProposalJpaRepository extends JpaRepository<UserProposals, Long> {
}
