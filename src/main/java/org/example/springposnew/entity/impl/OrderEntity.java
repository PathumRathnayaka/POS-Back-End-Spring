package org.example.springposnew.entity.impl;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.springposnew.entity.SuperEntity;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "orders")
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

    @ManyToOne
    @JoinColumn(name = "custId", nullable = false)
    private CustomerEntity customer;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<OrderItem> orderItems;
}
