package org.example.pos_backend_spring.service;

import org.example.pos_backend_spring.dto.CustomerStatus;
import org.example.pos_backend_spring.dto.impl.CustomerDTO;

import java.util.List;

public interface CustomerService {
    void saveCustomer(CustomerDTO customerDTO);
    List<CustomerDTO> getAllCustomer();
    CustomerStatus getCustomer(String customerId);
    void deleteCustomer(String customerId);
    void updateCustomer(String customerId, CustomerDTO customerDTO);
}
