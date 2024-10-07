package org.example.pos_backend_spring.service;



import org.example.pos_backend_spring.dto.OrderStatus;
import org.example.pos_backend_spring.dto.impl.OrderDTO;

import java.util.List;

public interface OrderService {
    void saveOrder(OrderDTO orderDTO);
    List<OrderDTO> getAllOrder();
    OrderStatus getOrder(String orderId);
    void deleteOrder(String orderId);
    void updateOrder(String orderId, OrderDTO orderDTO);
}
