import java.util.*;
import java.text.*;
import java.lang.Math.*;
import java.io.*;

public class RegresiLinear {
    public void regresi () {
        Scanner xinput = new Scanner(System.in);
        int n = xinput.nextInt();
        int k = xinput.nextInt();
        double Elmtx;
        Matriks M= new Matriks(k+1, k+2); //inisiasi matriks
        M.setIsi(0, 0, 1); // inisialisasi elemen 0,0 dengan 1
        for (int j=0; j<k+2;j++){ 
            Elmtx= xinput.nextDouble();
            for (int i=0;i<k+1;i++){
                if(i==0 && j<k-1){
                    M.setIsi(i, j+1, Elmt);
                }
                if (i!=0){
                    M.setIsi(i, j, Elmt);
                }
            }
        double Elmty;
        Elmty= xinput.nextDouble();
        for (int i=0;i<k+1;i++){
            M.setIsi(k+1, j, x);
        }
        for (int i=1;i<k;i++){
            for (int j=1; j<k;j++){
                M.kaliSkalar(i, M.getIsi(i, 0));
            }
        }
        M.tulisMatriks();  
    }
    public static void main(String[] args){
        RegresiLinear reg = new RegresiLinear();
        reg.regresi();
    }
}