package christmas.controller;

import christmas.model.UserOrder;
import christmas.service.ChristmasEventService;

public class ChristmasEventController {

    private final ChristmasEventService christmasEventService;

    public ChristmasEventController() {
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
