package baseball;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static camp.nextstep.edu.missionutils.test.Assertions.assertRandomNumberInRangeTest;
import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ApplicationTest extends NsTest {
    @Test
    void 게임종료_후_재시작() {
        assertRandomNumberInRangeTest(
                () -> {
                    run("246", "135", "1", "597", "589", "2");
                    assertThat(output()).contains("낫싱", "3스트라이크", "1볼 1스트라이크", "3스트라이크", "게임 종료");
                },
                1, 3, 5, 5, 8, 9
        );
    }

    @Test
    void 예외_테스트() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("1234"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Override
    public void runMain() {
        Application.main(new String[]{});
    }


    @Test
    void checkNewGame() {

    }

    @Test
    void doGame() {
    }

    @Test
    void generateMessage() {
        assertThat(Application.generateMessage(1, 2))
                .isEqualTo("2볼 1스트라이크");
        assertThat(Application.generateMessage(0, 0))
                .isEqualTo("낫싱");
        assertThat(Application.generateMessage(3, 0))
                .isEqualTo("3스트라이크");
        assertThat(Application.generateMessage(3, 0).contentEquals("3스트라이크"))
                .isEqualTo(true);

    }

    @Test
    void checkBall() {
        assertThat(Application.checkBall(List.of(1, 2, 3), List.of(2, 3, 1)))
                .isEqualTo(3);
        assertThat(Application.checkBall(List.of(1, 2, 3), List.of(7, 8, 9)))
                .isEqualTo(0);
    }

    @Test
    void checkStrike() {
        assertThat(Application.checkStrike(List.of(1, 2, 3), List.of(1, 3, 2)))
                .isEqualTo(1);
        assertThat(Application.checkStrike(List.of(1, 2, 3), List.of(7, 8, 9)))
                .isEqualTo(0);
    }

    @Test
    void getUserNumber() {
    }

    @Test
    void generateComNumber() {
        //요소가 3개인지
        assertThat(Application.generateComNumber().size()).isEqualTo(3);
        //중복되는 요소가 없는지
        assertThat(Application.generateComNumber().stream()
                .collect(Collectors.toSet()).size()).isEqualTo(3);
    }
}
