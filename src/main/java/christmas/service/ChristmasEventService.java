package christmas.service;

import christmas.model.BenefitCalculator;
import christmas.model.UserOrder;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.Map;

public class ChristmasEventService {

    private static InputView inputView;
    private static OutputView outputView;
    private static UserOrder userOrder;

    public ChristmasEventService() {
        inputView = new InputView();
        outputView = new OutputView();
        userOrder = new UserOrder();
    }

    public UserOrder requestUserInput() {
        int reservationDate = inputView.readDate();
        Map<String, Integer> reservationOrder = inputView.readOrder();

        return userOrder;
    }

    public void requestUserBenefit(UserOrder userOrder) {
        BenefitCalculator benefitCalculator = new BenefitCalculator(userOrder);

        outputView.printBenefit(benefitCalculator);
    }
}
