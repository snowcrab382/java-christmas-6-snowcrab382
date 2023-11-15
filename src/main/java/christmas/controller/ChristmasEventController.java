package christmas.controller;

import christmas.model.UserOrder;
import christmas.service.ChristmasEventService;

public class ChristmasEventController {

    private static final String EVENT_START_MESSAGE = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.";

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
