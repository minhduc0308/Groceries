package org.groceries.controller;

import org.groceries.DAO.ProfitDAO;
import org.groceries.DAO.RevenueDAO;
import org.groceries.bo.ManageGroceries;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.Locale;

public class GroceiesController {
    private RevenueDAO revenueDAO;
    private ProfitDAO profitDAO;

    private ManageGroceries manageGroceries;

    public GroceiesController(RevenueDAO revenueDAO, ManageGroceries manageGroceries, ProfitDAO profitDAO) {
        this.revenueDAO = revenueDAO;
        this.manageGroceries = manageGroceries;
        this.profitDAO = profitDAO;
    }
    public static String formatVietnameseCurrency(int amount) {
        // Tạo đối tượng NumberFormat cho Locale Vietnam
        NumberFormat formatter = NumberFormat.getInstance(new Locale("vi", "VN"));

        // Định dạng số
        String formattedAmount = formatter.format(amount);

        // Thêm đơn vị "₫" vào cuối
        return formattedAmount + " ₫";
    }

    public void displayRevenue(Date startDate, Date endDate) {
        double revenue = revenueDAO.getRevenue(startDate, endDate);
        String formattedRevenue = formatVietnameseCurrency((int) revenue);
        System.out.println("Total Revenue from " + startDate + " to " + endDate + " is: " + formattedRevenue);
    }
    public void displayProfitByDate(Date startDate, Date endDate) {
        double totalProfit = profitDAO.getProfitByDate(startDate, endDate);
        String formattedProfit = formatVietnameseCurrency((int) totalProfit);
        System.out.println("Tổng lợi nhuận từ " + startDate + " đến " + endDate + " là: " + formattedProfit);
    }

    public void displayInventoryReport() {
        try {
            ResultSet rs = revenueDAO.getInventoryReport();
            manageGroceries.displayReport(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
