package org.example.pos_backend_spring.controller;


import org.example.pos_backend_spring.dto.impl.CustomerDTO;
import org.example.pos_backend_spring.exception.DataPersistException;
import org.example.pos_backend_spring.service.CustomerService;
import org.example.pos_backend_spring.util.AppUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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

}
