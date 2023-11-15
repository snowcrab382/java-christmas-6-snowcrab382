package christmas.controller;

import static christmas.view.InputView.EVENT_START_MESSAGE;

import christmas.model.UserOrder;
import christmas.service.ChristmasEventService;

public class ChristmasEventController {

    private final ChristmasEventService christmasEventService;

    public ChristmasEventController() {
        System.out.println(EVENT_START_MESSAGE);
        christmasEventService = new ChristmasEventService();
    }

    public void run() {
        UserOrder userOrder = requestUserInput();

        requestUserBenefit(userOrder);
    }

    public UserOrder requestUserInput() {
        return christmasEventService.requestUserInput();
    }

    public void requestUserBenefit(UserOrder userOrder) {
        christmasEventService.requestUserBenefit(userOrder);
    }
}
