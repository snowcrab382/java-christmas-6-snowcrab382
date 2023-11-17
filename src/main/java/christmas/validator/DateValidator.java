package christmas.validator;

import static christmas.constant.Constants.MAX_INPUT_DATE;
import static christmas.constant.Constants.MIN_INPUT_DATE;
import static christmas.constant.ErrorMessages.WRONG_DATE;

public class DateValidator {

    private static int reservationDate;

    public int validate(String date) {
        isNumber(date);
        isInRange();
        return reservationDate;
    }

    private void isNumber(String input) {
        try {
            reservationDate = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(WRONG_DATE.getMessage());
        }
    }

    private void isInRange() {
        if (reservationDate < MIN_INPUT_DATE || reservationDate > MAX_INPUT_DATE) {
            throw new IllegalArgumentException(WRONG_DATE.getMessage());
        }
    }
}
