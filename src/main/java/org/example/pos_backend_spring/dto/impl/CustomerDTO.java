package org.example.pos_backend_spring.dto.impl;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.pos_backend_spring.dto.CustomerStatus;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerDTO implements CustomerStatus {
    private String id;
    private String name;
    private String address;
    private String salary;
}
