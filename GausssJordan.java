import java.util.*;
import java.text.*;
import java.lang.Math.*;
import java.io.*;

public class GausssJordan {

    public void gaussjor(Matriks M) {
        int n=0;
        double x;
        int j=M.idxKlmOne(1);
        int i=1;
        while(i<=M.getLastIdxBrs()){
            j=M.idxKlmOne(i);
            for (int a=0;a<i;a++){
                M.kurangBaris(a, i, M.getIsi(a, j));
            }
            i++;
        }
        /*while (n<M.getLastIdxKlm()-1){
            i=1;
            a=M.idxKlmOne(i);
            while (i<=M.getLastIdxBrs() && (a+n)<=M.getLastIdxKlm()){
                a=M.idxKlmOne(i);
                x=M.getIsi(a-1, a+n);
                M.kurangBaris(a-1, a+n, x);
                i++;
                System.out.println(a+n);
            }
            n+=1;
        }*/
        M.tulisMatriks();
            
    }
    public static void main(String[] args){
        GausssJordan gj= new GausssJordan();
        Matriks M=new Matriks(4,5);
        M.bacaMatriks();
        gj.gaussjor(M);
    }
}