package christmas.service;

import christmas.model.BenefitCalculator;
import christmas.model.UserOrder;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.LinkedHashMap;

public class ChristmasEventService {

    private static InputView inputView;
    private static OutputView outputView;

    public ChristmasEventService() {
        inputView = new InputView();
        outputView = new OutputView();
    }

    public UserOrder requestUserInput() {
        int reservationDate = inputView.readDate();
        LinkedHashMap<String, Integer> reservationOrder = inputView.readOrder();

        return new UserOrder(reservationDate, reservationOrder);
    }

    public void requestUserBenefit(UserOrder userOrder) {
        BenefitCalculator benefitCalculator = new BenefitCalculator(userOrder);
        benefitCalculator.calculate();

        outputView.printBenefit(benefitCalculator);
    }
}
