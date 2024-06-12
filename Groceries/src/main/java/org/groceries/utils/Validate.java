package org.groceries.utils;

import java.util.Scanner;
public class Validate {
    private static final Scanner SCANNER = new Scanner(System.in);
    public static int getInt(
            String messInfo,
            String messErrorRange,
            String messErrorInvalidate,
            int min, int max
    ) {
        do {
            try {
                System.out.print(messInfo);
                int number = Integer.parseInt(SCANNER.nextLine());
                if (number >= min && number <= max) {
                    return number;
                }
                System.out.println(messErrorRange);
            } catch (NumberFormatException e) {
                System.out.println(messErrorInvalidate);
            }

        } while (true);
    }
    public static String getString(
            String messageInfo,
            String messageErrorInvalid,
            final String REGEX) {
        do {
            System.out.print(messageInfo);
            String str = SCANNER.nextLine();
            if (str.matches(REGEX)) {
                return str;
            }
            System.out.println(messageErrorInvalid);
        } while (true);
    }
    public static String getDateString(
            String messageInfo,
            String messageErrorInvalid,
            final String REGEX) {
        do {
            System.out.print(messageInfo);
            String str = SCANNER.nextLine();
            if (str.matches(REGEX)) {
                return str;
            }
            System.out.println(messageErrorInvalid);
        } while (true);
    }

}
