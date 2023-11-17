package christmas.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class MenuTest {

    @DisplayName("메뉴에 존재하는 음식 이름만 사용할 수 있다.")
    @ParameterizedTest
    @ValueSource(strings = {"양송이수프", "타파스", "시저샐러드", "티본스테이크", "바비큐립", "해산물파스타", "크리스마스파스타", "초코케이크", "아이스크림", "제로콜라",
            "레드와인", "샴페인"})
    void isInMenuTest(String menu) {
        assertThat(Menu.isInMenu(menu)).isTrue();
    }

    @DisplayName("음료는 다른 카테고리의 음식이 될 수 없다.")
    @ParameterizedTest()
    @ValueSource(strings = {"양송이수프", "타파스", "시저샐러드", "티본스테이크", "바비큐립", "해산물파스타", "크리스마스파스타", "초코케이크", "아이스크림"})
    void isBeverageTest(String notBeverage) {
        assertThat(Menu.isBeverage(notBeverage)).isFalse();
    }

    @DisplayName("메인 디쉬는 다른 카테고리의 음식이 될 수 없다.")
    @ParameterizedTest
    @ValueSource(strings = {"초코케이크", "아이스크림", "제로콜라", "레드와인", "샴페인"})
    void isMainDishTest() {
    }

    @DisplayName("디저트는 다른 카테고리의 음식이 될 수 없다.")
    @ParameterizedTest
    @ValueSource(strings = {"양송이수프", "타파스", "시저샐러드", "티본스테이크", "바비큐립", "해산물파스타", "크리스마스파스타", "제로콜라", "레드와인", "샴페인"})
    void isDessertTest() {
    }
}