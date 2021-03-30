package com.sandesh.ekinmelservices.service;

import com.sandesh.ekinmelservices.model.UserActionAudit;
import com.sandesh.ekinmelservices.repository.UserActionAuditRepository;
import org.springframework.stereotype.Service;

@Service
public class UserActionAuditService {

    private final UserActionAuditRepository repository;

    public UserActionAuditService(UserActionAuditRepository repository) {
        this.repository = repository;
    }

    public UserActionAudit saveUserActionAudit(UserActionAudit userActionAudit) {
        return this.repository.save(userActionAudit);
    }
}
