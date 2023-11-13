package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.model.Menu;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


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
            isCorrectFormat(input);
            System.out.println("reservationOrder = " + reservationOrder);
            isInMenu();
            isMenuDuplicate();
            isMenuOnlyBeverage();
            isCountInRange();
            isTotalCountInRange();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            reservationOrder.clear();
            readOrder();
        }
    }

    private void isTotalCountInRange() {
    }

    private void isCountInRange() {
        for (int count : reservationOrder.values()) {
            if (count < 1) {
                throw new IllegalArgumentException("주문 개수는 1 이상이어야 합니다.");
            }
        }
    }

    private void isMenuOnlyBeverage() {
        Set<String> foodNames = reservationOrder.keySet();


    }

    private void isMenuDuplicate() {
        try {
            Set<String> menuNames = reservationOrder.keySet();
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("중복된 메뉴가 있습니다.");
        }
    }

    private void isInMenu() {
        for (String foodName : reservationOrder.keySet()) {
            System.out.println("foodName = " + foodName);
            if (!Menu.isInMenu(foodName)) {
                throw new IllegalArgumentException("메뉴에 없는 메뉴가 있습니다.");
            }
        }
    }

    private void isCorrectFormat(String input) {
        try {
            List<String> order = List.of(input.split(","));
            for (String menu : order) {
                String[] menuAndCount = menu.split("-");
                String menuName = menuAndCount[0];
                String menuCount = menuAndCount[1];
                reservationOrder.put(menuName, Integer.parseInt(menuCount));
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new IllegalArgumentException("메뉴와 개수를 '-'로 구분해 주세요.");
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("메뉴 개수는 숫자로 입력해 주세요.");
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

    private void isInRange() {
        if (reservationDate < 1 || reservationDate > 31) {
            throw new IllegalArgumentException("1~31 사이 숫자가 아닙니다.");
        }
    }

    private void isNumber(String input) {
        try {
            reservationDate = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자가 아닙니다.");
        }
    }

}
