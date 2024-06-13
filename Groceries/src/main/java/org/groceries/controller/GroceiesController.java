package org.groceries.controller;

import org.groceries.bo.InputGroceries.InputGroceries;
import org.groceries.bo.ManagerGroceries.ManageGroceries;
import org.groceries.constants.IConstants;
import org.groceries.entities.ResponseOrderDTO;
import org.groceries.entities.StatusType;
import org.groceries.utils.Validate;

import java.util.List;

public class GroceiesController {
    private InputGroceries inputGroceries;
    private ManageGroceries manageGroceries;

    public GroceiesController() {
        manageGroceries = new ManageGroceries();
    }

    public List<ResponseOrderDTO> displayAllOrder() {
        return manageGroceries.getAllOrder();
    }

    public String updateStatusOrder() {
        int id = Validate.getInt("Enter id of order: ", "Error input number in not range", "Invalid input number!", 1, Integer.MAX_VALUE );
        int choiceStatus = Validate.getInt("1. Shipped\n 2. Cancelled\n 3. Processing\n 4. Completed\n", "Error input number in not range", "Invalid input number!", 1, Integer.MAX_VALUE );
        StatusType statusType = null;
        switch (choiceStatus){
            case 1:
                statusType = StatusType.Shipped;
                break;
            case 2:
                statusType = StatusType.Cancelled;
                break;
            case 3:
                statusType = StatusType.Processing;
                break;
            case 4:
                statusType = StatusType.Completed;
                break;
        }

        if(manageGroceries.updateStatusOrder(id, statusType)){
            return "Update sucessfully order with id: " + id;
        }else {
            return "Update failed order with id: " + id;
        }

    }
}
