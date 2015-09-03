/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author dav
 */
public class Square extends Quadrilateral{
    private double length;
    
    // length constructor
    public Square(double length){
        super(length, length, length, length);
        this.length = length;
    }
    
    // vertices constructor
    public Square(Vertex A, Vertex B, Vertex C, Vertex D){
        super(A, B, C, D);
        this.length = new Line(A, B).length();
    }
    
    // get perimeter of shape
    @Override
    public double perimeter(){
        return length * 4;
    }
    
    // get area of shape
    @Override
    public double area(){
        return Math.pow(length, 2);
    }
}
