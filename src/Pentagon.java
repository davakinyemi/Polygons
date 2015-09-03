/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author dav
 */
// exception class
class vertexValueException extends Exception {
   public vertexValueException(String msg){
      super(msg);
   }
}

public class Pentagon implements Polygon{
    private Vertex A, B, C, D, E;
    private double length;
    private double check;
    
    // length constructor
    public Pentagon(double length){
        this.length = length;
    }
    
    // vertices constructor, throws exception if distance between all vertices are not equal
    public Pentagon(Vertex A, Vertex B, Vertex C, Vertex D, Vertex E) throws vertexValueException{
        check = new Line (A, B).length();
        if(new Line(B, C).length() != check && new Line(C, D).length() != check && new Line(D, E).length() != check)
            throw new vertexValueException("Error: Lengths between all vertices should be equal");
        
        this.A = A;
        this.B = B;
        this.C = C;
        this.D = D;
        this.E = E;
        
        // converts vertex to length
        length = new Line(A, B).length();
    }
    
    // get area
    @Override
    public double area(){
        double area = (0.25 * Math.sqrt(5 * (5 + (2 * Math.sqrt(5))))) * Math.pow(length, 2);
        return area;
    }
    
    // get perimeter
    @Override
    public double perimeter(){
        return length * 5;
    }
}
