package org.example.springposnew.service;



import org.example.springposnew.dto.OrderStatus;
import org.example.springposnew.dto.impl.OrderDTO;

import java.util.List;

public interface OrderService {
    void saveOrder(OrderDTO orderDTO);
    List<OrderDTO> getAllOrder();
    OrderStatus getOrder(String orderId);
    void deleteOrder(String orderId);
    void updateOrder(String orderId, OrderDTO orderDTO);
}
