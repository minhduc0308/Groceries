package org.groceries.bo.InputGroceries;

import org.groceries.entities.StatusType;
import org.groceries.utils.Validate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.sql.*;

public class InputGroceries {

    public int getInputIdOrder() {
        return Validate.getInt("Enter id of order: ", "Error input number in not range", "Invalid input number!", 1, Integer.MAX_VALUE );
    }

    public StatusType getInputStatusOrder() {
        int choiceStatus = Validate.getInt("1. Shipped\n 2. Cancelled\n 3. Processing\n 4. Completed\n", "Error input number in not range", "Invalid input number!", 1, Integer.MAX_VALUE );
        StatusType statusType = null;
        switch (choiceStatus){
            case 1:
                statusType = StatusType.Shipped;
                break;
            case 2:
                statusType = StatusType.Cancelled;
                break;
            case 3:
                statusType = StatusType.Processing;
                break;
            case 4:
                statusType = StatusType.Completed;
                break;
        }
        return statusType;
    }


    public Date getInputEndDate() {
        System.out.println("Enter end Date: ");
        Scanner scanner = new Scanner(System.in);
        String endDate = scanner.nextLine();
        return Date.valueOf(endDate);
    }

    public Date getInputStarDate() {
        System.out.println("Enter start Date: ");
        Scanner scanner = new Scanner(System.in);
        String dateString = scanner.nextLine();
        return Date.valueOf(dateString);
    }
}
