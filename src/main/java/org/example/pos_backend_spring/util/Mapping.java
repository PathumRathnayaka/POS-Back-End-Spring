package org.example.pos_backend_spring.util;


import org.example.pos_backend_spring.dto.impl.CustomerDTO;
import org.example.pos_backend_spring.dto.impl.ItemDTO;
import org.example.pos_backend_spring.entity.impl.CustomerEntity;
import org.example.pos_backend_spring.entity.impl.ItemEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Mapping {
    @Autowired
    private ModelMapper modelMapper;
    //for user mapping
    public CustomerEntity toCustomerEntity(CustomerDTO customerDTO) {
        return modelMapper.map(customerDTO, CustomerEntity.class);
    }
    public CustomerDTO toCustomerDTO(CustomerEntity customerEntity) {
        return modelMapper.map(customerEntity, CustomerDTO.class);
    }
    public List<CustomerDTO> asUserDTOList(List<CustomerEntity> userEntities) {
        return modelMapper.map(userEntities, new TypeToken<List<CustomerDTO>>() {}.getType());
    }

    //for note mapping
    public ItemDTO toItemDTO(ItemEntity itemEntity) {
        return modelMapper.map(itemEntity, ItemDTO.class);
    }
    public ItemEntity toItemEntity(ItemDTO itemDTO) {
        return modelMapper.map(itemDTO, ItemEntity.class);
    }
    public List<ItemDTO> asNoteDTOList(List<ItemEntity> noteEntities) {
        return modelMapper.map(noteEntities, new TypeToken<List<ItemDTO>>() {}.getType());
    }
}
