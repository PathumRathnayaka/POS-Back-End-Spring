package org.example.pos_backend_spring.service.impl;

import jakarta.transaction.Transactional;

import org.example.pos_backend_spring.customerStatusCodes.SelectedErrorStatus;
import org.example.pos_backend_spring.dao.CustomerDAO;
import org.example.pos_backend_spring.dto.CustomerStatus;
import org.example.pos_backend_spring.dto.impl.CustomerDTO;
import org.example.pos_backend_spring.entity.impl.CustomerEntity;
import org.example.pos_backend_spring.exception.CustomerNotFoundException;
import org.example.pos_backend_spring.exception.DataPersistException;
import org.example.pos_backend_spring.service.CustomerService;
import org.example.pos_backend_spring.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CustomerServiceIMPL implements CustomerService {
    @Autowired
    private CustomerDAO customerDAO;
    @Autowired
    private Mapping mapping;
    @Override
    public void saveCustomer(CustomerDTO customerDTO) {
        CustomerEntity savedCustomer =
                customerDAO.save(mapping.toCustomerEntity(customerDTO));
        if (savedCustomer == null) {
            throw new DataPersistException("Customer not saved");
        }
    }
    @Override
    public List<CustomerDTO> getAllCustomer() {
        List<CustomerEntity> allCustomers = customerDAO.findAll();
        return mapping.asCustomerDTOList(allCustomers);
    }

    @Override
    public CustomerStatus getCustomer(String custId) {
        if(customerDAO.existsById(custId)){
            CustomerEntity selectedCustomer = customerDAO.getReferenceById(custId);
            return mapping.toCustomerDTO(selectedCustomer);
        }else {
            return new SelectedErrorStatus(2, "User with id " + custId + " not found");
        }
    }

    @Override
    public void deleteCustomer(String custId) {
        Optional<CustomerEntity> existedCustomer = customerDAO.findById(custId);
        if(!existedCustomer.isPresent()){
            throw new CustomerNotFoundException("User with id " + custId + " not found");
        }else {
            customerDAO.deleteById(custId);
        }
    }

    @Override
    public void updateCustomer(String custId, CustomerDTO customerDTO) {
        Optional<CustomerEntity> tmpCustomer = customerDAO.findById(custId);
        if(tmpCustomer.isPresent()) {
            tmpCustomer.get().setCustName(customerDTO.getName());
            tmpCustomer.get().setAddress(customerDTO.getAddress());
            tmpCustomer.get().setSalary(Double.parseDouble(customerDTO.getSalary()));
        }
    }
}
