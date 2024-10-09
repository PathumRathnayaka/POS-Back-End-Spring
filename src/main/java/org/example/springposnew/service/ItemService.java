package org.example.springposnew.service;

import org.example.springposnew.dto.ItemStatus;
import org.example.springposnew.dto.impl.ItemDTO;

import java.util.List;

public interface ItemService {
    void saveItem(ItemDTO itemDTO);
    List<ItemDTO> getAllItem();
    ItemStatus getItem(String itemId);
    void deleteItem(String itemId);
    void updateItem(String itemId, ItemDTO itemDTO);
}
