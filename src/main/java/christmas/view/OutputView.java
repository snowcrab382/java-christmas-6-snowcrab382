package christmas.view;

import static christmas.util.OutputFormatter.formatMoney;

import christmas.constant.Constants;
import christmas.model.BenefitCalculator;

public class OutputView {

    private static final String BENEFIT_PREVIEW_MESSAGE = "12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!%n";
    private static final String BADGE_MESSAGE = "<12월 이벤트 배지>";
    private static final String EXPECTED_PAY_MESSAGE = "<할인 후 예상 결제 금액>";
    private static final String TOTAL_BENEFIT_MESSAGE = "<총혜택 금액>";
    private static final String BENEFIT_RESULT_MESSAGE = "<혜택 내역>";
    private static final String PRESENT_MESSAGE = "<증정 메뉴>";
    private static final String TOTAL_PRICE_MESSAGE = "<할인 전 총주문 금액>";
    private static final String ORDER_MESSAGE = "<주문 메뉴>";

    public void printResult(BenefitCalculator benefitCalculator) {
        System.out.printf(BENEFIT_PREVIEW_MESSAGE, benefitCalculator.getReservationDate());
        printOrders(benefitCalculator);
        printTotalPrice(benefitCalculator);
        printPresent(benefitCalculator);
        printBenefitResult(benefitCalculator);
        printBenefitPrice(benefitCalculator);
        printDiscountedTotalPrice(benefitCalculator);
        printEventBadge(benefitCalculator);
    }

    private void printEventBadge(BenefitCalculator benefitCalculator) {
        System.out.println();
        System.out.println(BADGE_MESSAGE);
        System.out.println(benefitCalculator.getEventBadge());
    }

    private void printDiscountedTotalPrice(BenefitCalculator benefitCalculator) {
        System.out.println();
        System.out.println(EXPECTED_PAY_MESSAGE);
        System.out.println(formatMoney(benefitCalculator.getDiscountedTotalPrice()));
    }

    private void printBenefitPrice(BenefitCalculator benefitCalculator) {
        System.out.println();
        System.out.println(TOTAL_BENEFIT_MESSAGE);
        System.out.println(formatMoney(benefitCalculator.getBenefitPrice()));
    }

    private void printBenefitResult(BenefitCalculator benefitCalculator) {
        System.out.println();
        System.out.println(BENEFIT_RESULT_MESSAGE);
        if (benefitCalculator.isBenefitAvailable()) {
            benefitCalculator.getBenefitResult().forEach((key, value) -> {
                if (value != 0) {
                    System.out.printf("%s: %s%n", key, formatMoney(value));
                }
            });
        }
        if (!benefitCalculator.isBenefitAvailable()) {
            System.out.println(Constants.DEFAULT_CONDITION);
        }
    }

    private void printPresent(BenefitCalculator benefitCalculator) {
        System.out.println();
        System.out.println(PRESENT_MESSAGE);
        System.out.println(benefitCalculator.getPresent());
    }

    private void printTotalPrice(BenefitCalculator benefitCalculator) {
        System.out.println();
        System.out.println(TOTAL_PRICE_MESSAGE);
        System.out.println(formatMoney(benefitCalculator.getTotalPrice()));
    }

    private void printOrders(BenefitCalculator benefitCalculator) {
        System.out.println();
        System.out.println(ORDER_MESSAGE);
        benefitCalculator.getReservationOrder().forEach((key, value) -> {
            System.out.printf("%s %d개%n", key, value);
        });

    }


}
