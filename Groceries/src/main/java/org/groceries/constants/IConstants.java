package org.groceries.constants;

public class IConstants {
    public static String REGEX_NAME = "^[A-Za-z ]+$";
    public static String REGEX_TEXT = "^[A-Za-z0-9 , \\.]+$";
    public static String REGEX_NUMBER = "^\\d+$";
    String UPDATE = "^[uU]$";
    String DELETE = "^[dD]$";
    String CREATE = "CREATE";
    String REGEX_UPDATE_DELETE = "^[uUdD]$";
    String EMAIL_REGEX = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

}
