package me.tungexplorer.study.equals.verifier;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import nl.jqno.equalsverifier.EqualsVerifier;

public class S02 {

    public class Point {
        private int x;
        private int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public boolean equals(Point obj) {
            if (!(obj instanceof Point)) {
                return false;
            }
            return x == obj.x && y == obj.y;
        }

        public int hashCode() {
            int result = x;
            result = 31 * result + y;
            return result;
        }
    }

    @Test
    public void whats_the_problem() {
        Point point = new Point(1, 1);
        Point clone = new Point(1, 1);
        List<Point> pts  = List.of(point);
        Assert.assertTrue(point.equals(clone));
        Assert.assertTrue(pts.contains(clone));
    }

    @Test
    public void get_a_better_error_message() {
        EqualsVerifier.forClass(Point.class).verify();
    }
}
