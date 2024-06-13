package org.groceries.DAO;
import java.sql.*;
public class ProfitDAO extends DBUtils{
    private Connection connection;

    public ProfitDAO() throws SQLException, ClassNotFoundException {
        connection = DBUtils.getConnection();
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

}
