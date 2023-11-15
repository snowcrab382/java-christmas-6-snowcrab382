package christmas.validator;

import static christmas.constant.Constants.MAX_ORDER_COUNT;
import static christmas.constant.ErrorMessages.WRONG_ORDER;

import christmas.model.Menu;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderValidator {

    private static Map<String, Integer> reservationOrder;

    public OrderValidator() {
        reservationOrder = new HashMap<>();
    }

    public Map<String, Integer> validate(String input) {
        List<String> orders = isCorrectFormat(input);
        isCountNumber(orders);
        isMenuDuplicate(orders);
        isInMenu();
        isMenuOnlyBeverage();
        isCountInRange();
        isTotalCountInRange();
        return reservationOrder;
    }

    private List<String> isCorrectFormat(String input) {
        List<String> orders = List.of(input.split(","));
        for (String order : orders) {
            List<String> menuAndCount = List.of(order.split("-"));
            if (menuAndCount.size() != 2) {
                throw new IllegalArgumentException(WRONG_ORDER.getMessage());
            }
        }
        return orders;
    }

    private void isCountNumber(List<String> orders) {
        for (String order : orders) {
            List<String> menuAndCount = List.of(order.split("-"));
            try {
                Integer.parseInt(menuAndCount.get(1));
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException(WRONG_ORDER.getMessage());
            }
        }
    }

    private void isMenuDuplicate(List<String> orders) {
        for (String order : orders) {
            List<String> menuAndCount = List.of(order.split("-"));
            String foodName = menuAndCount.get(0);
            int count = Integer.parseInt(menuAndCount.get(1));

            if (reservationOrder.containsKey(foodName)) {
                throw new IllegalArgumentException(WRONG_ORDER.getMessage());
            }
            reservationOrder.put(foodName, count);
        }
    }

    private void isInMenu() {
        for (String foodName : reservationOrder.keySet()) {
            if (!Menu.isInMenu(foodName)) {
                throw new IllegalArgumentException(WRONG_ORDER.getMessage());
            }
        }
    }

    private void isMenuOnlyBeverage() {
        boolean flag = true;
        for (String foodName : reservationOrder.keySet()) {
            if (!Menu.isBeverage(foodName)) {
                flag = false;
            }
        }
        if (flag) {
            throw new IllegalArgumentException(WRONG_ORDER.getMessage());
        }
    }

    private void isCountInRange() {
        for (int count : reservationOrder.values()) {
            if (count < 1) {
                throw new IllegalArgumentException(WRONG_ORDER.getMessage());
            }
        }
    }

    private void isTotalCountInRange() {
        int totalCount = 0;
        for (int count : reservationOrder.values()) {
            totalCount += count;
        }
        if (totalCount > MAX_ORDER_COUNT) {
            throw new IllegalArgumentException(WRONG_ORDER.getMessage());
        }
    }
}
