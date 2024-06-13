package org.groceries.bo.ManagerGroceries;
import org.groceries.DAO.ProfitDAO;
import org.groceries.DAO.RevenueDAO;
import org.groceries.controller.GroceiesController;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ManageGroceries {
    private InputGroceries inputGroceries;
    public ManageGroceries(InputGroceries inputGroceries) {
        this.inputGroceries = inputGroceries;
    }

    public void handleRevenueReport(RevenueDAO revenueDAO, ProfitDAO profitDAO) {
        Date startDate = inputGroceries.inputDate("Enter start date (YYYY-MM-DD): ");
        Date endDate = inputGroceries.inputDate("Enter end date (YYYY-MM-DD): ");
        GroceiesController controller = new GroceiesController(revenueDAO, this, profitDAO);
        controller.displayRevenue(startDate, endDate);
    }
    public void handleProfitReport(RevenueDAO revenueDAO, ProfitDAO profitDAO) {
        Date startDate = inputGroceries.inputDate("Enter start date (YYYY-MM-DD): ");
        Date endDate = inputGroceries.inputDate("Enter end date (YYYY-MM-DD): ");
        GroceiesController controller = new GroceiesController(revenueDAO, this, profitDAO);
        controller.displayProfitByDate(startDate, endDate);
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
}
