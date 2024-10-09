package org.example.springposnew.dto.impl;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.springposnew.dto.CustomerStatus;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerDTO implements CustomerStatus {
     String custId;
     String name;
     String address;
     double salary;
}
