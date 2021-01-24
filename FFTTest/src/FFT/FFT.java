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
public class FFT {
    public void Transform (Complex input[], Complex output[], int n, boolean bInverse)
    {
        if((n & (n-1)) != 0)
            return;
       Complex tempArray[] = new Complex[n];
       int iCounter, temp, tempIndex;
       int i, j;
       int step = n;
       int group = 2;
       int G_index, H_index, twiddle_K;
       Complex G, H, Multiplier, X;
       
       //reorder input to temp
       for(iCounter = 0; iCounter < n; iCounter++)
       {
           temp = reverse(iCounter, n);
           tempArray[temp] = new Complex(input[iCounter].RE(), input[iCounter].IM());
       }

       while((step >>= 1) > 0)
       {
           for(i=0; i < n; i+=group)
           {
               for(j=0; j < group; j++)
               {
                   tempIndex = i+j;
                   twiddle_K = step * j;
                   if(j < (group / 2))
                   {
                       G_index = tempIndex;
                       H_index = tempIndex + (group / 2);
                   }
                   else
                   {
                       G_index = tempIndex - (group / 2);
                       H_index = tempIndex;
                   }
                   G = tempArray[G_index];
                   H = tempArray[H_index];
                   Multiplier = WKN(twiddle_K, n, bInverse);
                   X = butterfly(G, H, Multiplier);
                   output[tempIndex] = new Complex(X.RE(), X.IM());
               }
           }
           //ccpy output back into temp for next iteration
           for(i=0;i<n;i++)
           {
               tempArray[i] = new Complex(output[i].RE(), output[i].IM());
           }
           group <<= 1;
       }
       if(bInverse)
       {
           for(i=0;i<n;i++)
           {
               output[i] = new Complex((output[i].RE() / n), (output[i].IM()/n));
           }
       }
    }
    
    private Complex WKN (int K, int N, boolean bInverse)
    {
        double re,im;
        re = Math.cos(2 * Math.PI * K / N);
        im = -Math.sin(2 * Math.PI * K / N);
        if(bInverse)
            im = -im;
        return new Complex(re, im);
    }
    
    private int reverse (int input, int size)
    {
        int temp = size;
        int retval = 0;
        int ctr = 1;
        while((temp >>= 1) != 0)
        {
            retval |= (((ctr & input) > 0) ? temp : 0);
            ctr <<= 1;
        }
        return retval;
    }
    
    private Complex butterfly(Complex D1, Complex D2, Complex Multiplier)
    {
        Complex temp = Complex.multiply(D2, Multiplier);
        temp = Complex.add(temp, D1);
        return temp;
    }
    
}
