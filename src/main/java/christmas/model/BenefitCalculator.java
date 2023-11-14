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
        calculateTotalPrice();
        calculateDdaySalePrice();
        calculateWeekdaySale();
        calculateWeekendSale();
        calculateSpecialSalePrice();
        calculatePresentPrice();
        calculateTotalBenefit();
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
        if (isPresentAvailable()) {
            PRESENT = "샴페인 1개";
        }
        return PRESENT;
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
            PRESENT_PRICE = 25000;
        }
    }

    private void calculateTotalBenefit() {
        TOTAL_BENEFIT.put("크리스마스 디데이 할인: ", D_DAY_SALE_PRICE);
        TOTAL_BENEFIT.put("평일 할인: ", WEEKDAY_SALE_PRICE);
        TOTAL_BENEFIT.put("주말 할인: ", WEEKEND_SALE_PRICE);
        TOTAL_BENEFIT.put("특별 할인: ", SPECIAL_SALE_PRICE);
        TOTAL_BENEFIT.put("증정 이벤트: ", PRESENT_PRICE);

        TOTAL_BENEFIT.forEach((key, value) -> {
            TOTAL_BENEFIT_PRICE += value;
        });
    }

    private boolean isSpecialDay() {
        int date = getReservationDate();
        return date % 7 == 3 || date == 25;
    }

    private boolean isWeekend() {
        int date = getReservationDate();
        return date % 7 == 1 || date % 7 == 2;
    }

    public boolean isBenefitAvailable() {
        return TOTAL_PRICE >= 10000;
    }

    public boolean isPresentAvailable() {
        return TOTAL_PRICE >= 120000;
    }

}
