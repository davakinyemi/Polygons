/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 *
 * @author dav
 */
public class Octagon implements Polygon {
    private Vertex A, B, C, D, E, F, G, H;
    private double length, check;
    
    // length constructor
    public Octagon(double length){
        this.length = length;
    }
    
    // vertices constructor, throws exception if distance between all vertices are not equal
    public Octagon(Vertex A, Vertex B, Vertex C, Vertex D, Vertex E, Vertex F, Vertex G, Vertex H) throws vertexValueException{
        check = new Line(A, B).length();
        if(new Line(B, C).length() != check && new Line(C, D).length() != check && new Line(D, E).length() != check && new Line(E, F).length() != check && new Line(F, G).length() != check && new Line(G, H).length() != check)
            throw new vertexValueException("Error: Lengths between all vertices should be equal");
        
        this.A = A;
        this.B = B;
        this.C = C;
        this.D = D;
        this.E = E;
        this.F = F;
        this.G = G;
        this.H = H;
        
        // convert vertex to length
        length = new Line(A, B).length();
    }
    
    // get area
    @Override
    public double area(){
        double area = 2 * (1 + Math.sqrt(2)) * Math.pow(length, 2);
        return area;
    }   
    
    // get perimeter
    @Override
    public double perimeter(){
        return length * 8;
    }
}
