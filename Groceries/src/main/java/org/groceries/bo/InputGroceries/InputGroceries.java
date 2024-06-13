package org.groceries.bo.InputGroceries;

import org.groceries.DAO.OrderDAO;
import org.groceries.entities.OrderProduct;
import org.groceries.entities.Products;
import org.groceries.entities.StatusType;
import org.groceries.utils.Validate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.sql.*;

public class InputGroceries {
    private List<Products> listProduct;
    private OrderProduct orderProduct;

    public InputGroceries() {
        listProduct = new ArrayList<>();
        orderProduct = new OrderProduct();
    }

    public int getInputIdOrder() {
        return Validate.getInt("Enter id of order: ", "Error input number in not range", "Invalid input number!", 1, Integer.MAX_VALUE );
    }

    public StatusType getInputStatusOrder() {
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
        return statusType;
    }


    public Date getInputEndDate() {
        System.out.println("Enter end Date: ");
        Scanner scanner = new Scanner(System.in);
        String endDate = scanner.nextLine();
        return Date.valueOf(endDate);
    }

    public Date getInputStarDate() {
        System.out.println("Enter start Date: ");
        Scanner scanner = new Scanner(System.in);
        String dateString = scanner.nextLine();
        return Date.valueOf(dateString);
    }

    public OrderProduct getInputAddNewOrder() {
        int customerId = Validate.getInt("Enter customerid:", "Invalid range of number", "Invalid input number", 1, Integer.MAX_VALUE);
        LocalDate date = Validate.getDateFromUser("Enter date (YYYY-dd-MM): ");
        int status = Validate.getInt("1. Shipped\n 2. Cancelled\n 3. Processing\n 4. Completed\n", "Error input number in not range", "Invalid input number!", 1, Integer.MAX_VALUE );
        StatusType statusType = null;
        switch (status){
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

        OrderDAO orderDAO = new OrderDAO();
        Scanner scanner = new Scanner(System.in);
        String continueInput;
        int quantity = 0;
        int idProduct;
        Products products = null;

        do {
            idProduct = Validate.getInt("Enter productId:", "Invalid range of number", "Invalid input number", 1, Integer.MAX_VALUE);
            products = orderDAO.findProductById(idProduct);

//            Products productWithId = new Products(products.getProductId(), products.getProductName(), products.getCategory(), products.getPrice(), products.getStock(), products.getCost());
            listProduct.add(products);

            quantity = Validate.getInt("Enter quantity:", "Invalid range of number", "Invalid input number", 1, Integer.MAX_VALUE);

            System.out.print("Do you want to enter another product ID? (yes/no): ");
            continueInput = scanner.nextLine().trim().toLowerCase();
        } while (continueInput.equals("yes"));

        orderProduct.setCustomerId(customerId);
        orderProduct.setOrderDate(date.toString());
        orderProduct.setListProduct(listProduct);
        orderProduct.setStatus(statusType.toString());
        orderProduct.setQuantity(quantity);

        return orderProduct;


    }
}
