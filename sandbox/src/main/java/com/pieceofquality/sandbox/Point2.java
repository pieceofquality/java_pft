package com.pieceofquality.sandbox;//Second and third exercices

public class Point2 {

    public static void main (String[] args){
        Point p1 = new Point(10,20);
        Point p2 = new Point(30,40);
        p1.x=10;
        p1.y=20;
        p2.x=30;
        p2.y=40;

        System.out.println("p1 = " + p1.x + "," +p1.y);
        System.out.println("p2 = " + p2.x + "," +p2.y);
        System.out.println("Distance between points is "+ p1.distance(p2));
    }
}