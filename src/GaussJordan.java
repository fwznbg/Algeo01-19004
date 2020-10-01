import java.util.*;
import java.text.*;
import java.lang.Math.*;
import java.io.*;

public class GaussJordan {

    public Matriks gaussjor(Matriks M) {
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
        return M;
    }
    public static void main(String[] args){
        GaussJordan gj= new GaussJordan();
        Gauss g= new Gauss();
        Matriks M=new Matriks(3,4);
        M.bacaMatriks();
        Matriks x;
        x = gj.gaussjor(g.gauss(M));
        x.tulisMatriks();
        
    }
}
