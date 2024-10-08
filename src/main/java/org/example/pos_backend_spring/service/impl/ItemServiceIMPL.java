package org.example.pos_backend_spring.service.impl;

import jakarta.transaction.Transactional;

import org.example.pos_backend_spring.customerStatusCodes.SelectedErrorStatus;
import org.example.pos_backend_spring.dao.ItemDAO;
import org.example.pos_backend_spring.dto.ItemStatus;
import org.example.pos_backend_spring.dto.impl.ItemDTO;
import org.example.pos_backend_spring.entity.impl.ItemEntity;
import org.example.pos_backend_spring.exception.DataPersistException;
import org.example.pos_backend_spring.exception.ItemNotFoundException;
import org.example.pos_backend_spring.service.ItemService;
import org.example.pos_backend_spring.util.AppUtil;
import org.example.pos_backend_spring.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ItemServiceIMPL implements ItemService {
   @Autowired
   private ItemDAO itemDAO;
   @Autowired
   private Mapping itemMapping;

    @Override
    public void saveItem(ItemDTO itemDTO) {
        itemDTO.setCode(AppUtil.generateCustomerId());
        ItemEntity savedItem =
                itemDAO.save(itemMapping.toItemEntity(itemDTO));
        if(savedItem == null){
            throw new DataPersistException("Item not saved");
        }
    }

    @Override
    public List<ItemDTO> getAllItem() {
        return itemMapping.asItemDTOList(itemDAO.findAll());
    }

    @Override
    public ItemStatus getItem(String itemId) {
       if(itemDAO.existsById(itemId)){
           var selectedItem = itemDAO.getReferenceById(itemId);
           return itemMapping.toItemDTO(selectedItem);
       }else {
           return new SelectedErrorStatus(2,"Selected Item not found");
       }
    }

    @Override
    public void deleteItem(String itemId) {
        Optional<ItemEntity> foundItem = itemDAO.findById(itemId);
        if (!foundItem.isPresent()) {
            throw new ItemNotFoundException("Item not found");
        }else {
            itemDAO.deleteById(itemId);
        }
    }

    @Override
    public void updateItem(String itemId, ItemDTO itemDTO) {
        Optional<ItemEntity> findItem = itemDAO.findById(itemId);
        if (!findItem.isPresent()) {
            throw new ItemNotFoundException("Item not found");
        }else {
            findItem.get().setName(itemDTO.getName());
            findItem.get().setQty(Integer.parseInt(itemDTO.getQuantity()));
            findItem.get().setPrice(itemDTO.getPrice());
        }
    }
}
