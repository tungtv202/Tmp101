package tmp;

import java.util.SortedSet;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

public class Tmp01 {

    static boolean isBalance(char a, char b) {
        switch (a) {
            case '(':
                return b == ')';
            case '{':
                return b == '}';
            case '[':
                return b == ']';
            default:
                return false;
        }
    }

    static boolean checkBalance(String input) {
        int length = input.length();
        if (length == 0 || length % 2 == 1) return false;
        for (int i = 0; i < length / 2; i++) {
            if (!isBalance(input.charAt(i), input.charAt(length - 1 - i))) {
                return false;
            }
        }
        return true;
    }

    @Test
    void test01() {
        SoftAssertions.assertSoftly(softAssertions -> {
                softAssertions.assertThat(checkBalance("()"))
                    .isTrue();
                softAssertions.assertThat(checkBalance(")"))
                    .isFalse();
                softAssertions.assertThat(checkBalance("))"))
                    .isFalse();
                softAssertions.assertThat(checkBalance("[()]"))
                    .isTrue();
                softAssertions.assertThat(checkBalance("{()]"))
                    .isFalse();
                softAssertions.assertThat(checkBalance(""))
                    .isFalse();
                softAssertions.assertThat(checkBalance("{(([]))}"))
                    .isTrue();
            }
        );

    }
}
