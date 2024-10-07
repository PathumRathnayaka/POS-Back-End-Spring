package org.example.pos_backend_spring.entity.impl;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.pos_backend_spring.entity.SuperEntity;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "order")
public class OrderEntity implements SuperEntity {

    @Id
    String orderId;
    Date orderDate;
    String cusId;
    String itemId;
    int orderQty;
    double total;
    double txtCash;
    double txtDiscount;

    @OneToMany(mappedBy = "order" ,cascade = CascadeType.ALL)
    private List<OrderItem> orderItems;
}
