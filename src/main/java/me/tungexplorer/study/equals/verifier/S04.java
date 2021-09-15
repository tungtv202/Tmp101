package me.tungexplorer.study.equals.verifier;

import java.util.Objects;

import org.junit.Assert;
import org.junit.Test;

import nl.jqno.equalsverifier.EqualsVerifier;

public class S04 {

    public class Point {
        private final int x;
        private final int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return x == point.x && y == point.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    @Test
    public void whats_the_problem() {
        Point point = new Point(1, 1);
        Point sub = new Point(1, 1) {   };
        Assert.assertEquals(point, sub);
    }

    @Test
    public void get_a_better_error_message() {
        EqualsVerifier.forClass(Point.class)
            .usingGetClass()
            .verify();
    }
}
