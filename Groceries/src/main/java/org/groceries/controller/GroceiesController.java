package org.groceries.controller;

import org.groceries.bo.InputGroceries.InputGroceries;
import org.groceries.bo.ManagerGroceries.ManageGroceries;
import org.groceries.constants.IConstants;
import org.groceries.entities.ResponseOrderDTO;
import org.groceries.entities.ResponseOrderDetailsDTO;
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
        inputGroceries = new InputGroceries();

        int id = inputGroceries.getInputIdOrder();
        StatusType statusType = inputGroceries.getInputStatusOrder();

        if(manageGroceries.updateStatusOrder(id, statusType)){
            return "Update sucessfully order with id: " + id;
        }else {
            return "Update failed order with id: " + id;
        }

    }

    public List<ResponseOrderDetailsDTO> displayOrderDetails() {
        inputGroceries = new InputGroceries();
        int id = inputGroceries.getInputIdOrder();
        return manageGroceries.getOrderDetailsById(id);
    }
}
