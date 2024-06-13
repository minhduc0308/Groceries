package org.groceries.ui;

import org.groceries.DAO.DBUtils;
import org.groceries.DAO.ProfitDAO;
import org.groceries.DAO.RevenueDAO;
import org.groceries.bo.InputGroceries;
import org.groceries.bo.ManageGroceries;
import org.groceries.controller.GroceiesController;
import org.groceries.utils.DisplayUtils;
import org.groceries.utils.Validate;

import java.sql.Connection;
import java.sql.ResultSet;

public class Main {
    public static void main(String[] args) throws Exception{
        InputGroceries inputGroceries = new InputGroceries();
        Connection conn = DBUtils.getConnection();
        RevenueDAO revenueDAO = new RevenueDAO(conn);
        ProfitDAO priceDAO = new ProfitDAO(conn);
        ManageGroceries manageGroceries = new ManageGroceries(inputGroceries);
        GroceiesController groceiesController = new GroceiesController(revenueDAO, manageGroceries, priceDAO);
        while (true) {
            //display gui
            DisplayUtils.displayMenu();
            // user enter choice
            int choice = Validate.getChoice("Enter your choice", 1, 9);
            // case
            switch (choice) {
                case 1:
                    break;
                case 2:

                    break;
                case 3:

                    break;
                case 4:

                    break;
                case 5:

                    break;
                case 6:
                    manageGroceries.handleRevenueReport(revenueDAO, priceDAO);
                    break;
                case 7:
                    manageGroceries.handleProfitReport(revenueDAO, priceDAO);
                    break;
                case 8:
                    ResultSet rs = revenueDAO.getInventoryReport();
                    manageGroceries.displayReport(rs);
                    break;
                case 9:
                    System.exit(0);
                    break;
            }
        }
    }
}
