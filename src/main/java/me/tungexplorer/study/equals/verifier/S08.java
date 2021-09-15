package me.tungexplorer.study.equals.verifier;

import java.util.Objects;

import org.junit.Test;

import nl.jqno.equalsverifier.EqualsVerifier;

public class S08 {

    public class Point {
        private final int x;
        private final int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public final boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Point)) return false;
            Point point = (Point) o;
            return x == point.x && y == point.y;
        }

        @Override
        public final int hashCode() {
            return Objects.hash(x, y);
        }
    }

    @Test
    public void whats_the_problem() {
        EqualsVerifier.forClass(Point.class).verify(); // TRUE
    }
}
