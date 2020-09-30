import java.util.*;
import java.io.*;

public class Matriks {
    /** Data matriks */
    int baris, kolom;
    double[][] isimatriks;

    /** Constructor */
    Matriks(int inputBaris, int inputKolom) {
        int i, j;
        this.baris = inputBaris;
        this.kolom = inputKolom;

        // inisialisasi isi matriks dengan nilai -1
        this.isimatriks = new double[this.baris][this.kolom];
        for (i = 0; i < this.baris; i++) {
            for (j = 0; j < this.kolom; j++) {
                isimatriks[i][j] = -1;
            }
        }
    }

    /** Method */

    // Mengembalikan banyak baris
    int getBaris() {
        return this.baris;
    }

    // Mengembalikan banyak kolom
    int getKolom() {
        return this.kolom;
    }

    // Mengembalikan indeks baris terakhir
    int getLastIdxBrs() {
        return this.baris - 1;
    }

    // Mengembalikan indeks kolom terakhir
    int getLastIdxKlm() {
        return this.kolom - 1;
    }

    // Membaca nilai isi matriks dari masukan pengguna
    void bacaMatriks() {
        int i, j;
        Scanner input = new Scanner(System.in);
        for (i = 0; i < this.baris; i++) {
            String a[] = input.nextLine().split(" ");
            for (j = 0; j < this.kolom; j++) {
                this.isimatriks[i][j] = Double.parseDouble(a[j]);
                // System.out.print("Masukkan isi matriks baris ke-"+(i+1)+" kolom ke-"+(j+1)+":
                // ");;
            }
        }
    }

    // Menampilkan isi matriks di layar
    void tulisMatriks() {
        int i;
        for (i = 0; i < this.baris; i++) {
            System.out.println(Arrays.toString(this.isimatriks[i]));
        }
    }

    // Mengalikan setiap nilai di baris i dengan skalar k
    // Indeks baris dimulai dari 0
    void kaliSkalar(int i, double k) {
        int j;
        for (j = 0; j < this.kolom; j++) {
            this.isimatriks[i][j] *= k;
        }
    }

    // Mengurangi setiap nilai di baris i1 dengan x kali nilai baris i2
    void kurangBaris(int i1, int i2, double x) {
        int j;
        for (j = 0; j < this.kolom; j++) {
            this.isimatriks[i1][j] -= this.isimatriks[i2][j] * x;
        }
    }

    // Menukar nilai di baris i1 dengan baris i2
    void tukarBaris(int i1, int i2) {
        int j;
        for (j = 0; j < this.kolom; j++) {
            double tmp = this.isimatriks[i1][j];
            this.isimatriks[i1][j] = this.isimatriks[i2][j];
            this.isimatriks[i2][j] = tmp;
        }
    }

    // getisi
    double getIsi(int i, int j) {
        return this.isimatriks[i][j];
    }

    // setisi
    void setIsi(int i, int j, double x) {
        this.isimatriks[i][j] = x;
    }

    boolean isBarisNol(int i) {
        boolean isNol = true;
        int j = 0;
        while (j <= getLastIdxKlm() && isNol) {
            if (this.isimatriks[i][j] != 0) {
                isNol = false;
            } else {
                j++;
            }
        }
        return (isNol);
    }

    boolean isNoSolution(int i) {
        boolean isNol = true;
        int j = 0;
        while (j < getLastIdxKlm() && isNol) {
            if (this.isimatriks[i][j] != 0) {
                isNol = false;
            }
            j++;
        }
        return ((this.isimatriks[i][getLastIdxKlm()] == 0) && isNol);
    }

    int idxKlmOne(int i) {
        int j = 0;
        boolean isOne = false;
        while (j < getLastIdxKlm() && !isOne) {
            if (this.isimatriks[i][j] == 1) {
                isOne = true;
            } else {
                j++;
            }
        }
        return j;
    }

    // Membaca matriks dari file.txt
    void bacaMatriksFile(String namafile_txt) {
        FileInputStream fis;
        try {
            fis = new FileInputStream(namafile_txt);
            Scanner sc=new Scanner(fis);
            int i, j;
            for(i=0;i<this.baris;i++){
                String a[] = sc.nextLine().split(" ");
                for(j=0;j<this.kolom;j++){
                    this.isimatriks[i][j] = Double.parseDouble(a[j]);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("\nFile tidak ditemukan\n");
        }
    }

    // menentukan apakah elemen 0 atau bukan
    boolean isNol(int i, int j){
        return (this.isimatriks[i][j]==0);
    }

    int idxNotNol(int i){
        int j=0;
        while (j<=getLastIdxKlm() && isNol(i, j)){
            j++;
        }
        return j;
    }
    double detRed(Matriks M){
        int i, j;
        double ratio;
        double determinan = 1;

        // Membuat matriks segitiga
        for(j=0;j<M.getLastIdxKlm();j++){
            if(M.getIsi(j, j) == 0){
                determinan = 0;
            }
            for(i=j+1;i<=M.getLastIdxBrs();i++){
                ratio = M.getIsi(i, j)/M.getIsi(j, j);
                M.kurangBaris(i, j, ratio);
            }
        }
        if(determinan!=0){
        for(i=0;i<=M.getLastIdxBrs();i++){
            determinan *= M.getIsi(i, i);
            }
        }
        return determinan;
    }

    void inverse(Matriks M)
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

    void splinvers(Matriks M){
        int i, j;
        Matriks Minv = new Matriks(M.getBaris(), M.getKolom()-1);   // Matriks yang akan diinvers
        Matriks Mb = new Matriks(M.getBaris(), 1);                 
        // Membuat matriks augmented
        for(i=0;i<=M.getLastIdxBrs();i++){
            for(j=0;j<=M.getLastIdxKlm();j++){
                if(j!=M.getLastIdxKlm()){
                    Minv.setIsi(i, j, M.getIsi(i, j));
                }
                else{
                    Mb.setIsi(i, 0, M.getIsi(i, j));
                }
            }
        }

        // membuat matriks identitas
        Matriks invers = new Matriks(Minv.getBaris(), Minv.getKolom());     // Hasil invers matriks
        for (i=0; i<=invers.getLastIdxBrs(); i++){
                for (j=0; j<=invers.getLastIdxKlm(); j++){
                    if (i==j)
                    {
                        invers.setIsi(i, j, 1);
                    }
                    else{
                        invers.setIsi(i, j, 0);
                    }
                }
            }

        // Inverse

        // Membuat matriks segitiga atas
        for(j=0; j<Minv.getLastIdxKlm(); j++){
            if(Minv.getIsi(j, j) == 0){
                System.out.println("Matriks tidak memiliki balikan");
                break;
            }
            for(i=j+1; i<=Minv.getLastIdxBrs(); i++){
                double ratio = Minv.getIsi(i, j)/Minv.getIsi(j, j);
                Minv.kaliSkalar(i, 1/(Minv.getIsi(j, j)));
                invers.kaliSkalar(i, 1/(invers.getIsi(j, j)));
                Minv.kurangBaris(i, j, ratio);
                invers.kurangBaris(i, j, ratio);
            }
        }
        for(j=1; j<=Minv.getLastIdxKlm(); j++){
            if(Minv.getIsi(j, j) == 0){
                System.out.println("Matriks tidak memiliki balikan");
                break;
            }
            for(i=0; i<j; i++){
                double ratio = Minv.getIsi(i, j)/Minv.getIsi(j, j);
                Minv.kurangBaris(i, j, ratio);
                invers.kurangBaris(i, j, ratio);
            }
        }
        if(Minv.getIsi(Minv.getLastIdxBrs(), Minv.getLastIdxKlm())==-1){
            Minv.kaliSkalar(Minv.getLastIdxBrs(), -1);
            invers.kaliSkalar(invers.getLastIdxBrs(), -1);
        }
        // perkalian matriks invers dan Mb
        Matriks hasil = new Matriks(invers.getBaris(), 1);
        for(i=0;i<=invers.getLastIdxBrs();i++){
            double sum = 0;
            for(j=0;j<=Mb.getLastIdxKlm();j++){
                for(int k =0;k<=Mb.getLastIdxBrs();k++){
                    sum += invers.getIsi(i, k)*Mb.getIsi(k, j);
                }
            }
            hasil.setIsi(i, 0, sum);
        }
        hasil.tulisMatriks();
    }

    void gaussjor(Matriks M) {
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

    void interpolasi(){
        int n, i, j;
        double x, y;
        Scanner scanner = new Scanner(System.in);
        System.out.print("Masukkan banyak titik: ");
        n = scanner.nextInt();

        Matriks titik = new Matriks(n, 2);
        for(i=0;i<titik.getBaris();i++){
            System.out.print("Masukkan nilai x"+i+": ");
            x = scanner.nextDouble();
            titik.setIsi(i, 0, x);
            System.out.print("Masukkan nilai y"+i+": ");
            y = scanner.nextDouble();
            titik.setIsi(i, 1, y);
        }
        scanner.close();
        Matriks intpls = new Matriks(n, n+1);
        for(i=0;i<intpls.getBaris();i++){
            intpls.setIsi(i, 0, 1);
            for(j=1;j<intpls.getKolom();j++){
                if(j != intpls.getLastIdxKlm()){
                    intpls.setIsi(i, j, Math.pow(titik.getIsi(i, 0), j));
                }
                else{
                    intpls.setIsi(i, j, titik.getIsi(i, 1));
                }
            }
        }
        
        //tinggal nunggu fungsi gaussnya
        intpls.tulisMatriks();
    }

    void regresi () {
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
    }
    // public static void main(String[] args){
    //     Matriks matriks1 = new Matriks(4, 4);
    //     matriks1.bacaMatriksFile("inpu.txt");
    //     matriks1.tulisMatriks();
    // }
}
