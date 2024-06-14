package org.groceries.ui;

import org.groceries.DAO.CustomerDAO;

import org.groceries.DAO.RevenueDAO;
import org.groceries.controller.GroceiesController;
import org.groceries.entities.Customers;

import org.groceries.entities.ResponseOrderDTO;
import org.groceries.entities.ResponseOrderDetailsDTO;
import org.groceries.utils.DisplayUtils;
import org.groceries.utils.Validate;

import java.sql.ResultSet;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception{
        GroceiesController groceiesController = new GroceiesController();
        RevenueDAO revenueDAO = new RevenueDAO();
        while (true) {
            //display gui
            DisplayUtils.displayMenu();
            // user enter choice
            int choice = Validate.getChoice("Enter your choice", 1, 9);
            // case
            switch (choice) {
                case 1:
                    manageCustomer();
                    break;
                case 2:
                    System.out.println("========= List Product =========");
                    groceiesController.DisplayProduct();
                    break;
                case 3:
                    System.out.println("=========== Add Product ==========");
                    groceiesController.AddProduct();
                    break;
                case 4:
                    System.out.println("=========== Update Product =========");
                    groceiesController.UpdateProduct();
                    break;
                case 5:
                    ResultSet rs = revenueDAO.getInventoryReport();
                    groceiesController.displayReport(rs);
                    break;
                case 6:
                    groceiesController.handleProfitReport();
                    break;
                case 7:
                    groceiesController.handleRevenueReport();
                    break;
                case 8:
                    ManageOrder();
                    break;
                case 9:
//                    addInvoice(invoiceDAO);
                    break;
                case 10:
//                    listAllInvoices(invoiceDAO);
                    break;

                case 11:
//                    deleteInvoice(invoiceDAO);
                    break;
                case 12:
                    System.exit(0);
                    break;
            }
        }
    }

    private static void manageCustomer(){
        CustomerDAO customerDAO = new CustomerDAO();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Chọn thao tác:");
            System.out.println("1. Thêm khách hàng");
            System.out.println("2. Hiển thị danh sách khách hàng");
            System.out.println("3. Sửa thông tin khách hàng");
            System.out.println("4. Xóa khách hàng");
            System.out.println("5. Thoát");

            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    System.out.print("Nhập First Name: ");
                    String fname = scanner.nextLine();
                    System.out.print("Nhập Last Name: ");
                    String lname = scanner.nextLine();
                    System.out.print("Nhập Email: ");
                    String email = scanner.nextLine();
                    System.out.println("Nhập số điện thoại: ");
                    String phone = scanner.nextLine();
                    System.out.println("Nhập địa chỉ: ");
                    String address = scanner.nextLine();
                    customerDAO.addCustomer(fname, lname, email, phone, address);
                    break;
                case 2:
                    List<Customers> customers = customerDAO.getAllCustomers();
                    for (Customers customer : customers) {
                        System.out.println(customer);
                    }
                    break;
                case 3:
                    System.out.print("Nhập ID khách hàng cần sửa: ");
                    String idUpdate = scanner.nextLine();
                    System.out.print("Nhập First Name mới: ");
                    String newFName = scanner.nextLine();
                    System.out.print("Nhập Last Name mới: ");
                    String newLName = scanner.nextLine();
                    System.out.print("Nhập email mới: ");
                    String newEmail = scanner.nextLine();
                    System.out.println("Nhập số điện thoại mới: ");
                    String newPhone = scanner.nextLine();
                    System.out.println("Nhập địa chỉ mới: ");
                    String newAddress = scanner.nextLine();
                    customerDAO.updateCustomer(idUpdate, newFName, newLName, newEmail, newPhone, newAddress);
                    break;
                case 4:
                    System.out.print("Nhập ID khách hàng cần xóa: ");
                    int idDelete = scanner.nextInt();
                    customerDAO.deleteCustomer(idDelete);
                    break;
                case 5:
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
            }
        }
    }

    private static void ManageOrder() {
        while (true){
            GroceiesController groceiesController = new GroceiesController();
            DisplayUtils.displayMenuManageOrder();
            int choiceMenuManagerOrder = Validate.getChoice("Enter your choice", 1, 4);
            switch (choiceMenuManagerOrder){
                case 1:
                    List<ResponseOrderDTO> listOrder = groceiesController.displayAllOrder();
                    for (ResponseOrderDTO o : listOrder){
                        System.out.println(o);
                    }
                    break;
                case 2:
                    List<ResponseOrderDetailsDTO> listOrderDetails = groceiesController.displayOrderDetails();
                    for (ResponseOrderDetailsDTO o : listOrderDetails){
                        System.out.println(o);
                    }
                    break;
                case 3:
                    String updateStatus = groceiesController.updateStatusOrder();
                    System.out.println(updateStatus);
                    break;
                case 4:
                    System.out.println(groceiesController.addOrder());
                    break;
                case 5:
                    System.exit(0);
                    break;
            }
        }

    }

}
