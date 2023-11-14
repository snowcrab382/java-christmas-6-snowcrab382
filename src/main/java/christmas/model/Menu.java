package christmas.model;

public enum Menu {
    MUSHROOM_SOUP("양송이수프", 6_000, "APPETIZER"),
    TAPAS("타파스", 5_500, "APPETIZER"),
    CAESAR_SALAD("시저샐러드", 8_000, "APPETIZER"),

    T_BONE_STEAK("티본스테이크", 55_000, "MAIN_DISH"),
    BARBECUE_RIB("바비큐립", 54_000, "MAIN_DISH"),
    SEAFOOD_PASTA("해산물파스타", 35_000, "MAIN_DISH"),
    CHRISTMAS_PASTA("크리스마스파스타", 25_000, "MAIN_DISH"),

    CHOCOLATE_CAKE("초코케이크", 15_000, "DESSERT"),
    ICECREAM("아이스크림", 5_000, "DESSERT"),

    ZERO_COKE("제로콜라", 3_000, "BEVERAGE"),
    RED_WINE("레드와인", 60_000, "BEVERAGE"),
    CHAMPAGNE("샴페인", 25_000, "BEVERAGE");

    private final String foodNames;
    private final int price;
    private final String category;

    Menu(String foodNames, int price, String category) {
        this.foodNames = foodNames;
        this.price = price;
        this.category = category;
    }

    public static boolean isInMenu(String foodName) {
        for (Menu value : Menu.values()) {
            if (value.foodNames.equals(foodName)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isBeverage(String foodName) {
        for (Menu value : Menu.values()) {
            if (value.foodNames.equals(foodName)) {
                return value.category.equals("BEVERAGE");
            }
        }
        return false;
    }

    public static boolean isMainDish(String foodName) {
        for (Menu value : Menu.values()) {
            if (value.foodNames.equals(foodName)) {
                return value.category.equals("MAIN_DISH");
            }
        }
        return false;
    }

    public static boolean isDessert(String foodName) {
        for (Menu value : Menu.values()) {
            if (value.foodNames.equals(foodName)) {
                return value.category.equals("DESSERT");
            }
        }
        return false;
    }

    public static int getPrice(String foodName) {
        for (Menu value : Menu.values()) {
            if (value.foodNames.equals(foodName)) {
                return value.price;
            }
        }
        return 0;
    }

}
