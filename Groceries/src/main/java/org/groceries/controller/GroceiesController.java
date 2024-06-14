package org.groceries.controller;

import org.groceries.DAO.ProductDAO;
import org.groceries.bo.InputGroceries.InputGroceries;
import org.groceries.bo.ManagerGroceries.ManageGroceries;
import org.groceries.constants.IConstants;
import org.groceries.entities.*;
import org.groceries.utils.Validate;
import java.util.List;
import java.sql.*;
import java.util.Scanner;

public class GroceiesController {
    private InputGroceries inputGroceries;
    private Validate validate;
    Scanner sc = new Scanner(System.in);
    private ManageGroceries manageGroceries;
    private ProductDAO productDAO;

    public GroceiesController() {
        this.validate = new Validate();
        this.productDAO = new ProductDAO();
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

    public void DisplayProduct() {
        List<Products> list = productDAO.getProduct();
        for (Products p : list) {
            System.out.println(p);
        }
    }
    public void AddProduct() throws Exception {
        Products p = new Products();
        int productId = validate.getInt("Enter Product ID: ", "Id can not less be than 0!", "Error!", 1, Integer.MAX_VALUE);
        if (productDAO.getProductByID(productId).isEmpty()) {
            String productName = validate.getString("Enter Product Name: ", "Name Error!", "[A-Za-z\\s]+");
            String productCategory = validate.getString("Enter Product category: ", "Category Error!", "[A-Za-z\\s]+");
            System.out.print("Enter Product Price: ");
            double productPrice = Double.parseDouble(sc.nextLine());
            int productStock = validate.getInt("Enter Product Stock: ", "Stock can not less be than 0!", "Error!", 1, Integer.MAX_VALUE);
            p = new Products(productId, productName, productCategory, productPrice, productStock);
            productDAO.addProduct(p);
            System.out.println("Add successfully!");
        } else {
            throw new Exception("Id is valid!");
        }
    }

    public void UpdateProduct() throws Exception {
        int productId = validate.getInt("Enter Product ID: ", "Id can not less be than 0!", "Error!", 1, Integer.MAX_VALUE);
        List<Products> p = productDAO.getProductByID(productId);
        if (p != null) {
            String productName = validate.getString("Enter Product Name: ", "Name Error!", "[A-Za-z\\s]+");
            String productCategory = validate.getString("Enter Product category: ", "Category Error!", "[A-Za-z\\s]+");
            System.out.print("Enter Product Price: ");
            double productPrice = Double.parseDouble(sc.nextLine());
            int productStock = validate.getInt("Enter Product Stock: ", "Stock can not less be than 0!", "Error!", 1, Integer.MAX_VALUE);
            Products product = new Products(productId, productName, productCategory, productPrice, productStock);
            productDAO.update(productId, product);
            System.out.println("Update successfully!");
        } else {
            throw new Exception("Id not valid!");
        }
    }

    public void DeleteProduct() throws Exception {
        int productId = validate.getInt("Enter Product ID: ", "Id can not less be than 0!", "Error!", 1, Integer.MAX_VALUE);
        if (productDAO.getProductByID(productId) != null) {
            productDAO.deleteProduct(productId);
            System.out.println("Delete successfully!");
        } else {
            throw new Exception("Id is valid!");
        }
    }
}
