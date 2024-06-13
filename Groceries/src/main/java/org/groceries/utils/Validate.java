package org.groceries.utils;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.Locale;
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



    public static int getChoice(String mess, int min, int max) {
        Scanner sc = new Scanner(System.in);
        String input;
        while (true) {
            System.out.println(mess);
            input = sc.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("Empty!");
                continue;
            }
            try {
                int num = Integer.parseInt(input);
                if (num < min || num > max) {
                    System.out.println("Number not in range!");
                } else {
                    return num;
                }
            } catch (NumberFormatException ex) {
                System.out.println("Must be number");
            }
        }
    }

    public static String formatVietnameseCurrency(int amount) {
        // Tạo đối tượng NumberFormat cho Locale Vietnam
        NumberFormat formatter = NumberFormat.getInstance(new Locale("vi", "VN"));

        // Định dạng số
        String formattedAmount = formatter.format(amount);

        // Thêm đơn vị "₫" vào cuối
        return formattedAmount + " ₫";
    }

    public static String getInputDate() {
        Scanner scanner = new Scanner(System.in);
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String inputDate;
        LocalDate date = null;

        while (true) {
            System.out.print("Nhập ngày (theo định dạng YYYY-mm-dd): ");
            inputDate = scanner.nextLine();

            try {
                date = LocalDate.parse(inputDate, dateFormatter);
                break; // Nếu parse thành công, thoát khỏi vòng lặp
            } catch (Exception e) {
                System.out.println("Định dạng ngày không đúng. Vui lòng nhập lại.");
            }
        }

        scanner.close(); // Đóng Scanner sau khi không cần thiết nữa

        return dateFormatter.format(date); // Trả về ngày đã được định dạng đúng
    }

}
