package com.sandesh.ekinmelservices.repositories;

import com.sandesh.ekinmelservices.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository  extends JpaRepository <User, String> {

    User findUserByUsername(String username);

    long countByUsername(String username);

    long countByEmail(String email);
}
