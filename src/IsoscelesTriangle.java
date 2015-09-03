/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author dav
 */
public class IsoscelesTriangle extends Triangle {    
    
    // constructor for length input
    public IsoscelesTriangle(double width, double sideLength) throws lengthValueException{
        super(width, sideLength, sideLength);
    }
    
    // constructor for vertices
    public IsoscelesTriangle(Vertex A, Vertex B, Vertex C) throws lengthValueException{
        super(A, B, C);
    }
}
