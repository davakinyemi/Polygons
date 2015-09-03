/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author dav
 */
public class Line {
    Vertex p1, p2;
    public Line(Vertex point1, Vertex point2) {
        this.p1 = point1;
        this.p2 = point2;
    }
    public Vertex midPoint() {
        return new Vertex ((p1.getX()+p2.getX())/2, (p1.getY()+p2.getY())/2);
    }
    public double length() {
        return Math.sqrt(
            (p1.getX() - p2.getX()) *  (p1.getX() - p2.getX()) + 
            (p1.getY() - p2.getY()) *  (p1.getY() - p2.getY())
        );
    }
}
