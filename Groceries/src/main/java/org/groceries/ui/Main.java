package org.groceries.ui;

import org.groceries.controller.GroceiesController;
import org.groceries.entities.ResponseOrderDTO;
import org.groceries.entities.ResponseOrderDetailsDTO;
import org.groceries.utils.DisplayUtils;
import org.groceries.utils.Validate;

import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception{
        GroceiesController groceiesController = new GroceiesController();
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

                    break;
                case 7:

                    break;
                case 8:
                    ManageOrder();
                    break;
                case 9:
                    System.exit(0);
                    break;
            }
        }
    }

    private static void ManageOrder() {
        while (true){
            GroceiesController groceiesController = new GroceiesController();
            DisplayUtils.displayMenuManageOrder();
            int choiceMenuManagerOrder = Validate.getChoice("Enter your choice", 1, 4);
            switch (choiceMenuManagerOrder){
                case 1:
                    List<ResponseOrderDTO> listOrder = groceiesController.displayAllOrder();
                    for (ResponseOrderDTO o : listOrder){
                        System.out.println(o);
                    }
                    break;
                case 2:
                    List<ResponseOrderDetailsDTO> listOrderDetails = groceiesController.displayOrderDetails();
                    for (ResponseOrderDetailsDTO o : listOrderDetails){
                        System.out.println(o);
                    }
                    break;
                case 3:
                    String updateStatus = groceiesController.updateStatusOrder();
                    System.out.println(updateStatus);
                    break;
                case 4:
                    System.exit(0);
                    break;

            }
        }

    }
}
