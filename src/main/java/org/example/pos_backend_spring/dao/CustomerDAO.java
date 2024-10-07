package org.example.pos_backend_spring.dao;

import org.example.pos_backend_spring.entity.impl.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerDAO extends JpaRepository<CustomerEntity,String> {
}
