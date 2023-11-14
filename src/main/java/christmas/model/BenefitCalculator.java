package christmas.model;

public class BenefitCalculator {

    private static int TOTAL_PRICE = 0;
    private static int D_DAY_SALE_PRICE = 1000;

    private static int WEEKEND_SALE_PRICE = 0;
    private static int WEEKDAY_SALE_PRICE = 0;

    private static int SPECIAL_SALE_PRICE = 0;

    private static UserOrder userOrder;

    public BenefitCalculator(UserOrder userOrder) {
        this.userOrder = userOrder;
    }

    public int calculateTotalPrice() {
        userOrder.getReservationOrder().forEach((key, value) -> {
            TOTAL_PRICE += Menu.valueOf(key).getPrice() * value;
        });
        return TOTAL_PRICE;
    }

    public int calculateDdaySalePrice() {
        int date = userOrder.getReservationDate();
        if (date <= 25) {
            D_DAY_SALE_PRICE += (date - 1) * 100;
        }
        return D_DAY_SALE_PRICE;
    }

    public int calculateDailySalePrice() {
        if (isWeekend()) {
            calculateWeekendSale();
            return WEEKEND_SALE_PRICE;
        }
        calculateWeekdaySale();
        return WEEKDAY_SALE_PRICE;
    }

    public int calculateSpecialSalePrice() {
        if (isSpecialDay()) {
            SPECIAL_SALE_PRICE += 1000;
        }
        return SPECIAL_SALE_PRICE;
    }

    private boolean isSpecialDay() {
        int date = userOrder.getReservationDate();
        return date % 7 == 3 || date == 25;
    }

    private void calculateWeekdaySale() {
        userOrder.getReservationOrder().forEach((key, value) -> {
            if (Menu.isDessert(key)) {
                WEEKDAY_SALE_PRICE += value * 2023;
            }
        });
    }

    private void calculateWeekendSale() {
        userOrder.getReservationOrder().forEach((key, value) -> {
            if (Menu.isMainDish(key)) {
                WEEKEND_SALE_PRICE += value * 2023;
            }
        });
    }

    private boolean isWeekend() {
        int date = userOrder.getReservationDate();
        return date % 7 == 1 || date % 7 == 2;
    }


}
