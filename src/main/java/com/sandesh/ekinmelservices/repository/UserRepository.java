package com.sandesh.ekinmelservices.repository;

import com.sandesh.ekinmelservices.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository  extends JpaRepository <User, String> {

    User findUserByUsername(String username);

    long countByUsername(String username);

    long countByEmail(String email);
}
