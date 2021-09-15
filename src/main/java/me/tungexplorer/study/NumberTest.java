package me.tungexplorer.study;

import java.util.Optional;

import org.junit.Test;

import com.google.common.base.Preconditions;

public class NumberTest {


    private static Optional<Double> doubleParser(String param) {
        try {
            return Optional.ofNullable(param)
                .map(Double::parseDouble)
                .map(associatedProbability -> {
                    Preconditions.checkArgument(associatedProbability > 0 && associatedProbability < 1, "'associatedProbability' must be greater than 0.0 and smaller than 1.0");
                    return associatedProbability;
                });
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException("'expectedBlobCount' must be numeric");
        }
    }

    @Test
    public void testParseDouble(){
        System.out.println(doubleParser("0.2"));
    }

}
