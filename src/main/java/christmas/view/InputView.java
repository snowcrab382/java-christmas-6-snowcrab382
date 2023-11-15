package christmas.view;


import camp.nextstep.edu.missionutils.Console;
import christmas.validator.DateValidator;
import christmas.validator.OrderValidator;
import java.util.LinkedHashMap;


public class InputView {

    private static final String REQUEST_DATE_MESSAGE = "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)";
    private static final String REQUEST_ORDER_MESSAGE = "주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)";

    private static DateValidator dateValidator;
    private static OrderValidator orderValidator;

    public InputView() {
        dateValidator = new DateValidator();
        orderValidator = new OrderValidator();
    }

    public int readDate() {
        System.out.println(REQUEST_DATE_MESSAGE);
        try {
            String input = Console.readLine();
            return dateValidator.validate(input);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return readDate();
        }
    }

    public LinkedHashMap<String, Integer> readOrder() {
        System.out.println(REQUEST_ORDER_MESSAGE);
        try {
            String input = Console.readLine();
            return orderValidator.validate(input);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return readOrder();
        }
    }
}
