package christmas;

import christmas.controller.ChristmasEventController;

public class Application {
    public static void main(String[] args) {
        ChristmasEventController christmasEventController = new ChristmasEventController();
        christmasEventController.run();
    }
}
