package com.cms.repositories;

import com.cms.model.DBFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileJpaRepository extends JpaRepository<DBFile, String> {

}
