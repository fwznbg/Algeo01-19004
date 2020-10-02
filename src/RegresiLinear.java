import java.util.*;
import java.text.*;
import java.lang.Math.*;
import java.io.*;

public class RegresiLinear {
    public void regresi () {
        Scanner xinput = new Scanner(System.in);
        System.out.println("Masukkan jumlah variabel");
        int n = xinput.nextInt();
        System.out.println("Masukkan jumlah data");
        int data = xinput.nextInt();
        while (data<n){
            System.out.println("jumlah data tidak mencukupi!");
            data = xinput.nextInt();
        }
        double Elmt;
        Matriks M= new Matriks(data, n+1);
        System.out.println("Masukkan nilai x dan y");
        System.out.print("y ");
        for (int j=0;j<n;j++){
            System.out.print("x"+(j+1)+" ");
        }
        System.out.println();
        M.bacaMatriks();
        Matriks Spl= new Matriks(n+1, n+2); //inisiasi matriks
        Spl.setIsi(0, 0, data);
        for (int i=1; i<n+1;i++){ //mengisi Matriks Spl kolom dan baris pertama matriks spl (selain 0,0) Matriks M
            double sum=0;
            for(int a=0;a<data;a++){
                sum+=M.getIsi(a, i);
            }
            Spl.setIsi(i, 0, sum);
            Spl.setIsi(0, i, sum);
        }
        for (int i=1; i<n+1;i++){ //mengisi Matriks Spl kolom (selain 1 dan n+1) dan baris (selain n)  matriks spl (selain 0,0) dari Matriks M
            for (int j=1;j<n+1;j++){
                double sum=0;
                for(int a=0;a<data;a++){
                    sum+=M.getIsi(a, i)*M.getIsi(a, j);
                }
                Spl.setIsi(i, j, sum);
            }
        }
        for (int i=0;i<n+1;i++){ //mengisi kolom terakhir
            double sum=0;
            if (i==0){
                for(int a=0;a<data;a++){
                    sum+=M.getIsi(a, 0);
                }
            }
            else{
                for(int a=0;a<data;a++){
                    sum+=M.getIsi(a, 0)*M.getIsi(a, i);
                }
            }
            Spl.setIsi(i, n+1, sum);
        }
        Gauss g= new Gauss();
        GaussJordan gj=new GaussJordan();
        Matriks spl;
        spl = gj.gaussjor(g.gauss(Spl));
        if (spl.isBarisNol(spl.getLastIdxBrs()) || spl.isNoSolution(spl.getLastIdxBrs())){
            System.out.println("Data tidak dapat diregresi");
        }
        else{
            Matriks beta= new Matriks(1, n);
            for (int i=0;i<=beta.getLastIdxBrs();i++){
                beta.setIsi(0, i, spl.getIsi(i, spl.getLastIdxKlm()));
            }
            System.out.println("Persamaan regresinya adalah");
            System.out.print("y = ");
            double sum=0;
            for (int j=0;j<=beta.getLastIdxKlm();j++){
                if (j!=beta.getLastIdxKlm()){
                    System.out.print(beta.getIsi(0, j)+"(x"+(j+1)+")+");
                }
                else{
                    System.out.print(beta.getIsi(0, j)+"(x"+(j+1)+")");
                }
            }
            System.out.println();
            Matriks k= new Matriks(1, n);
            System.out.println("Masukkan nilai-nilai yang ingin di taksir");
            for (int j=0;j<n;j++){
                System.out.print("x"+(j+1)+" ");
            }
            System.out.println();
            k.bacaMatriks();
            for (int j=0;j<=beta.getLastIdxKlm();j++){
                sum+=beta.getIsi(0, j)*k.getIsi(0, j);
            }
            System.out.println("Aproksimasinya adalah "+sum);
        }

    }
    public static void main(String[] args){
        RegresiLinear reg = new RegresiLinear();
        reg.regresi();
        
    }
}
