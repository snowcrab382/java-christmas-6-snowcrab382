package christmas.model;

import java.util.HashMap;
import java.util.Map;

public class BenefitCalculator {

    private static int TOTAL_PRICE = 0;
    private static int DISCOUNTED_TOTAL_PRICE = 0;

    private static int D_DAY_SALE_PRICE = 0;
    private static int WEEKEND_SALE_PRICE = 0;
    private static int WEEKDAY_SALE_PRICE = 0;
    private static int SPECIAL_SALE_PRICE = 0;

    private static String PRESENT = "없음";
    private static int PRESENT_PRICE = 0;

    private static Map<String, Integer> TOTAL_BENEFIT_RESULT = new HashMap<>();
    private static int TOTAL_BENEFIT_PRICE = 0;

    private static String EVENT_BADGE = "없음";

    private static UserOrder userOrder;

    public BenefitCalculator(UserOrder userOrder) {
        this.userOrder = userOrder;
        calculateTotalPrice();
        calculateDdaySalePrice();
        calculateWeekdaySale();
        calculateWeekendSale();
        calculateSpecialSalePrice();
        calculatePresentPrice();
        calculateTotalBenefit();
        calculateDiscountedTotalPrice();
        calculateEventBadge();
    }

    public Map<String, Integer> getReservationOrder() {
        return userOrder.getReservationOrder();
    }

    public int getReservationDate() {
        return userOrder.getReservationDate();
    }

    public int getTotalPrice() {
        return TOTAL_PRICE;
    }

    public String getPresent() {
        return PRESENT;
    }

    public Map<String, Integer> getBenefitResult() {
        return TOTAL_BENEFIT_RESULT;
    }

    public int getBenefitPrice() {
        return TOTAL_BENEFIT_PRICE;
    }

    public int getDiscountedTotalPrice() {
        return DISCOUNTED_TOTAL_PRICE;
    }

    public String getEventBadge() {
        return EVENT_BADGE;
    }

    private void calculateTotalPrice() {
        getReservationOrder().forEach((key, value) -> {
            TOTAL_PRICE += Menu.getPrice(key) * value;
        });
    }

    private void calculateDdaySalePrice() {
        int date = getReservationDate();
        if (date <= 25) {
            D_DAY_SALE_PRICE = 1000 + (date - 1) * 100;
        }
    }

    private void calculateWeekdaySale() {
        if (!isWeekend()) {
            getReservationOrder().forEach((key, value) -> {
                if (Menu.isDessert(key)) {
                    WEEKDAY_SALE_PRICE += value * 2023;
                }
            });
        }
    }

    private void calculateWeekendSale() {
        if (isWeekend()) {
            getReservationOrder().forEach((key, value) -> {
                if (Menu.isMainDish(key)) {
                    WEEKEND_SALE_PRICE += value * 2023;
                }
            });
        }
    }

    private void calculateSpecialSalePrice() {
        if (isSpecialDay()) {
            SPECIAL_SALE_PRICE += 1000;
        }
    }

    private void calculatePresentPrice() {
        if (isPresentAvailable()) {
            PRESENT = "샴페인 1개";
            PRESENT_PRICE = 25000;
        }
    }

    private void calculateTotalBenefit() {
        TOTAL_BENEFIT_RESULT.put("크리스마스 디데이 할인: ", D_DAY_SALE_PRICE);
        TOTAL_BENEFIT_RESULT.put("평일 할인: ", WEEKDAY_SALE_PRICE);
        TOTAL_BENEFIT_RESULT.put("주말 할인: ", WEEKEND_SALE_PRICE);
        TOTAL_BENEFIT_RESULT.put("특별 할인: ", SPECIAL_SALE_PRICE);
        TOTAL_BENEFIT_RESULT.put("증정 이벤트: ", PRESENT_PRICE);

        TOTAL_BENEFIT_RESULT.forEach((key, value) -> {
            TOTAL_BENEFIT_PRICE += value;
        });
    }

    private void calculateDiscountedTotalPrice() {
        DISCOUNTED_TOTAL_PRICE = TOTAL_PRICE - TOTAL_BENEFIT_PRICE + PRESENT_PRICE;
    }

    private void calculateEventBadge() {
        if (TOTAL_BENEFIT_PRICE > 5000) {
            EVENT_BADGE = "별";
        }
        if (TOTAL_BENEFIT_PRICE > 10000) {
            EVENT_BADGE = "트리";
        }
        if (TOTAL_BENEFIT_PRICE > 20000) {
            EVENT_BADGE = "산타";
        }
    }

    public boolean isBenefitAvailable() {
        return TOTAL_PRICE >= 10000;
    }

    private boolean isSpecialDay() {
        int date = getReservationDate();
        return date % 7 == 3 || date == 25;
    }

    private boolean isWeekend() {
        int date = getReservationDate();
        return date % 7 == 1 || date % 7 == 2;
    }

    private boolean isPresentAvailable() {
        return TOTAL_PRICE >= 120000;
    }
}
