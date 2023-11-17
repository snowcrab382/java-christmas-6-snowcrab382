package christmas.validator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class DateValidatorTest {

    DateValidator dateValidator = new DateValidator();

    @DisplayName("입력받은 날짜가 1~31 사이의 정수가 아니면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"0", "32", "", " ", "*", "a", "1.1", "-1234", "999999999999"})
    void validateFailedTest(String date) {
        assertThatThrownBy(() -> dateValidator.validate(date))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
    }

    @DisplayName("입력받은 날짜가 1~31 사이의 숫자이면 정수로 변환하여 반환된다.")
    @ParameterizedTest
    @ValueSource(strings = {"1", "31", "12", "25"})
    void validateSuccessTest(String date) {
        assertThat(dateValidator.validate(date)).isEqualTo(Integer.parseInt(date));
    }

}