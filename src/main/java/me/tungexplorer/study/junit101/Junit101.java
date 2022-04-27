package me.tungexplorer.study.junit101;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.Random;

import org.junit.jupiter.api.RepeatedTest;

public class Junit101 {


    @RepeatedTest(10)
    public void repeatTest() {
        Random random = new Random();
        var randomN = random.nextInt(11) + 1;

        assertThat(randomN)
            .isEqualTo(3);
    }
}
