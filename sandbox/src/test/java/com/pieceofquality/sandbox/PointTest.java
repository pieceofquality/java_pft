package com.pieceofquality.sandbox;

import org.testng.annotations.Test;


public class PointTest {

    @Test
    public void testDistance1(){
        Point p1 = new Point(10,20);
        Point p2 = new Point(30,40);
        assert p1.distance(p2) == 28.284271247461902;
    }
}
