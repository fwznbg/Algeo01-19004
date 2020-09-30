import java.util.*;
import java.text.*;
import java.lang.Math.*;
import java.io.*;

public class Inverse
{
    public void inverse(Matriks M)
    {
        if (M.getBaris()==M.getKolom())     // jumlah baris dan kolom sama
        {
            int i,j;
            // inisiasi matriks baru, diawal dibentuk menjadi matriks identitas dahulu
            Matriks Minv = new Matriks(M.getBaris(),M.getKolom());
            for (i=0; i<=Minv.getLastIdxBrs(); i++)
            {
                for (j=0; j<=Minv.getLastIdxKlm(); j++)
                {
                    if (i==j)
                    {
                        Minv.setIsi(i, j, 1);
                    }
                    else
                    {
                        Minv.setIsi(i, j, 0);
                    }
                }
            }

            // membuat matriks segitiga atas
            for(j=0; j<M.getLastIdxKlm(); j++){
                if(M.getIsi(j, j) == 0){
                    System.out.println("Matriks tidak memiliki balikan");
                    break;
                }
                for(i=j+1; i<=M.getLastIdxBrs(); i++){
                    double ratio = M.getIsi(i, j)/M.getIsi(j, j);
                    M.kaliSkalar(i, 1/(M.getIsi(j, j)));
                    Minv.kaliSkalar(i, 1/(Minv.getIsi(j, j)));
                    M.kurangBaris(i, j, ratio);
                    Minv.kurangBaris(i, j, ratio);
                }
            }

            // membuat matriks segitiga bawah
            for(j=1; j<=M.getLastIdxKlm(); j++){
                if(M.getIsi(j, j) == 0){
                    System.out.println("Matriks tidak memiliki balikan");
                    break;
                }
                for(i=0; i<j; i++){
                    double ratio = M.getIsi(i, j)/M.getIsi(j, j);
                    M.kurangBaris(i, j, ratio);
                    Minv.kurangBaris(i, j, ratio);
                }
            }

            // baris paling bawah dikali -1
            Minv.kaliSkalar(Minv.getLastIdxBrs(), -1);

            Minv.tulisMatriks();
        }
        else
        {
            System.out.println("Matriks tidak memiliki balikan");
        }
    }
    public static void main(String[] args)
    {
        Inverse I = new Inverse();
        Matriks M = new Matriks(3,3);
        M.bacaMatriks();
        I.inverse(M);
    }
}