package me.tungexplorer.study.equals.verifier;

import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;

public class S03 {

    public class Point {
        private int x;
        private int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public final boolean equals(Object obj) {
            if (!(obj instanceof Point)) {
                return false;
            }
            Point other = (Point) obj;
            return x == other.x && y == other.y;
        }

        public final int hashCode() {
            int result = x;
            result = 31 * result + y;
            return result;
        }
    }

    @Test
    public void whats_the_problem() {
        Point point = new Point(1, 1);
        Set<Point> pts = new HashSet<>();
        pts.add(point);
        point.x = 3;
        Assert.assertTrue(pts.contains(point));
    }

    @Test
    public void get_a_better_error_message() {
        EqualsVerifier.forClass(Point.class)
            .suppress(Warning.NONFINAL_FIELDS)
            .verify();
    }
}
