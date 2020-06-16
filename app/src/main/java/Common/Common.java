package Common;

import Model.User;

public class Common {
    public static User currentUser;
    public static final String USER_KEY = "User";
    public static final String PWD_KEY = "Password";
    public static String convertCodeToStatus(String status) {
        if(status.equals("0"))
            return "Request sent";
        else if(status.equals("1"))
            return "On my way";
        else
            return "Donated";
    }
}
