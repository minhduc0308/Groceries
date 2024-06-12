package org.groceries.ui;

import org.groceries.controller.GroceiesController;
import org.groceries.utils.DisplayUtils;
import org.groceries.utils.Validate;

public class Main {
    public static void main(String[] args) throws Exception{
        GroceiesController groceiesController = new GroceiesController();
        while (true) {
            //display gui
            DisplayUtils.displayMenu();
            // user enter choice
            int choice = Validate.getChoice("Enter your choice", 1, 5);
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

                    break;
                case 9:
                    System.exit(0);
                    break;
            }
        }
    }
}
