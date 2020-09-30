import java.util.*;
import java.text.*;
import java.lang.Math.*;
import java.io.*;

public class Cramer
{
    public void cramer(Matriks M)
    {
        double deter, deterNew, hasil;
        int i,j;

        // inisiasi matriks A
        Matriks Ma = new Matriks(M.getBaris(),(M.getKolom())-1);
        for (int a=0; a<=Ma.getLastIdxBrs(); a++)
        {
            for (int b=0; b<=Ma.getLastIdxKlm(); b++)
            {
                Ma.setIsi(a, b, M.getIsi(a, b));
            }
        } 

        // determinan Ma
        DetReduksi det = new DetReduksi();
        deter = det.detRed(Ma);

        // inisiasi matriks B
        Matriks Mb = new Matriks(M.getBaris(),1);
        for (int c=0; c<=Mb.getLastIdxBrs(); c++)
        {
            Mb.setIsi(c, 0, M.getIsi(c, M.getLastIdxKlm()));
        } 

        // inisiasi matriks hasil
        Matriks Mhasil = new Matriks(1, (M.getKolom())-1);

        // looping untuk mencari hasil
        for (j=0; j<=Ma.getLastIdxKlm(); j++)
        {
            // inisiasi matriks baru untuk dicari determinan nya
            Matriks Mnew = new Matriks(M.getBaris(),(M.getKolom())-1);
            for (int m=0; m<=Mnew.getLastIdxBrs(); m++)
            {
                for (int n=0; n<=Mnew.getLastIdxKlm(); n++)
                {
                    Mnew.setIsi(m, n, M.getIsi(m, n));
                }
            } 

            // mengganti isi setiap baris di kolom j menjadi isi di matriks Mb
            for (i=0; i<=Ma.getLastIdxBrs(); i++)
            {
                Mnew.setIsi(i, j, Mb.getIsi(i, 0));
            }

            Mnew.tulisMatriks();

            // mencari determinan matriks Mnew yang sudah isi kolom j nya sudah diganti
            deterNew = det.detRed(Mnew);
            System.out.println(deterNew);

            // mencari hasil dan dimasukan ke matriks Mhasil
            hasil = deterNew/deter;
            Mhasil.setIsi(0, j, hasil);
        }

        Mhasil.tulisMatriks();
    }

    public static void main(String[] args)
    {
        Cramer R = new Cramer();
        Matriks M = new Matriks(3,4);
        M.bacaMatriks();
        R.cramer(M);
    }
}