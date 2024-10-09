package org.example.springposnew.entity.impl;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.springposnew.entity.SuperEntity;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "item")
public class ItemEntity implements SuperEntity {
    @Id
    private String code;
    private String name;
    private double price;
    private int qty;

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<OrderItem> orderItems;
}
