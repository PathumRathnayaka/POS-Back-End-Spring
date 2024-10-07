package org.example.pos_backend_spring.service;

import org.example.pos_backend_spring.dto.ItemStatus;
import org.example.pos_backend_spring.dto.impl.ItemDTO;

import java.util.List;

public interface ItemService {
    void saveItem(ItemDTO itemDTO);
    List<ItemDTO> getAllItem();
    ItemStatus getItem(String itemId);
    void deleteItem(String itemId);
    void updateItem(String itemId, ItemDTO itemDTO);
}
