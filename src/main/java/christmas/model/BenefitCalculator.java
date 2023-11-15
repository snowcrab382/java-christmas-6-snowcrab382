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

public class BenefitCalculator {

    private static int totalPrice;
    private static int discountedTotalPrice;

    private static int dDaySalePrice;
    private static int weekendSalePrice;
    private static int weekdaySalePrice;
    private static int specialSalePrice;

    private static String present;
    private static int presentPrice;

    private static LinkedHashMap<String, Integer> totalBenefitResult;
    private static int totalBenefitPrice;

    private static String eventBadge;

    private static UserOrder userOrder;

    public BenefitCalculator(UserOrder userOrder) {
        this.userOrder = userOrder;
        totalPrice = 0;
        discountedTotalPrice = 0;
        dDaySalePrice = 0;
        weekendSalePrice = 0;
        weekdaySalePrice = 0;
        specialSalePrice = 0;
        present = DEFAULT_CONDITION;
        presentPrice = 0;
        totalBenefitResult = new LinkedHashMap<>();
        totalBenefitPrice = 0;
        eventBadge = DEFAULT_CONDITION;
    }

    public void calculate() {
        totalPrice();
        dDaySalePrice();
        weekdaySalePrice();
        weekendSalePrice();
        specialSalePrice();
        presentPrice();
        totalBenefit();
        discountedTotalPrice();
        eventBadge();
    }

    public LinkedHashMap<String, Integer> getReservationOrder() {
        return userOrder.getReservationOrder();
    }

    public int getReservationDate() {
        return userOrder.getReservationDate();
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public String getPresent() {
        return present;
    }

    public LinkedHashMap<String, Integer> getBenefitResult() {
        return totalBenefitResult;
    }

    public int getBenefitPrice() {
        return totalBenefitPrice;
    }

    public int getDiscountedTotalPrice() {
        return discountedTotalPrice;
    }

    public String getEventBadge() {
        return eventBadge;
    }

    private void totalPrice() {
        getReservationOrder().forEach((key, value) -> {
            totalPrice += Menu.getPrice(key) * value;
        });
    }

    private void dDaySalePrice() {
        int date = getReservationDate();
        if (date <= D_DAY_SALE_DATE) {
            dDaySalePrice -= D_DAY_SALE_DEFAULT + (date - 1) * D_DAY_INCREASE_PRICE;
        }
    }

    private void weekdaySalePrice() {
        if (!isWeekend()) {
            getReservationOrder().forEach((key, value) -> {
                if (Menu.isDessert(key)) {
                    weekdaySalePrice -= value * WEEKDAY_SALE_DEFAULT;
                }
            });
        }
    }

    private void weekendSalePrice() {
        if (isWeekend()) {
            getReservationOrder().forEach((key, value) -> {
                if (Menu.isMainDish(key)) {
                    weekendSalePrice -= value * WEEKEND_SALE_DEFAULT;
                }
            });
        }
    }

    private void specialSalePrice() {
        if (isSpecialDay()) {
            specialSalePrice -= SPECIAL_SALE_DEFAULT;
        }
    }

    private void presentPrice() {
        if (isPresentAvailable()) {
            present = "샴페인 1개";
            presentPrice -= PRESENT_CHAMPAGNE_PRICE;
        }
    }

    private void totalBenefit() {
        totalBenefitResult.put("크리스마스 디데이 할인", dDaySalePrice);
        totalBenefitResult.put("평일 할인", weekdaySalePrice);
        totalBenefitResult.put("주말 할인", weekendSalePrice);
        totalBenefitResult.put("특별 할인", specialSalePrice);
        totalBenefitResult.put("증정 이벤트", presentPrice);

        totalBenefitResult.forEach((key, value) -> {
            totalBenefitPrice += value;
        });
    }

    private void discountedTotalPrice() {
        discountedTotalPrice = totalPrice + totalBenefitPrice - presentPrice;
    }

    private void eventBadge() {
        if (totalBenefitPrice < -5000) {
            eventBadge = STAR_BADGE;
        }
        if (totalBenefitPrice < -10000) {
            eventBadge = TREE_BADGE;
        }
        if (totalBenefitPrice < -20000) {
            eventBadge = SANTA_BADGE;
        }
    }

    public boolean isBenefitAvailable() {
        return totalPrice >= MIN_BENEFITABLE_PRICE;
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
        return totalPrice >= MIN_PRESENTABLE_PRICE;
    }
}
