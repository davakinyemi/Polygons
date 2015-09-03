/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author dav
 */
public class Rectangle extends Quadrilateral {
    private double width, height;
    
    // length constructor
    public Rectangle(double width, double height){
        super(width, height, width, height);
        this.width = width;
        this.height = height;
    }
    
    // vertex constructor
    public Rectangle(Vertex A, Vertex B, Vertex C, Vertex D){
        super(A, B, C, D);
        this.width = new Line(A, B).length();
        this.height = new Line(B, C).length();
    }
    
    // get perimeter of shape
    @Override
    public double perimeter(){
        return (width * 2) + (height * 2);
    }
    
    // get area of shape
    @Override
    public double area(){
        return width * height;
    }
}
