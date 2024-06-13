package org.groceries.DAO;

import java.sql.*;

public class RevenueDAO {
    private Connection conn;

    public RevenueDAO(Connection conn) {
        this.conn = conn;
    }

    public double getRevenue(Date startDate, Date endDate) {
       double totalRevenue = 0;
       String sql = "EXEC sp_ReportRevenue ?, ?";
       try (PreparedStatement ps = conn.prepareStatement(sql)){
          ps.setDate(1, startDate);
          ps.setDate(2, endDate);
          ResultSet rs = ps.executeQuery();
          if(rs.next()) {
              totalRevenue = rs.getDouble("TotalRevenue");
          }
       }catch (SQLException e){
           e.printStackTrace();
       }
       return totalRevenue;
    }
    public ResultSet getInventoryReport() throws SQLException {
        Statement statement = conn.createStatement();
        return statement.executeQuery("EXEC GetInventoryReport");
    }
}
