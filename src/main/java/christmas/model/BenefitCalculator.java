package christmas.model;

import java.util.HashMap;
import java.util.Map;

public class BenefitCalculator {

    private static int TOTAL_PRICE = 0;
    private static int D_DAY_SALE_PRICE = 0;

    private static int WEEKEND_SALE_PRICE = 0;
    private static int WEEKDAY_SALE_PRICE = 0;

    private static int SPECIAL_SALE_PRICE = 0;

    private static String PRESENT = "없음";
    private static int PRESENT_PRICE = 0;


    private static Map<String, Integer> TOTAL_BENEFIT = new HashMap<>();
    private static int TOTAL_BENEFIT_PRICE = 0;

    private static UserOrder userOrder;

    public BenefitCalculator(UserOrder userOrder) {
        this.userOrder = userOrder;
    }

    public int getTotalPrice() {
        userOrder.getReservationOrder().forEach((key, value) -> {
            TOTAL_PRICE += Menu.getPrice(key) * value;
        });
        return TOTAL_PRICE;
    }

    public String getPresent() {
        if (isPresentAvailable()) {
            PRESENT = "샴페인 1개";
        }
        return PRESENT;
    }

    public Map<String, Integer> calculateTotalBenefit() {
        TOTAL_BENEFIT.put("크리스마스 디데이 할인: ", calculateDdaySalePrice());
        TOTAL_BENEFIT.put("평일 할인: ", calculateWeekdaySale());
        TOTAL_BENEFIT.put("주말 할인: ", calculateWeekendSale());
        TOTAL_BENEFIT.put("특별 할인: ", calculateSpecialSalePrice());
        TOTAL_BENEFIT.put("증정 이벤트: ", calculatePresentPrice());

        return TOTAL_BENEFIT;
    }

    private int calculateDdaySalePrice() {
        int date = userOrder.getReservationDate();
        if (date <= 25) {
            D_DAY_SALE_PRICE = 1000 + (date - 1) * 100;
        }
        return D_DAY_SALE_PRICE;
    }

    private int calculateSpecialSalePrice() {
        if (isSpecialDay()) {
            SPECIAL_SALE_PRICE += 1000;
        }
        return SPECIAL_SALE_PRICE;
    }

    private int calculateWeekdaySale() {
        if (!isWeekend()) {
            userOrder.getReservationOrder().forEach((key, value) -> {
                if (Menu.isDessert(key)) {
                    WEEKDAY_SALE_PRICE += value * 2023;
                }
            });
        }
        return WEEKDAY_SALE_PRICE;
    }

    private int calculateWeekendSale() {
        if (isWeekend()) {
            userOrder.getReservationOrder().forEach((key, value) -> {
                if (Menu.isMainDish(key)) {
                    WEEKEND_SALE_PRICE += value * 2023;
                }
            });
        }
        return WEEKEND_SALE_PRICE;
    }

    private int calculatePresentPrice() {
        if (isPresentAvailable()) {
            PRESENT_PRICE = 25000;
        }
        return PRESENT_PRICE;
    }

    private boolean isSpecialDay() {
        int date = userOrder.getReservationDate();
        return date % 7 == 3 || date == 25;
    }

    private boolean isWeekend() {
        int date = userOrder.getReservationDate();
        return date % 7 == 1 || date % 7 == 2;
    }

    public boolean isBenefitAvailable() {
        return TOTAL_PRICE >= 10000;
    }

    public boolean isPresentAvailable() {
        return TOTAL_PRICE >= 120000;
    }

    public Map<String, Integer> getReservationOrder() {
        return userOrder.getReservationOrder();
    }

    public int getReservationDate() {
        return userOrder.getReservationDate();
    }
}
