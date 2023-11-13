package christmas.view;

import camp.nextstep.edu.missionutils.Console;


public class InputView {

    private static int reservationDate;

    public void readDate() {
        System.out.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
        String input = Console.readLine();
        validateDate(input);
    }

    public void readOrder() {
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
