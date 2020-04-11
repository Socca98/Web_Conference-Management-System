package com.cms.repositories;

import com.cms.model.Proposal;
import com.cms.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProposalJpaRepository extends JpaRepository<Proposal, Long> {
}
