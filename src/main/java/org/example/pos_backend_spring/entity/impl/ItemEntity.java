package org.example.pos_backend_spring.entity.impl;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.pos_backend_spring.entity.SuperEntity;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "item")
public class ItemEntity implements SuperEntity {
    @Id
    private String id;
    private String name;
    private double price;
    private int qty;
    @ManyToOne
    @JoinColumn(name = "custId",nullable = false)
    private CustomerEntity customer;
}
