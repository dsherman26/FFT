/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FFT;

/**
 *
 * @author daves
 */
public class Complex {
    private final double real;
    private final double imaginary;
    public Complex ()
    {
        real = 0.0;
        imaginary = 0.0;
    }
    public Complex (double a, double b)
    {
        real = a;
        imaginary = b;
    }
    
    public double RE()
    {
        return this.real;
    }
    
    public double IM()
    {
        return this.imaginary;
    }
    
    public static Complex add (Complex a, Complex b)
    {
        return new Complex((a.RE() + b.RE()), (a.IM() + b.IM()));
    }
    
    public static Complex multiply (Complex a, Complex b)
    {
        double mreal = ((a.RE() * (b.RE())) - (a.IM() * b.IM()));
        double mimaginary = ((a.RE() * b.IM()) + (a.IM() * b.RE()));
        return new Complex(mreal, mimaginary);
    }
}
