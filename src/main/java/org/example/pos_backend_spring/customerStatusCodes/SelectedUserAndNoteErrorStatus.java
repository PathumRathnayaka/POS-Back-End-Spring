package org.example.pos_backend_spring.customerStatusCodes;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.pos_backend_spring.dto.CustomerStatus;
import org.example.pos_backend_spring.dto.ItemStatus;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SelectedUserAndNoteErrorStatus implements CustomerStatus, ItemStatus {
    private int statusCode;
    private String statusMessage;
}
