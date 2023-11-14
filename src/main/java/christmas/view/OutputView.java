package christmas.view;

import christmas.model.BenefitCalculator;

public class OutputView {

    public void printBenefit(BenefitCalculator benefitCalculator) {
        System.out.println("12월 " + benefitCalculator.getReservationDate() + "일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!");
        printOrders(benefitCalculator);
        printTotalPrice(benefitCalculator);
        printPresent(benefitCalculator);
        if (benefitCalculator.isBenefitAvailable()) {
            printBenefitResult(benefitCalculator);
        }
    }

    private void printBenefitResult(BenefitCalculator benefitCalculator) {
        System.out.println();
        System.out.println("<혜택 내역>");
        if (benefitCalculator.isBenefitAvailable()) {
            benefitCalculator.calculateTotalBenefit().forEach((key, value) -> {
                if (value != 0) {
                    System.out.println(key + "-" + value + "원");
                }
            });
        }

    }

    private void printPresent(BenefitCalculator benefitCalculator) {
        System.out.println();
        System.out.println("<증정 메뉴>");
        System.out.println(benefitCalculator.getPresent() + " 1개");
    }

    private void printTotalPrice(BenefitCalculator benefitCalculator) {
        System.out.println();
        System.out.println("<할인 전 총주문 금액>");
        int totalPrice = benefitCalculator.getTotalPrice();
        //쉼표 구분해서 출력 구현 필요
        System.out.println(totalPrice + "원");
    }

    private void printOrders(BenefitCalculator benefitCalculator) {
        System.out.println();
        System.out.println("<주문 메뉴>");
        benefitCalculator.getReservationOrder().forEach((key, value) -> {
            System.out.println(key + " " + value + "개");
        });

    }


}
