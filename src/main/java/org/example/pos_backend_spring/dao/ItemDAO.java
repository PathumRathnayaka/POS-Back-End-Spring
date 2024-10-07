package org.example.pos_backend_spring.dao;

import org.example.pos_backend_spring.entity.impl.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemDAO extends JpaRepository<ItemEntity,String> {
}
