package christmas.view;

import static christmas.constant.ErrorMessages.WRONG_DATE;
import static christmas.constant.ErrorMessages.WRONG_ORDER;

import camp.nextstep.edu.missionutils.Console;
import christmas.model.Menu;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class InputView {

    private static int reservationDate;
    private static Map<String, Integer> reservationOrder;

    public InputView() {
        reservationOrder = new HashMap<>();
    }

    public int readDate() {
        System.out.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
        String input = Console.readLine();
        validateDate(input);
        return reservationDate;
    }

    public Map<String, Integer> readOrder() {
        System.out.println("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");
        String input = Console.readLine();
        validateOrder(input);
        return reservationOrder;
    }

    private void validateOrder(String input) {
        try {
            List<String> orders = isCorrectFormat(input);
            isCountNumber(orders);
            isMenuDuplicate(orders);
            isInMenu();
            isMenuOnlyBeverage();
            isCountInRange();
            isTotalCountInRange();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            reservationOrder.clear();
            readOrder();
        }
    }

    private List<String> isCorrectFormat(String input) {
        List<String> orders = List.of(input.split(","));
        for (String order : orders) {
            List<String> menuAndCount = List.of(order.split("-"));
            if (menuAndCount.size() != 2) {
                throw new IllegalArgumentException(WRONG_ORDER);
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
                throw new IllegalArgumentException(WRONG_ORDER);
            }
        }
    }

    private void isMenuDuplicate(List<String> orders) {
        for (String order : orders) {
            List<String> menuAndCount = List.of(order.split("-"));
            String foodName = menuAndCount.get(0);
            int count = Integer.parseInt(menuAndCount.get(1));

            if (reservationOrder.containsKey(foodName)) {
                throw new IllegalArgumentException(WRONG_ORDER);
            }
            reservationOrder.put(foodName, count);
        }
    }

    private void isInMenu() {
        for (String foodName : reservationOrder.keySet()) {
            if (!Menu.isInMenu(foodName)) {
                throw new IllegalArgumentException(WRONG_ORDER);
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
            throw new IllegalArgumentException(WRONG_ORDER);
        }
    }

    private void isCountInRange() {
        for (int count : reservationOrder.values()) {
            if (count < 1) {
                throw new IllegalArgumentException(WRONG_ORDER);
            }
        }
    }

    private void isTotalCountInRange() {
        int totalCount = 0;
        for (int count : reservationOrder.values()) {
            totalCount += count;
        }
        if (totalCount > 20) {
            throw new IllegalArgumentException(WRONG_ORDER);
        }
    }


    private void validateDate(String input) {
        try {
            isNumber(input);
            isInRange();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            readDate();
        }
    }

    private void isNumber(String input) {
        try {
            reservationDate = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(WRONG_DATE);
        }
    }

    private void isInRange() {
        if (reservationDate < 1 || reservationDate > 31) {
            throw new IllegalArgumentException(WRONG_DATE);
        }
    }
}
