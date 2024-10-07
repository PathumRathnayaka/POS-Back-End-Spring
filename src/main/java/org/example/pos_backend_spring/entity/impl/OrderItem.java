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
public class OrderItem implements SuperEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private OrderEntity order;

    @ManyToOne
    @JoinColumn(name = "item_id", nullable = false)
    private ItemEntity item;
}
