package com.sandesh.ekinmelservices.repository;

import com.sandesh.ekinmelservices.model.UserActionAudit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserActionAuditRepository extends JpaRepository<UserActionAudit, Integer> {
}
