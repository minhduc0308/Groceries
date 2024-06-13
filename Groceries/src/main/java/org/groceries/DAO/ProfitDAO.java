package org.groceries.DAO;

import java.sql.*;

public class ProfitDAO {
    private Connection connection;

    public ProfitDAO(Connection connection) {
        this.connection = connection;
    }

    public double getProfitByDate(Date startDate, Date endDate) {
        double totalProfit = 0;
        String sql = "EXEC CalculateProfitByDate ?, ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setDate(1, startDate);
            statement.setDate(2, endDate);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                totalProfit += resultSet.getDouble("Profit");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return totalProfit;
    }

        public static void main(String[] args) {
        try {
            Connection conn = DBUtils.getConnection();
            ProfitDAO profitDAO = new ProfitDAO(conn);

            // Thay thế với ngày bắt đầu và kết thúc mong muốn
            Date startDate = Date.valueOf("2024-06-01");
            Date endDate = Date.valueOf("2024-06-02");

            double profit = profitDAO.getProfitByDate(startDate, endDate);
            System.out.println("Lợi nhuận từ " + startDate + " đến " + endDate + " là: " + profit);

            conn.close();
        }catch (Exception e) {
            e.printStackTrace();
        }

    }
}
