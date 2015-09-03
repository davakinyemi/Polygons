/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author dav
 */
public class EquiTriangle extends Triangle {
    // length constructor 
    public EquiTriangle(double sideLength) throws lengthValueException{
        super(sideLength, sideLength, sideLength);
    }
    
    //vertices constructor
    public EquiTriangle(Vertex A, Vertex B, Vertex C) throws lengthValueException{
        super(A, B, C);
    }
}
