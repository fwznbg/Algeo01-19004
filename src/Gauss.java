import java.util.*;
import java.text.*;
import java.lang.Math.*;
import java.io.*;

public class Gauss {

    public void gauss(Matriks M) {
        int i,j;
        i=0;
        j=0;
        while (i<=M.getLastIdxBrs() && M.isNol(i, j)){ //cek elmt 0,0 apakah 0
            i++;
        }
        if (M.isNol(i, j)==false){
            M.tukarBaris(0, i); //elmt 0,0 sudah bukan 0
        }
        i=0;
        while(i<=M.getLastIdxBrs()){ //iterasi tiap baris
            j=M.idxNotNol(i); //mencari elemen pertama tidak 0 pada suatu baris
            M.kaliSkalar(i, (1/M.getIsi(i, j))); //mengubah elemen menjadi lead eselon
            for(int a=i+1;a<=M.getLastIdxBrs();a++){ //menjadikan setiap baris pada kolom j menjadi 0
                M.kurangBaris(a, i, M.getIsi(a, j));
            }
            i++;
        }
        M.tulisMatriks();
            
    }
    public static void main(String[] args){
        Gauss g= new Gauss();
        Matriks M=new Matriks(3,4);
        M.bacaMatriks();
        g.gauss(M);
    }
}
