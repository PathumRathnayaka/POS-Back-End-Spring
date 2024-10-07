package org.example.pos_backend_spring.dto.impl;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.pos_backend_spring.dto.ItemStatus;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ItemDTO implements ItemStatus {
    private String code;
    private String name;
    private String quantity;
    private Double price;
}
