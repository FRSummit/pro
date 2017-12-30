package com.frsummit.hr2.jpa_repository;

import com.frsummit.hr2.model.Leaves;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeaveRepository extends JpaRepository<Leaves, Long> {
}
