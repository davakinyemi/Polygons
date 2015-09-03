/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author dav
 */
public class Quadrilateral implements Polygon{
    protected double base, left, top, right, diagonal, area, perimeter;    
    protected Vertex A, B, C, D;
    
    // length constructor
    public Quadrilateral(Vertex A, Vertex B, Vertex C, Vertex D){
        this.A = A;
        this.B = B;
        this.C = C;
        this.D = D;
        
        // convert vertices to length
        this.base = new Line(A, B).length();
        this.right = new Line(B, C).length();
        this.top = new Line(C, D).length();
        this.left = new Line(D, A).length();
        this.diagonal = new Line(A, C).length();
    }
    
    // length constructor
    public Quadrilateral(double base, double right, double top, double left){
        this.base = base;
        this.right = right;
        this.top = top;
        this.left = left;
    }
    
    // get area of shape
    @Override
    public double area(){
        double semi1 = (base + right + diagonal) / 2;
	double semi2 = (diagonal + top + left) / 2;
	area = Math.sqrt(semi1 * (semi1 - base) * (semi1 - right) * (semi1 - diagonal))  +
	    Math.sqrt(semi2 * (semi2 - top) * (semi2 - left) * (semi2 - diagonal));        
        return area;
    }
    
    // get perimeter of shape
    @Override
    public double perimeter(){
        perimeter = base + left + top + right;        
        return perimeter;
    }
}
