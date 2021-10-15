package otp.util;

public class Utils {
    public static String generateString() {
        return java.util.UUID.randomUUID().toString().substring(0,7);
    }
}
