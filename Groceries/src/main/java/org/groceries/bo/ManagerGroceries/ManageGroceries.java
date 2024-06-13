package org.groceries.bo.ManagerGroceries;

import org.groceries.DAO.OrderDAO;
import org.groceries.DAO.ProfitDAO;
import org.groceries.DAO.RevenueDAO;
import org.groceries.controller.GroceiesController;
import org.groceries.entities.OrderProduct;
import org.groceries.entities.ResponseOrderDTO;
import org.groceries.entities.ResponseOrderDetailsDTO;
import org.groceries.entities.StatusType;
import org.groceries.utils.Validate;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ManageGroceries {
    private OrderDAO orderDAO;

    public ManageGroceries() {
        orderDAO = new OrderDAO();
    }

    public List<ResponseOrderDTO> getAllOrder() {

        return orderDAO.findAllOrder();
    }

    public boolean updateStatusOrder(int id, StatusType statusType) {
        if (orderDAO.updateStatusOrder(statusType.toString(), id)){
            return true;
        }
        return false;
    }

    public List<ResponseOrderDetailsDTO> getOrderDetailsById(int id) {
        return orderDAO.findOrderDetails(id);
    }

    public void displayRevenueReport(Date startDate, Date endDate) {
        RevenueDAO revenueDAO = null;
        try {
            revenueDAO = new RevenueDAO();

            double revenue = revenueDAO.getRevenue(startDate,endDate);

            String revenueString = Validate.formatVietnameseCurrency((int) revenue);

            System.out.println(revenueString);

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public void displayProfitReport(Date startDate, Date endDate) {
        ProfitDAO profitDAO = null;
        try {
            profitDAO = new ProfitDAO();

            double profit = profitDAO.getProfitByDate(startDate, endDate);

            String profitString = Validate.formatVietnameseCurrency((int) profit);

            System.out.println(profitString);

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void displayInventoryReport() {
        RevenueDAO revenueDAO = null;
        GroceiesController groceiesController = new GroceiesController();
        try {
            revenueDAO = new RevenueDAO();
            ResultSet rs = revenueDAO.getInventoryReport();
            groceiesController.displayReport(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean addOrder(OrderProduct orderProduct) {
        if(orderDAO.addOrderandOrderDetails(orderProduct.getCustomerId(), orderProduct.getOrderDate(),
                orderProduct.getStatus(), orderProduct.getListProduct(), orderProduct.getQuantity())){
            return true;
        }else {
            return false;
        }
    }
}
