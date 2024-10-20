package org.example.springposnew.controller;


import org.example.springposnew.customerStatusCodes.SelectedErrorStatus;
import org.example.springposnew.dto.ItemStatus;
import org.example.springposnew.dto.impl.ItemDTO;
import org.example.springposnew.exception.DataPersistException;
import org.example.springposnew.exception.ItemNotFoundException;
import org.example.springposnew.service.ItemService;
import org.example.springposnew.util.RegexProcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/items")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveItem(@RequestBody ItemDTO itemDTO) {
        try {
            itemService.saveItem(itemDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (DataPersistException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(value = "/{itemID}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ItemStatus getSelectedNote(@PathVariable ("itemID") String itemId) {
        if (!RegexProcess.itemIdMatcher(itemId)) {
            return new SelectedErrorStatus(1, "Item ID is not valid");
        }
        return itemService.getItem(itemId);
    }
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ItemDTO> getALlItems(){
        return itemService.getAllItem();
    }
    @DeleteMapping(value = "/{itemId}")
    public ResponseEntity<Void> deleteItem(@PathVariable ("itemId") String itemId){
        try {
            if (!RegexProcess.itemIdMatcher(itemId)) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            itemService.deleteItem(itemId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (ItemNotFoundException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping(value = "/{itemId}")
    public ResponseEntity<Void> updateItem(@PathVariable ("itemId") String itemId,
                                           @RequestBody ItemDTO updateditemDTO){
        //validations
        try {
            if(!RegexProcess.itemIdMatcher(itemId) || updateditemDTO == null){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            itemService.updateItem(itemId,updateditemDTO);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (ItemNotFoundException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
