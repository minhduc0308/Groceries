package org.groceries.DAO;

import java.math.BigDecimal;
import java.sql.Connection;
import org.groceries.entities.Products;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.groceries.entities.Products;

public class ProductDAO extends DBUtils {

    public List<Products> getProduct() {
        List<Products> products = new ArrayList<>();
        String xSql = "SELECT * FROM Products";

        try ( Connection conn = DBUtils.getConnection();  PreparedStatement ps = conn.prepareStatement(xSql);  ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int pid = rs.getInt("ProductID");
                String name = rs.getNString("ProductName");
                String category = rs.getNString("Category");
                double price = rs.getDouble("Price");
                int stock = rs.getInt("Stock");

                Products product = new Products(pid, name, category, price, stock, null);
                products.add(product);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return products;
    }

    public int addProduct(Products product) {
        int row = 0;
        String xSql = "insert into Products(ProductName,Category,Price,Stock) values (?,?,?,?);";
        try ( Connection conn = DBUtils.getConnection();  PreparedStatement ps = conn.prepareStatement(xSql)) {
            ps.setString(1, product.getProductName());
            ps.setString(2, product.getCategory());
            ps.setDouble(3, product.getPrice()); // Assuming product.getPrice() returns a double
            ps.setInt(4, product.getStock());
            row = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return row;
    }

    public int update(int pID, Products product) {
        int row = 0;
        String xSql = "UPDATE Products SET ProductName = ?, Category = ?, Price = ?, Stock = ? WHERE ProductID = ?;";
        try ( Connection conn = DBUtils.getConnection();  PreparedStatement ps = conn.prepareStatement(xSql)) {

            ps.setString(1, product.getProductName());
            ps.setString(2, product.getCategory());
            ps.setDouble(3, product.getPrice()); // Assuming product.getPrice() returns a double
            ps.setInt(4, product.getStock());
            ps.setInt(5, pID);

            row = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return row;
    }

    public int deleteProduct(int pID) {
        int row = 0;
        String xSql = "DELETE FROM OrderDetails\n"
                + "WHERE ProductID = ?;\n"
                + "DELETE FROM Products\n"
                + "WHERE ProductID = ?;\n"
                + "select * from OrderDetails";
        try ( Connection conn = DBUtils.getConnection();  PreparedStatement ps = conn.prepareStatement(xSql)) {

            ps.setInt(1, pID);
            ps.setInt(2, pID);

            row = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return row;
    }

    public List<Products> getProductByID(int pID) {
        List<Products> products = new ArrayList<>();
        String xSql = "SELECT * FROM Products WHERE ProductID = ?";

        try ( Connection conn = DBUtils.getConnection();  PreparedStatement ps = conn.prepareStatement(xSql)) {

            ps.setInt(1, pID); // Đặt giá trị cho tham số trước khi thực thi câu lệnh
            try ( ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int pid = rs.getInt("ProductID");
                    String name = rs.getNString("ProductName");
                    String category = rs.getNString("Category");
                    double price = rs.getDouble("Price");
                    int stock = rs.getInt("Stock");

                    Products product = new Products(pid, name, category, price, stock, null);
                    products.add(product);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return products;
    }


}