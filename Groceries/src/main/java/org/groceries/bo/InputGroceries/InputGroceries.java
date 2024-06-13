package org.groceries.bo.InputGroceries;

import java.sql.Date;
import java.util.Scanner;

public class InputGroceries {
    private Scanner scanner;

    public InputGroceries() {
        this.scanner = new Scanner(System.in);
    }

    public Date inputDate(String prompt) {
        System.out.print(prompt);
        String dateString = scanner.nextLine();
        return Date.valueOf(dateString); // Assumes input format is YYYY-MM-DD
    }
}
