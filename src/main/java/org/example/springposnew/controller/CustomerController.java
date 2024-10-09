package org.example.springposnew.controller;


import org.example.springposnew.customerStatusCodes.SelectedErrorStatus;
import org.example.springposnew.dto.CustomerStatus;
import org.example.springposnew.dto.impl.CustomerDTO;
import org.example.springposnew.exception.CustomerNotFoundException;
import org.example.springposnew.exception.DataPersistException;
import org.example.springposnew.service.CustomerService;
import org.example.springposnew.util.RegexProcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void>saveCustomer(@RequestBody CustomerDTO customerDTO){
        try {
            customerService.saveCustomer(customerDTO);
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
            @RequestPart ("salary") double salary,
            @PathVariable ("custId") String custId
    ){

        //Build the Object
        CustomerDTO buildCustomerDTO = new CustomerDTO();
        buildCustomerDTO.setCustId(custId);
        buildCustomerDTO.setName(name);
        buildCustomerDTO.setAddress(address);
        buildCustomerDTO.setSalary(salary);
        customerService.updateCustomer(custId,buildCustomerDTO);
    }

}
