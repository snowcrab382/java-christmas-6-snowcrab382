package christmas.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.LinkedHashMap;
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
        reservationOrder.put("초코케이크", 1);
        reservationOrder.put("제로콜라", 2);

        UserOrder userOrder = new UserOrder(reservationDate, reservationOrder);
        benefitCalculator = new BenefitCalculator(userOrder);
        benefitCalculator.calculate();
    }

    @DisplayName("주문을 정상적으로 입력하면 LinkedHashMap 객체에 주문과 수량이 저장된다.")
    @Test
    void getReservationOrderTest() {
        assertThat(benefitCalculator.getReservationOrder()).isEqualTo(new LinkedHashMap<String, Integer>() {{
            put("해산물파스타", 1);
            put("초코케이크", 1);
            put("제로콜라", 2);
        }});
    }

    @DisplayName("예약 날짜를 정상적으로 입력하면 int 타입으로 저장된다.")
    @Test
    void getReservationDateTest() {
        assertThat(benefitCalculator.getReservationDate()).isEqualTo(24);
    }

    @DisplayName("입력받은 주문을 토대로 총 주문 금액을 계산한다.")
    @Test
    void getTotalPriceTest() {
        assertThat(benefitCalculator.getTotalPrice()).isEqualTo(56000);
    }

    @DisplayName("총 주문 금액을 토대로 증정 선물을 확인한다.")
    @Test
    void getPresentTest() {
        assertThat(benefitCalculator.getPresent()).isEqualTo("없음");
    }

    @DisplayName("입력받은 주문을 토대로 모든 혜택을 계산한다.")
    @Test
    void getBenefitResultTest() {
        assertThat(benefitCalculator.getBenefitResult()).isEqualTo(new LinkedHashMap<String, Integer>() {{
            put("크리스마스 디데이 할인", -3300);
            put("평일 할인", -2023);
            put("주말 할인", 0);
            put("특별 할인", -1000);
            put("증정 이벤트", 0);
        }});
    }

    @DisplayName("모든 혜택을 토대로 총 혜택 금액을 계산한다.")
    @Test
    void getBenefitPriceTest() {
        assertThat(benefitCalculator.getBenefitPrice()).isEqualTo(-6323);
    }

    @DisplayName("예상 결제 금액은 총 주문 금액에서 총 혜택 금액을 뺀 금액이다. 단, 증정 선물의 가격은 제외한다.")
    @Test
    void getDiscountedTotalPriceTest() {
        assertThat(benefitCalculator.getDiscountedTotalPrice()).isEqualTo(49677);
    }

    @DisplayName("총 혜택 금액을 토대로 이벤트 배지를 부여한다.")
    @Test
    void getEventBadgeTest() {
        assertThat(benefitCalculator.getEventBadge()).isEqualTo("별");
    }

    @DisplayName("총 주문 금액이 10000원 이상이어야 혜택을 받을 수 있다.")
    @Test
    void isBenefitAvailableTest() {
        assertThat(benefitCalculator.isBenefitAvailable()).isTrue();
    }
}