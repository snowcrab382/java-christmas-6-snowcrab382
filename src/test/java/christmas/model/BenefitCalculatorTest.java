package christmas.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.LinkedHashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BenefitCalculatorTest {

    private static BenefitCalculator benefitCalculator;

    @BeforeAll
    static void setBenefitCalculator() {
        int reservationDate = 24;
        LinkedHashMap<String, Integer> reservationOrder = new LinkedHashMap<>();
        reservationOrder.put("해산물파스타", 1);
        reservationOrder.put("레드와인", 1);
        reservationOrder.put("초코케이크", 1);

        UserOrder userOrder = new UserOrder(reservationDate, reservationOrder);
        benefitCalculator = new BenefitCalculator(userOrder);
    }

    @DisplayName("입력된 주문은 ")
    @Test
    void getReservationOrderTest() {
        assertThat(benefitCalculator.getReservationOrder()).isEqualTo(Map.of(
                "해산물파스타", 1,
                "레드와인", 1,
                "초코케이크", 1
        ));
    }

    @Test
    void getReservationDateTest() {
        assertThat(benefitCalculator.getReservationDate()).isEqualTo(24);
    }

    @Test
    void getTotalPriceTest() {
        assertThat(benefitCalculator.getTotalPrice()).isEqualTo(110000);
    }

    @Test
    void getPresentTest() {
        assertThat(benefitCalculator.getPresent()).isEqualTo("없음");
    }

    @Test
    void getBenefitResultTest() {
        assertThat(benefitCalculator.getBenefitResult()).isEqualTo(Map.of(
                "크리스마스 디데이 할인", -3300,
                "평일 할인", -2023,
                "주말 할인", 0,
                "특별 할인", -1000,
                "증정 이벤트", 0
        ));

    }

    @Test
    void getBenefitPriceTest() {
        assertThat(benefitCalculator.getBenefitPrice()).isEqualTo(-6323);
    }

    @Test
    void getDiscountedTotalPriceTest() {
        assertThat(benefitCalculator.getDiscountedTotalPrice()).isEqualTo(103677);
    }

    @Test
    void getEventBadgeTest() {
        assertThat(benefitCalculator.getEventBadge()).isEqualTo("별");
    }

    @Test
    void isBenefitAvailableTest() {
        assertThat(benefitCalculator.isBenefitAvailable()).isTrue();
    }
}