/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author dav
 */
class lengthValueException extends Exception {
   public lengthValueException(String msg){
      super(msg);
   }
}

public class Triangle implements Polygon {
    protected double base, leftSide, rightSide, perimeter, area;
    protected double numSides = 3;
    
    // length constructor
    public Triangle(double base, double rightSide, double leftSide) throws lengthValueException{    
        if(base > (leftSide + rightSide) || leftSide > (base + rightSide) || rightSide > (base + leftSide)) // determines whether shape is a triangle
            throw new lengthValueException("Error: Not a Triangle if one side is bigger than the other two combined");
        
        this.base = base;
        this.leftSide = leftSide;
        this.rightSide = rightSide;
    }
    
    // vertices constructor
    public Triangle(Vertex A, Vertex B, Vertex C) throws lengthValueException{
        this(new Line(A, B).length(), new Line(B, C).length(), new Line(C, A).length());      
    }
    
    // get perimeter of triangle
    @Override
    public double perimeter(){
        perimeter = leftSide + rightSide + base; 
        return perimeter;
    }
           
    // get area of triangle using heron's formula
    @Override
    public double area(){
        double x = perimeter() / 2;
        area = Math.sqrt((x * (x - leftSide)) * (x - rightSide) * (x - base)); // Heron's formula
        return area;
    }
}
