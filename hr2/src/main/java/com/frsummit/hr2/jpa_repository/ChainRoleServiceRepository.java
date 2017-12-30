package com.frsummit.hr2.jpa_repository;

import com.frsummit.hr2.model.ChainRole;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChainRoleServiceRepository extends CrudRepository<ChainRole, Long> {
}
