package org.groceries.DAO;
import org.groceries.entities.ProductRequestDTO;
import org.groceries.entities.Products;
import org.groceries.entities.ResponseOrderDTO;
import org.groceries.entities.ResponseOrderDetailsDTO;
import org.groceries.utils.Validate;

import javax.swing.*;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderDAO extends DBUtils{
    private PreparedStatement ps;
    private ResultSet rs;
    private List<ResponseOrderDTO> listOrder;
    private List<ResponseOrderDetailsDTO> lisrOrderDetails;

    public OrderDAO() {
        listOrder = new ArrayList<>();
        lisrOrderDetails = new ArrayList<>();

    }

    public boolean addOrderandOrderDetails(int customerID, String orderDate, String status, List<ProductRequestDTO> listProduct, int quantity){
        String sql = "INSERT INTO [dbo].[Orders]\n" +
                "           ([CustomerID]\n" +
                "           ,[OrderDate]\n" +
                "           ,[Status])\n" +
                "     VALUES\n" +
                "           (?, ?, ?)";

        String sqlInsertOrderDetails = "\n" +
                "INSERT INTO [dbo].[OrderDetails]\n" +
                "           ([OrderID]\n" +
                "           ,[ProductID]\n" +
                "           ,[Quantity]\n" +
                "           ,[Price])\n" +
                "     VALUES\n" +
                "           (?, ?, ?, ?)";

        String msg = "";

        try (Connection connection = DBUtils.getConnection()) {
            ps = connection.prepareStatement(sql, ps.RETURN_GENERATED_KEYS);
            ps.setInt(1, customerID);
            ps.setString(2, orderDate);
            ps.setString(3, status);

            int rowAffected = ps.executeUpdate();
            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int orderId = generatedKeys.getInt(1);
                    for (ProductRequestDTO o : listProduct){
                        ps = connection.prepareStatement(sqlInsertOrderDetails);
                        ps.setInt(1, orderId);
                        ps.setInt(2, o.getProductId());
                        ps.setInt(3, quantity);
                        ps.setInt(4, o.getPrice());

                        int rowOrderDetailsAfected = ps.executeUpdate();
                        if (rowOrderDetailsAfected > 0){
                            msg = "add order details successfully";
                        }else {
                            msg = "add order details failed";
                        }

                    }

                } else {
                    throw new SQLException("Tạo đơn hàng thất bại, không có ID nào được lấy.");
                }
            }

            if(rowAffected > 0){
                return true;
            }

        }catch (SQLException e){
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public boolean updateStatusOrder(String status, int orderId){
        String sql = "UPDATE [dbo].[Orders]\n" +
                "SET [Status] = ?\n" +
                "WHERE [OrderID] = ?";

        try (Connection connection = DBUtils.getConnection()){
            ps = connection.prepareStatement(sql);
            ps.setString(1, status);
            ps.setInt(2, orderId);

            int rowAfected = ps.executeUpdate();

            if(rowAfected > 0){
                return true;
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public List<ResponseOrderDTO> findAllOrder(){
        String sql = "select o.OrderID, c.FirstName, c.LastName, c.Email, c.Phone, c.[Address], o.OrderDate, o.[Status] from Orders o\n" +
                "join Customers c\n" +
                "on  o.CustomerID = c.CustomerID";
        try (Connection connection = DBUtils.getConnection()) {

            ps = connection.prepareStatement(sql);

            rs = ps.executeQuery();

            while (rs.next()){
                int id = rs.getInt(1);
                String firstName = rs.getString(2);
                String lastName = rs.getString(3);
                String email = rs.getString(4);
                String phone = rs.getString(5);
                String address = rs.getString(6);
                Date orderDate = rs.getDate(7);
                String status = rs.getString(8);

                ResponseOrderDTO responseOrderDTO = new ResponseOrderDTO(id, firstName, lastName, email, phone, address,orderDate, status );

                listOrder.add(responseOrderDTO);


            }

        }catch (SQLException | ClassNotFoundException e){
            System.out.println(e.getMessage());
        }

        return listOrder;
    }

    public List<ResponseOrderDetailsDTO> findOrderDetails(int orderIdInput){
        String sql = "select o.OrderDetailID, o.OrderID, p.ProductName, p.Category, p.Price, o.Quantity from OrderDetails o\n" +
                "join Products p\n" +
                "on o.ProductID = p.ProductID\n" +
                "where o.OrderID = ?";

        try (Connection connection = DBUtils.getConnection()) {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, orderIdInput);
            rs = ps.executeQuery();

            while (rs.next()){
                int id = rs.getInt(1);
                int orderId = rs.getInt(2);
                String productName = rs.getString(3);
                String category = rs.getString(4);
                int price = rs.getInt(5);
                int quantity = rs.getInt(6);

                ResponseOrderDetailsDTO orderDetails = new ResponseOrderDetailsDTO(id, orderId, productName, category, Validate.formatVietnameseCurrency(price), quantity);

                lisrOrderDetails.add(orderDetails);
            }

        }catch (SQLException | ClassNotFoundException e){
            System.out.println(e.getMessage());
        }
        return lisrOrderDetails;
    }


    public static void main(String[] args) {
        OrderDAO orderDAO = new OrderDAO();
//        List<Products> products = new ArrayList<>();
//        products.add(new Products(1, "Laptop", "Electronics", 1000, 50, new BigDecimal("800.00")));
//        products.add(new Products(2, "Smartphone", "Electronics", 700, 100, new BigDecimal("500.00")));
//
//        orderDAO.addOrderandOrderDetails(1, "2022-02-02", "1", products , 1);


        List<ResponseOrderDetailsDTO> list = orderDAO.findOrderDetails(1);

        for (ResponseOrderDetailsDTO o : list){
            System.out.println(o);
        }
    }

    public ProductRequestDTO findProductById(int idProduct) {
        String sql = "select * from Products p\n" +
                "where p.ProductID = ?";

        ProductRequestDTO products = null;
        try  (Connection connection = DBUtils.getConnection()) {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, idProduct);

            rs = ps.executeQuery();

            if (rs.next()){
                int id = rs.getInt(1);
                String name = rs.getString(2);
                String category = rs.getString(3);
                int price = rs.getInt(4);
                int stock = rs.getInt(5);
                BigDecimal cost = rs.getBigDecimal(6);

                products = new ProductRequestDTO(id, name, category, price, stock, cost);


            }

        }catch (SQLException e){
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return products;
    }
}
