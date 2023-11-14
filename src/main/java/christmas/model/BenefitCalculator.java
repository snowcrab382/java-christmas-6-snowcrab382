package christmas.model;

import java.util.Map;

public class BenefitCalculator {

    private static int TOTAL_PRICE = 0;
    private static int D_DAY_SALE_PRICE = 0;

    private static UserOrder userOrder;

    public BenefitCalculator(UserOrder userOrder) {
        this.userOrder = userOrder;
    }

    public Map<String, Integer> getOrders() {
        return userOrder.getReservationOrder();
    }

    public int calculateTotalPrice() {
        userOrder.getReservationOrder().forEach((key, value) -> {
            TOTAL_PRICE += Menu.valueOf(key).getPrice() * value;
        });
        return TOTAL_PRICE;
    }

}
