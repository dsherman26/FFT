/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ffttest;
import FFT.*;
/**
 *
 * @author daves
 */
public class FFTTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        int iCounter;
        FFT myFFT = new FFT();
        Complex input[] = new Complex[32768];
        Complex output[] = new Complex[32768];
        for(iCounter = 0; iCounter < 32768; iCounter++)
        {
            if(iCounter < 16384)
                input[iCounter] = new Complex(1.0, 0);
            else
                input[iCounter] = new Complex(-1.0, 0);
        }
        myFFT.Transform(input, output, 32768, false);
        for(iCounter = 0; iCounter < 32768; iCounter++)
        {
            System.out.print(iCounter);
            System.out.print("   ");
            System.out.print(output[iCounter].RE());
            System.out.print("  ");
            System.out.println(output[iCounter].IM());
        }
        myFFT.Transform(output, input, 32768, true);
        for(iCounter=0;iCounter<32768;iCounter++)
        {
            System.out.print(iCounter);
            System.out.print("   ");
            System.out.print(input[iCounter].RE());
            System.out.print("   ");
            System.out.println(input[iCounter].IM());
        }
    }
    
}
