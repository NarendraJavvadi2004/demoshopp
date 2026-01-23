package uiApi.utilities;
import java.util.Random;

public class RandomDataUtils {

    public static String randomText() {
        return "Auto" + System.currentTimeMillis();
    }

    public static String randomEmail() {
        return "auto" + System.currentTimeMillis() + "@gmail.com";
    }

    public static String randomZip() {
        Random random = new Random();
        return String.valueOf(500000 + random.nextInt(99999));
    }

    public static String randomPhone() {
        Random random = new Random();
        return "9" + (100000000 + random.nextInt(899999999));
    }
}
