package christmas.model;

import static christmas.constant.Constants.DEFAULT_CONDITION;
import static christmas.constant.Constants.D_DAY_INCREASE_PRICE;
import static christmas.constant.Constants.D_DAY_SALE_DATE;
import static christmas.constant.Constants.D_DAY_SALE_DEFAULT;
import static christmas.constant.Constants.MIN_BENEFITABLE_PRICE;
import static christmas.constant.Constants.MIN_PRESENTABLE_PRICE;
import static christmas.constant.Constants.PRESENT_CHAMPAGNE_PRICE;
import static christmas.constant.Constants.SANTA_BADGE;
import static christmas.constant.Constants.SPECIAL_SALE_DEFAULT;
import static christmas.constant.Constants.STAR_BADGE;
import static christmas.constant.Constants.TREE_BADGE;
import static christmas.constant.Constants.WEEKDAY_SALE_DEFAULT;
import static christmas.constant.Constants.WEEKEND_SALE_DEFAULT;

import java.util.LinkedHashMap;
import java.util.Map;

public class BenefitCalculator {

    private static int TOTAL_PRICE = 0;
    private static int DISCOUNTED_TOTAL_PRICE = 0;

    private static int D_DAY_SALE_PRICE = 0;
    private static int WEEKEND_SALE_PRICE = 0;
    private static int WEEKDAY_SALE_PRICE = 0;
    private static int SPECIAL_SALE_PRICE = 0;

    private static String PRESENT = DEFAULT_CONDITION;
    private static int PRESENT_PRICE = 0;

    private static Map<String, Integer> TOTAL_BENEFIT_RESULT = new LinkedHashMap<>();
    private static int TOTAL_BENEFIT_PRICE = 0;

    private static String EVENT_BADGE = DEFAULT_CONDITION;

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
        if (date <= D_DAY_SALE_DATE) {
            D_DAY_SALE_PRICE -= D_DAY_SALE_DEFAULT + (date - 1) * D_DAY_INCREASE_PRICE;
        }
    }

    private void calculateWeekdaySale() {
        if (!isWeekend()) {
            getReservationOrder().forEach((key, value) -> {
                if (Menu.isDessert(key)) {
                    WEEKDAY_SALE_PRICE -= value * WEEKDAY_SALE_DEFAULT;
                }
            });
        }
    }

    private void calculateWeekendSale() {
        if (isWeekend()) {
            getReservationOrder().forEach((key, value) -> {
                if (Menu.isMainDish(key)) {
                    WEEKEND_SALE_PRICE -= value * WEEKEND_SALE_DEFAULT;
                }
            });
        }
    }

    private void calculateSpecialSalePrice() {
        if (isSpecialDay()) {
            SPECIAL_SALE_PRICE -= SPECIAL_SALE_DEFAULT;
        }
    }

    private void calculatePresentPrice() {
        if (isPresentAvailable()) {
            PRESENT = "샴페인 1개";
            PRESENT_PRICE -= PRESENT_CHAMPAGNE_PRICE;
        }
    }

    private void calculateTotalBenefit() {
        TOTAL_BENEFIT_RESULT.put("크리스마스 디데이 할인", D_DAY_SALE_PRICE);
        TOTAL_BENEFIT_RESULT.put("평일 할인", WEEKDAY_SALE_PRICE);
        TOTAL_BENEFIT_RESULT.put("주말 할인", WEEKEND_SALE_PRICE);
        TOTAL_BENEFIT_RESULT.put("특별 할인", SPECIAL_SALE_PRICE);
        TOTAL_BENEFIT_RESULT.put("증정 이벤트", PRESENT_PRICE);

        TOTAL_BENEFIT_RESULT.forEach((key, value) -> {
            TOTAL_BENEFIT_PRICE += value;
        });
    }

    private void calculateDiscountedTotalPrice() {
        DISCOUNTED_TOTAL_PRICE = TOTAL_PRICE + TOTAL_BENEFIT_PRICE - PRESENT_PRICE;
    }

    private void calculateEventBadge() {
        if (TOTAL_BENEFIT_PRICE < -5000) {
            EVENT_BADGE = STAR_BADGE;
        }
        if (TOTAL_BENEFIT_PRICE < -10000) {
            EVENT_BADGE = TREE_BADGE;
        }
        if (TOTAL_BENEFIT_PRICE < -20000) {
            EVENT_BADGE = SANTA_BADGE;
        }
    }

    public boolean isBenefitAvailable() {
        return TOTAL_PRICE >= MIN_BENEFITABLE_PRICE;
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
        return TOTAL_PRICE >= MIN_PRESENTABLE_PRICE;
    }
}
