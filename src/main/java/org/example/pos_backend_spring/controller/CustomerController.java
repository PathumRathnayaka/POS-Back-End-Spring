package org.example.pos_backend_spring.controller;


import org.example.pos_backend_spring.customerStatusCodes.SelectedErrorStatus;
import org.example.pos_backend_spring.dto.CustomerStatus;
import org.example.pos_backend_spring.dto.impl.CustomerDTO;
import org.example.pos_backend_spring.exception.CustomerNotFoundException;
import org.example.pos_backend_spring.exception.DataPersistException;
import org.example.pos_backend_spring.service.CustomerService;
import org.example.pos_backend_spring.util.AppUtil;
import org.example.pos_backend_spring.util.RegexProcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("api/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void>saveCustomer(
            @RequestPart ("name") String name,
            @RequestPart ("address") String address,
            @RequestPart ("salary") String salary
    ){
        try {
            String custId = AppUtil.generateCustomerId();
            CustomerDTO buildCustomerDTO= new CustomerDTO();
            buildCustomerDTO.setId(custId);
            buildCustomerDTO.setName(name);
            buildCustomerDTO.setAddress(address);
            buildCustomerDTO.setSalary(salary);
            customerService.saveCustomer(buildCustomerDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (DataPersistException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{custId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CustomerStatus getSelectedUser(@PathVariable ("custId") String custId){
        if(!RegexProcess.customerIdMatcher(custId)){
            return new SelectedErrorStatus(1,"Customer ID is not valid");
        }
        return customerService.getCustomer(custId);
    }

    @DeleteMapping(value = "/{custId}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable("custId") String custId){
        try {
            if(!RegexProcess.customerIdMatcher(custId)){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            customerService.deleteCustomer(custId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (CustomerNotFoundException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CustomerDTO> getAllCustomer(){
        return customerService.getAllCustomer();
    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping(value = "/{custId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void updateCustomer(
            @RequestPart ("name") String name,
            @RequestPart ("address") String address,
            @RequestPart ("salary") String salary,
            @PathVariable ("custId") String custId
    ){

        //Build the Object
        CustomerDTO buildCustomerDTO = new CustomerDTO();
        buildCustomerDTO.setId(custId);
        buildCustomerDTO.setName(name);
        buildCustomerDTO.setAddress(address);
        buildCustomerDTO.setSalary(salary);
        customerService.updateCustomer(custId,buildCustomerDTO);
    }

}
