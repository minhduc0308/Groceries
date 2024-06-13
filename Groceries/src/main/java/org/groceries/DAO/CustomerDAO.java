package org.groceries.DAO;

import org.groceries.entities.Customers;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO extends DBUtils{
    private Connection getConnect() throws SQLException {
        String url = "jdbc:sqlserver://localhost:1433;databaseName=ojt2";
        String user = "sa";
        String password = "1234";
        return DriverManager.getConnection(url, user, password);
    }
    public void addCustomer(String FirstName, String LastName, String Email, String Phone, String Address) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = getConnect();
            String sql = "INSERT INTO customers (FirstName, LastName, Email, Phone, Address) VALUES (?, ?, ?, ?, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, FirstName);
            pstmt.setString(2, LastName);
            pstmt.setString(3, Email);
            pstmt.setString(4, Phone);
            pstmt.setString(5, Address);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Thêm khách hàng thành công.");
            } else {
                System.out.println("Không thêm được khách hàng");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public List<Customers> getAllCustomers() {
        List<Customers> customers = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = getConnect();
            String sql = "select * from Customers";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                int CustomerID = rs.getInt("CustomerID");
                String FirstName = rs.getString("FirstName");
                String LastName = rs.getString("LastName");
                String Email = rs.getString("Email");
                String Phone = rs.getString("Phone");
                String Address = rs.getString("Address");
                Customers customer = new Customers(CustomerID, FirstName,LastName,Email,Phone,Address);
                customers.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return customers;
    }

    public void updateCustomer(String CustomerID, String FirstName, String LastName, String Email, String Phone, String Address) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = getConnect();
            String sql = "UPDATE customers SET FirstName = ?, LastName = ?, Email = ?, Phone = ?, Address = ? WHERE CustomerID = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, FirstName);
            pstmt.setString(2, LastName);
            pstmt.setString(3, Email);
            pstmt.setString(4, Phone);
            pstmt.setString(5, Address);
            pstmt.setInt(6, Integer.parseInt(CustomerID));
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Cập nhật thông tin của khách hàng thành công.");
            } else {
                System.out.println("Không có khách hàng nào được cập nhật. Id không hợp lệ hoặc không tìm thấy khách hàng.");
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi cập nhật thông tin của khách hàng:");
            e.printStackTrace();
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public void deleteCustomer(int CustomerID) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = getConnect();
            String sql = "DELETE FROM customers WHERE CustomerID = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, CustomerID);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Xóa khách hàng thành công.");
            } else {
                System.out.println("Không có khách hàng nào được xóa. Id không hợp lệ hoặc không tìm thấy khách hàng.");
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi xóa khách hàng:");
            e.printStackTrace();
        } finally {
            // Đóng tài nguyên
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        }
}
