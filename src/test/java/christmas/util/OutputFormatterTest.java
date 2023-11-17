package christmas.util;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class OutputFormatterTest {

    @DisplayName("출력되는 모든 금액은 1000원 단위로 쉼표(,)를 사용해 구분한다.")
    @ParameterizedTest()
    @CsvSource(value = {"1000:1,000원", "10000:10,000원", "100000:100,000원", "1000000:1,000,000원",
            "-1000:-1,000원"}, delimiter = ':')
    void formatMoneyTest(int money, String expected) {
        String actual = OutputFormatter.formatMoney(money);

        assertThat(actual).isEqualTo(expected);

    }
}