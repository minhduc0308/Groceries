package org.groceries.controller;

import org.groceries.bo.InputGroceries.InputGroceries;
import org.groceries.bo.ManagerGroceries.ManageGroceries;
import org.groceries.constants.IConstants;
import org.groceries.entities.OrderProduct;
import org.groceries.entities.ResponseOrderDTO;
import org.groceries.entities.ResponseOrderDetailsDTO;
import org.groceries.entities.StatusType;
import org.groceries.utils.Validate;
import java.util.List;
import java.sql.*;

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

    public void handleRevenueReport() {
        inputGroceries = new InputGroceries();
        Date startDate = inputGroceries.getInputStarDate();
        Date endDate = inputGroceries.getInputEndDate();

        manageGroceries.displayRevenueReport(startDate, endDate);


    }

    public void handleProfitReport() {

        inputGroceries = new InputGroceries();
        Date startDate = inputGroceries.getInputStarDate();
        Date endDate = inputGroceries.getInputEndDate();

        manageGroceries.displayProfitReport(startDate, endDate);

    }
    public void displayReport(ResultSet rs) throws SQLException {
        while (rs.next()) {
            int productId = rs.getInt("ProductID");
            String productName = rs.getString("ProductName");
            String category = rs.getString("Category");
            int stock = rs.getInt("Stock");
            System.out.println("Product ID: " + productId + ", Product Name: " + productName + ", Category: " + category + ", Stock: " + stock);
        }
    }

    public String addOrder() {
        inputGroceries = new InputGroceries();
        OrderProduct orderProduct = inputGroceries.getInputAddNewOrder();
        if(manageGroceries.addOrder(orderProduct)){
            return "add new order succesfully!";
        }else{
            return "add new order failed!";
        }
    }
}
