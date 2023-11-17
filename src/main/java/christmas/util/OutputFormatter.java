package christmas.util;

public class OutputFormatter {

    public static String formatMoney(int money) {
        return String.format("%,d원", money);
    }
}
