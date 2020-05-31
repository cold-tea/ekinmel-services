package com.sandesh.ekinmelservices.repository;

import com.sandesh.ekinmelservices.model.BaseFilter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BaseFilterRepository extends JpaRepository<BaseFilter, String > {
}
