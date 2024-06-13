package org.groceries.bo.ManagerGroceries;

import org.groceries.DAO.OrderDAO;
import org.groceries.entities.ResponseOrderDTO;
import org.groceries.entities.ResponseOrderDetailsDTO;
import org.groceries.entities.StatusType;

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
}
