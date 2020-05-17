package com.cms.repositories;

import com.cms.model.Invitation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvitationJpaRepository extends JpaRepository<Invitation, String> {
}
