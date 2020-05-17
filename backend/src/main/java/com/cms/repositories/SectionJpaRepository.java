package com.cms.repositories;

import com.cms.model.Conference;
import com.cms.model.Section;
import com.cms.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface SectionJpaRepository extends JpaRepository<Section, String> {
    List<Section> findAllByConference(Conference conference);
}
