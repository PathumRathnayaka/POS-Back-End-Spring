package org.example.springposnew.dto.impl;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.springposnew.dto.OrderStatus;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDTO implements OrderStatus {
    String orderId;
    Date orderDate;
    String cusIdOption;
    String itemIdOption;
    int orderQty;
    double total;
    double txtCash;
    double txtDiscount;
    private List<ItemDTO> items;

}
