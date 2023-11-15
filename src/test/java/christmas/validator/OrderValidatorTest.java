package christmas.validator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class OrderValidatorTest {

    OrderValidator orderValidator = new OrderValidator();

    @DisplayName("입력받은 주문은 메뉴-수량의 형식을 갖고, 없는 메뉴이거나 중복, 수량이 초과되는 등의 경우 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"", " ", "a", "a---", "레드와인-1,,,,,,초코케이크-2", ",레드와인-1,초코케이크-2", "a-a", "레드와인-1", "레드와인1",
            "레드와인-1,레드와인-1", "초코케이크-21", "파스타-1"})
    void validateFailedTest(String order) {
        assertThatThrownBy(() -> orderValidator.validate(order))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }

    @DisplayName("입력받은 주문이 정상적으로 입력되면, 메뉴-수량의 Map 객체로 반환된다.")
    @ParameterizedTest
    @ValueSource(strings = {"레드와인-1,초코케이크-1"})
    void validateSuccessTest(String order) {
        Map<String, Integer> validatedOrders = orderValidator.validate(order);
        assertThat(validatedOrders).containsEntry("레드와인", 1);
        assertThat(validatedOrders).containsEntry("초코케이크", 1);
    }
}