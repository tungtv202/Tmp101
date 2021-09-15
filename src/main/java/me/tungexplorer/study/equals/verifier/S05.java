package me.tungexplorer.study.equals.verifier;

import java.awt.Color;
import java.util.Objects;

import org.junit.Assert;
import org.junit.Test;


public class S05 {

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
            if (!(o instanceof Point)) return false;
            Point point = (Point) o;
            return x == point.x && y == point.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    @Test
    public void solves_liskov_substitution_principle() {
        Point point = new Point(1, 1);
        Point sub = new Point(1, 1) {
        };
        Assert.assertEquals(point, sub);
    }

    public class ColorPoint extends Point {

        private final Color color;

        public ColorPoint(int x, int y, Color color) {
            super(x, y);
            this.color = color;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof ColorPoint)) return false;
            if (!super.equals(o)) return false;
            ColorPoint that = (ColorPoint) o;
            return color.equals(that.color);
        }

        @Override
        public int hashCode() {
            return Objects.hash(super.hashCode(), color);
        }
    }

    @Test
    public void whats_the_problem() {
        Point p = new Point(1, 1);
        Point q = new ColorPoint(1, 1, Color.BLACK);
        Assert.assertEquals(p, q); // TRUE
        Assert.assertEquals(q, p); // FALSE
    }
}
