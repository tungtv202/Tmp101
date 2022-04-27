package tmp;

import java.util.Stack;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

public class StackAlgorithmDemo {

    static int evaluation(String input) {
        Stack<Character> stack = new Stack<>();
        int removeCounter = 0;
        boolean flag = false;
        for (char c : input.toCharArray()) {
            if (!stack.isEmpty() && "()".contentEquals(stack.peek() + String.valueOf(c))) {
                stack.pop();
                flag = true;
            } else {
                if (flag) {
                    removeCounter = removeCounter + stack.size();
                    stack.clear();
                    flag = false;
                }
                stack.push(c);
            }

        }
        return removeCounter + stack.size();
    }

    @Test
    void test1() {
        SoftAssertions.assertSoftly(softAssertions -> {
            softAssertions.assertThat(evaluation("()")).isEqualTo(0);
            softAssertions.assertThat(evaluation("")).isEqualTo(0);
            softAssertions.assertThat(evaluation(")")).isEqualTo(1);
            softAssertions.assertThat(evaluation("(")).isEqualTo(1);
            softAssertions.assertThat(evaluation("())")).isEqualTo(1);
            softAssertions.assertThat(evaluation("()))")).isEqualTo(2);
            softAssertions.assertThat(evaluation("(()))")).isEqualTo(1);
            softAssertions.assertThat(evaluation("((()))")).isEqualTo(0);
            softAssertions.assertThat(evaluation("((())()")).isEqualTo(1);
            softAssertions.assertThat(evaluation("((())())")).isEqualTo(2);
            softAssertions.assertThat(evaluation("((())()))")).isEqualTo(3);
            softAssertions.assertThat(evaluation("((())(()))")).isEqualTo(2);
            softAssertions.assertThat(evaluation("))")).isEqualTo(2);
            softAssertions.assertThat(evaluation("))())")).isEqualTo(3);
        });
    }
}
