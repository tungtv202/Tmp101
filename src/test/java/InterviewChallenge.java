import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.Stack;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

public class InterviewChallenge {

    static boolean isBalance(String input) {
        if (!input.contains("(")) {
            return false;
        }
        Stack<Character> stack = new Stack<>();
        for (char c : input.toCharArray()) {
            if (c == '(') {
                stack.push(c);
            } else if (c == ')') {
                if (stack.isEmpty() || stack.pop() != '(') {
                    return false;
                }
            }
        }
        return stack.size() <= 0;
    }

    @Test
    void test02() {
        assertThat(isBalance("aa(bb((dd))")).isFalse();
    }

    @Test
    void test01() {
        SoftAssertions.assertSoftly(softAssertions -> {
            softAssertions.assertThat(isBalance("()")).isTrue();
            softAssertions.assertThat(isBalance("")).isFalse();
            softAssertions.assertThat(isBalance(")")).isFalse();
            softAssertions.assertThat(isBalance("aa(bb((dd)))")).isTrue();
            softAssertions.assertThat(isBalance("aa(bb((dd))")).isFalse();
            softAssertions.assertThat(isBalance("aa(bb(dd)))")).isFalse();
            softAssertions.assertThat(isBalance("aabb")).isTrue();
        });
    }
}
