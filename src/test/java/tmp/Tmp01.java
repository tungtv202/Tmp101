package tmp;

import java.util.Optional;
import java.util.SortedSet;

import org.apache.commons.lang3.StringUtils;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

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

    @Test
    void test3() throws Exception{
        var temp = new ObjectMapper().readTree("{\n" +
            "      \"active\": true,\n" +
            "      \"client_id\": \"l238j323ds-23ij4\",\n" +
            "      \"username\": \"jdoe\",\n" +
            "      \"scope\": \"read write dolphin\",\n" +
            "      \"sub\": \"Z5O3upPC88QrAjx00dis\",\n" +
            "      \"aud\": \"https://protected.example.net/resource\",\n" +
            "      \"iss\": \"https://server.example.com/\",\n" +
            "      \"exp\": 1419356238,\n" +
            "      \"iat\": 1419350238,\n" +
            "      \"extension_field\": \"twenty-seven\"\n" +
            "     }");
        System.out.println(temp.get("active"));
    }

    @Test
    void testmp() {
        String replace = StringUtils.replace("Tung", "u", "1");
        System.out.println(replace);
    }

    private boolean isTMP1() {
        System.out.println("tmp1");
        return false;
    }

    private boolean isTMP2() {
        System.out.println("tmp2");
        return true;
    }
}
