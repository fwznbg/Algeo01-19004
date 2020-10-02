import java.util.*;
import java.io.*;
// import java.lang.Double;

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
                isimatriks[i][j] = -1.0;
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
    void bacaMatriksFile() {
        FileInputStream fis;
        try {
            fis = new FileInputStream("test/input.txt");
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

    boolean isDiagonalOne(){
        boolean isOne=true;
        int i=0;
        while (i<=getLastIdxBrs() && isOne){
            if (this.isimatriks[i][i]!=1){
                isOne=false;
            }
            i++;
        }
        return isOne;
    }
    // Menulis variabel bertipe double dan bukan matriks ke file
    void tulisDoubleFile(double x){
        try {
            FileWriter myWriter = new FileWriter("test/output.txt");
            myWriter.write(""+x);
            System.out.println("File berhasil disimpan");
            myWriter.close();
        } 
        catch (IOException e) {
            System.out.println("File gagal disimpan.");
        }
    }

    // Menulis matriks M ke file
    void tulisMatriksFile(Matriks M){
        try {
            FileWriter myWriter = new FileWriter("test/output.txt");
            for(int m=0; m<=M.getLastIdxBrs();m++){
                myWriter.write(Arrays.toString(M.isimatriks[m])+"\n");
            }
            System.out.println("File berhasil disimpan");
            myWriter.close();
            } catch (IOException e) {
            System.out.println("File gagal disimpan.");
        }
    }
    double detRed(Matriks M){
        int i, j;
        int tkrbrs = 0;
        double ratio;
        double determinan = 1;

        // Membuat matriks segitiga
        for(j=0;j<M.getLastIdxKlm();j++){
            int k = j+1;
            while(M.getIsi(j, j) == 0 && k<=M.getLastIdxBrs()){
                M.tukarBaris(j, k);
                tkrbrs++;
                k++;
            }
            for(i=j+1;i<=M.getLastIdxBrs();i++){
                ratio = M.getIsi(i, j)/M.getIsi(j, j);
                M.kurangBaris(i, j, ratio);
            }
        }
        for(i=0;i<=M.getLastIdxBrs();i++){
            determinan *= M.getIsi(i, i);
        }
        return determinan*Math.pow(-1, tkrbrs);
    }

    boolean inverse(Matriks M)
    {
        boolean inv = true;
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
                    inv = false;
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
                    inv = false;
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

            // mwngisi matriks inputan (M) dengan Minv
            for(int m=0; m<Minv.getBaris();m++){
                for(int n=0; n<Minv.getKolom();n++){
                    M.setIsi(m, n, Minv.getIsi(m, n));
                }
            }
        }
        return inv;
    }

    boolean splinvers(Matriks M){
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
        boolean inv = true;
        // Membuat matriks segitiga atas
        for(j=0; j<Minv.getLastIdxKlm(); j++){
            if(Minv.getIsi(j, j) == 0){
                System.out.println("Matriks tidak memiliki balikan, hasil tidak dapat ditemukan");
                inv = false;
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
                System.out.println("Matriks tidak memiliki balikan, hasil tidak dapat ditemukan");
                inv = false;
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
        for(int m=0; m<hasil.getBaris();m++){
            M.setIsi(m, 0, hasil.getIsi(m, 0));
        }
        return inv;
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
        System.out.println("");
        M.tulisMatriks();
        System.out.println("");
    }

       Matriks gauss(Matriks M) {
        int i,j;
        i=0;
        j=0;
        for (i=0;i<=M.getLastIdxBrs();i++){ //iterasi tiap baris
            int a=i;
            boolean z=true;
            boolean tertukar=false;
            while (a<=M.getLastIdxKlm() && !tertukar){
                int b=i;
                while(b<=M.getLastIdxBrs() && M.isNol(b, a)){
                    z=M.isNol(b, a);
                    b++;
                }
                if(b<=M.getLastIdxBrs())
                    z=M.isNol(b, a);
                    if (z==false){
                        M.tukarBaris(i, b);
                        tertukar=true;
                    }
                a++;
            }
            if (!M.isBarisNol(i) || !M.isNoSolution(i)){
                j=M.idxNotNol(i); //mencari elemen pertama tidak 0 pada suatu baris
                M.kaliSkalar(i, +(1/M.getIsi(i, j))); //mengubah elemen menjadi lead eselon
                
                for(int c=i+1;c<=M.getLastIdxBrs();c++){ //menjadikan setiap baris pada kolom j menjadi 0
                    M.kurangBaris(c, i, M.getIsi(c, j));
                }
            }

        }
        for (i=0;i<=M.getLastIdxBrs();i++){
            for (j=0;j<=M.getLastIdxBrs();j++){
                M.setIsi(i, j, M.getIsi(i, j)+0.0);
            }
        }
        System.out.println("");
        M.tulisMatriks();
        System.out.println("");
        return M;   
    }
    
    Matriks cramer(Matriks M)
    {
        double deter, deterNew, hasil;
        int i,j,h;

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
        deter = Ma.detRed(Ma);

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

            // mencari determinan matriks Mnew yang sudah isi kolom j nya sudah diganti
            deterNew = Mnew.detRed(Mnew);

            // mencari hasil dan dimasukan ke matriks Mhasil
            hasil = deterNew/deter;
            Mhasil.setIsi(0, j, hasil);
        }

        // print out hasil
        for (h=0; h<=Mhasil.getLastIdxKlm(); h++)
        {
            M.setIsi(0, h, Mhasil.getIsi(0, h));
        }
        return M;
    }
    
    boolean solveGauss (Matriks M) {
        boolean error = true;
        if (M.isDiagonalOne()){
            error = false;
            }
        return error;
        }

        
    double deterkofak(Matriks M){
        double det = 0;
        if(M.getBaris()==2){
            det = (M.getIsi(0, 0)*M.getIsi(1, 1)) - (M.getIsi(0, 1)*M.getIsi(1, 0));
        }
        else{ // Matriks nxn dengan n>2
            for(int i=0;i<=M.getLastIdxBrs();i++){
                Matriks cof = new Matriks(M.getBaris()-1, M.getKolom()-1);
                for(int m = 0;m<=M.getLastIdxBrs();m++){
                    for(int n = 1;n<=M.getLastIdxBrs();n++){
                        if(m!=i){
                            if(m>i){
                                cof.setIsi(m-1, n-1, M.getIsi(m, n));
                            }
                            else{
                                cof.setIsi(m, n-1, M.getIsi(m, n));
                            }
                        }
                    }
                }
                if(i%2 == 0){
                    det += M.getIsi(i, 0)*deterkofak(cof);
                }
                else{
                    det -= M.getIsi(i, 0)*deterkofak(cof);
                }
            } 
        }
        return det;
    }

    Matriks invKof(Matriks M){
        //membuat matriks kofaktor
        Matriks cof = new Matriks(M.getBaris(), M.getKolom());
        for(int i =0;i<=M.getLastIdxBrs();i++){
            for(int j=0;j<=M.getLastIdxKlm();j++){
                Matriks Mdet = new Matriks(cof.getBaris()-1, cof.getKolom()-1);
                for(int m=0;m<=cof.getLastIdxBrs();m++){
                    for(int n=0;n<=cof.getLastIdxKlm();n++){
                        if(m!=i && n!=j){
                            if(m>i && n>j){
                                Mdet.setIsi(m-1, n-1, M.getIsi(m, n));
                            }
                            else if(m>i){
                                Mdet.setIsi(m-1, n, M.getIsi(m, n));
                            }
                            else if(n>j){
                                Mdet.setIsi(m, n-1, M.getIsi(m, n));
                            }
                            else{
                                Mdet.setIsi(m, n, M.getIsi(m, n));
                            }
                        }
                    }
                }
                if(i%2 == 0){
                    if(j%2!=0){
                        cof.setIsi(i, j, (-1)*Mdet.deterkofak(Mdet));
                    }
                    else{
                        cof.setIsi(i, j, Mdet.deterkofak(Mdet));
                    }
                }
                else{
                    if(j%2==0){
                        cof.setIsi(i, j, (-1)*Mdet.deterkofak(Mdet));
                    }
                    else{
                        cof.setIsi(i, j, Mdet.deterkofak(Mdet));
                    }
                }
            }
        }
        // Tranpose
        Matriks adj = new Matriks(cof.getBaris(), cof.getKolom());
        for(int i = 0;i<=cof.getLastIdxBrs();i++){
            for(int j = 0;j<=cof.getLastIdxKlm();j++){
                adj.setIsi(i, j, cof.getIsi(j, i));
            }
        }
        //kali dengan 1/det
        for(int i=0;i<=M.getLastIdxBrs();i++){
            adj.kaliSkalar(i, 1/M.deterkofak(M));
        }
        return adj;
    }

    int solveGJ (Matriks M) {
        int kondisi = 0;
        if (M.isBarisNol(M.getLastIdxBrs())){
            kondisi = 1;
        }
        else if(M.isNoSolution(M.getLastIdxBrs())){
            kondisi = 2;
        }
        else{
            kondisi = 3;
        }
        return kondisi;
    }

    void tulisSolveGauss(Matriks M){
        double a;
        for (int i=M.getLastIdxBrs();i>=0;i--){
            a=M.getIsi(i, M.getLastIdxKlm());
            if (i==M.getLastIdxBrs()){
                System.out.print("X"+(i+1)+" = "+a+";");
            }
            else{
                double sum=a;
                double x;
                System.out.print("X"+(i+1)+" = "+sum);
                for (int j=M.getLastIdxKlm()-1;j>M.idxKlmOne(i);j--){
                    x=M.getIsi(i, j)*M.getIsi(j, M.getLastIdxKlm());
                    sum-=x;
                    System.out.print("-"+x);
                }
                System.out.print(" = "+sum+";");
            }
            System.out.println();
        }
    }
    int idxBrsNotNol(int j){
        int i=0;
        while (i<=getLastIdxBrs() && isNol(i, j)){
            i++;
        }
        return i;
    }

    boolean isIdxAfterKlmNotNol(int i, int j){
        int a=j+1;
        while (a<getLastIdxKlm() && isNol(i, a)){
            a++;
        }
        return a!=getLastIdxKlm();
    }

    void tulisSolveGaussFile(Matriks M){
        try {
            FileWriter myWriter = new FileWriter("test/output.txt");
            double a;
            for (int i=M.getLastIdxBrs();i>=0;i--){
                a=M.getIsi(i, M.getLastIdxKlm());
                if (i==M.getLastIdxBrs()){
                    myWriter.write("X"+(i+1)+" = "+a+";");
                }
                else{
                    double sum=a;
                    double x;
                    myWriter.write("X"+(i+1)+" = "+sum);
                    for (int j=M.getLastIdxKlm()-1;j>M.idxKlmOne(i);j--){
                        x=M.getIsi(i, j)*M.getIsi(j, M.getLastIdxKlm());
                        sum-=x;
                        myWriter.write("-"+x);
                    }
                    myWriter.write(" = "+sum+";");
                }
                myWriter.write("\n");
            }
            System.out.println("File berhasil disimpan");
            myWriter.close();
            } catch (IOException e) {
            System.out.println("File gagal disimpan.");
        }
    }
    
    void tulisSolveGaussJFile(Matriks M){
        try {
            FileWriter myWriter = new FileWriter("test/output.txt");
            switch(M.solveGJ(M)){
                case 1:
                    int a=M.getLastIdxBrs();
                    while(M.isBarisNol(a) && a>=0){
                        a--;
                    }
                    if (a!=0){
                        for (int j=M.getLastIdxKlm()-1;j>=0;j--){
                            int z=M.idxBrsNotNol(j);
                            double elmt=1/M.getIsi(z, j);
                            myWriter.write("x"+(j+1)+" = ");
                            if (!M.isIdxAfterKlmNotNol(z, j)){
                                if(M.idxNotNol(z)!=j){
                                    myWriter.write(elmt+"("+M.getIsi(z, M.getLastIdxKlm()));
                                    for (int b=j-1;b>=M.idxNotNol(z);b--){
                                        if(!M.isNol(z, b)){
                                            if (M.getIsi(z, b)>=0){
                                                myWriter.write(M.getIsi(z, b)*-1+"(u"+(b+1)+")");
                                            }
                                            else{
                                                myWriter.write("+"+M.getIsi(z, b)*-1+"(u"+(b+1)+")");
                                            }
                                        }
                                    }
                                    myWriter.write(")");
                                }
                                else{
                                    myWriter.write(""+elmt*M.getIsi(z, M.getLastIdxKlm()));
                                }
                            }
                            else{
                                myWriter.write("u"+(j+1));
                            }
                            myWriter.write("\n");
                        }
                    }
                    else{
                        myWriter.write("Matriks memiliki jumlah solusi yang tak terhingga\n");
                    }
                    break;
                case 2:
                    myWriter.write("Matriks tidak memiliki solusi\n");
                    break;
                case 3:
                    Matriks Sol= new Matriks(1, M.getLastIdxKlm());
                    for (int i=0;i<=M.getLastIdxBrs();i++){
                        myWriter.write("x"+(i+1)+" = "+M.getIsi(i, M.getLastIdxKlm())+"\n");
                        Sol.setIsi(0, i, M.getIsi(i, M.getLastIdxKlm()));
                    }
                    break;
            }
            System.out.println("File berhasil disimpan");
            myWriter.close();
            } catch (IOException e) {
            System.out.println("File gagal disimpan.");
        }
    }
    void tulisSolveGaussJ(Matriks M){
        switch(M.solveGJ(M)){
            case 1:
                int a=M.getLastIdxBrs();
                while(M.isBarisNol(a) && a>=0){
                    a--;
                }
                if (a!=0){
                    for (int j=M.getLastIdxKlm()-1;j>=0;j--){
                        int z=M.idxBrsNotNol(j);
                        double elmt=1/M.getIsi(z, j);
                        System.out.print("x"+(j+1)+" = ");
                        if (!M.isIdxAfterKlmNotNol(z, j)){
                            if(M.idxNotNol(z)!=j){
                                System.out.print(elmt+"("+M.getIsi(z, M.getLastIdxKlm()));
                                for (int b=j-1;b>=M.idxNotNol(z);b--){
                                    if(!M.isNol(z, b)){
                                        if (M.getIsi(z, b)>=0){
                                            System.out.print(M.getIsi(z, b)*-1+"(u"+(b+1)+")");
                                        }
                                        else{
                                            System.out.print("+"+M.getIsi(z, b)*-1+"(u"+(b+1)+")");
                                        }
                                    }
                                }
                                System.out.print(")");
                            }
                            else{
                                System.out.print(elmt*M.getIsi(z, M.getLastIdxKlm()));
                            }
                        }
                        else{
                            System.out.print("u"+(j+1));
                        }
                        System.out.println();
                    }
                }
                else{
                    System.out.println("Matriks memiliki jumlah solusi yang tak terhingga");
                }
                break;
            case 2:
                System.out.println("Matriks tidak memiliki solusi");
                break;
            case 3:
                Matriks Sol= new Matriks(1, M.getLastIdxKlm());
                for (int i=0;i<=M.getLastIdxBrs();i++){
                    System.out.println("x"+(i+1)+" = "+M.getIsi(i, M.getLastIdxKlm()));
                    Sol.setIsi(0, i, M.getIsi(i, M.getLastIdxKlm()));
                }
                break;
        }
    }
    Matriks interpolasi(Matriks titik){
        int n, i, j;
        double x, y;
        Matriks intpls = new Matriks(titik.getBaris(), titik.getBaris()+1);
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
        intpls = intpls.gauss(intpls);
        intpls.gaussjor(intpls);
        return intpls;
    }

    void tulisintpl(Matriks M, double x){
        if (M.isBarisNol(M.getLastIdxBrs()) || M.isNoSolution(M.getLastIdxBrs())){
            System.out.println("Data tidak dapat diinterpolasi");
        }
        else{
            double taksiran = 0;
            Matriks beta= new Matriks(1, M.getBaris());
            for (int i=0;i<=beta.getLastIdxKlm();i++){
                beta.setIsi(0, i, M.getIsi(i, M.getLastIdxKlm()));
            }
            System.out.println("Persamaan interpolasinya adalah");
            System.out.print("y = ");
            double sum=0;
            for (int j=0;j<=beta.getLastIdxKlm();j++){
                if (j!=beta.getLastIdxKlm()){
                    System.out.print(beta.getIsi(0, j)+"(x"+(j+1)+")+");
                    taksiran += beta.getIsi(0, j)*Math.pow(x, j);
                }
                else{
                    System.out.print(beta.getIsi(0, j)+"(x"+(j+1)+")");
                    taksiran += beta.getIsi(0, j)*Math.pow(x, (j+1));
                }
            }
            System.out.println("\nAproksimasinya adalah "+taksiran);
        }
    }

    void tulisintplFile(Matriks M, double x){
        try {
            FileWriter myWriter = new FileWriter("test/output.txt");
            if (M.isBarisNol(M.getLastIdxBrs()) || M.isNoSolution(M.getLastIdxBrs())){
                myWriter.write("Data tidak dapat diinterpolasi");
            }
            else{
                double taksiran = 0;
                Matriks beta= new Matriks(1, M.getBaris());
                for (int i=0;i<=beta.getLastIdxKlm();i++){
                    beta.setIsi(0, i, M.getIsi(i, M.getLastIdxKlm()));
                }
                myWriter.write("Persamaan interpolasinya adalah\n");
                myWriter.write("y = ");
                double sum=0;
                for (int j=0;j<=beta.getLastIdxKlm();j++){
                    if (j!=beta.getLastIdxKlm()){
                        myWriter.write(beta.getIsi(0, j)+"(x"+(j+1)+")+");
                        taksiran += beta.getIsi(0, j)*Math.pow(x, j);
                    }
                    else{
                        myWriter.write(beta.getIsi(0, j)+"(x"+(j+1)+")");
                        taksiran += beta.getIsi(0, j)*Math.pow(x, (j+1));
                    }
                }
                myWriter.write("\nAproksimasinya adalah "+taksiran);
            }
            System.out.println("File berhasil disimpan");
            myWriter.close();
            } catch (IOException e) {
            System.out.println("File gagal disimpan.");
        }
    }

    Matriks regresi (Matriks M) {
        Matriks Spl= new Matriks(M.getKolom(), M.getKolom()+1); //inisiasi matriks
        Spl.setIsi(0, 0, M.getBaris());
        for (int i=1; i<M.getKolom();i++){ //mengisi Matriks Spl kolom dan baris pertama matriks spl (selain 0,0) Matriks M
            double sum=0;
            for(int a=0;a<M.getBaris();a++){
                sum+=M.getIsi(a, i);
            }
            Spl.setIsi(i, 0, sum);
            Spl.setIsi(0, i, sum);
        }
        for (int i=1; i<M.getKolom();i++){ //mengisi Matriks Spl kolom (selain 1 dan n+1) dan baris (selain n)  matriks spl (selain 0,0) dari Matriks M
            for (int j=1;j<M.getKolom();j++){
                double sum=0;
                for(int a=0;a<M.getBaris();a++){
                    sum+=M.getIsi(a, i)*M.getIsi(a, j);
                }
                Spl.setIsi(i, j, sum);
            }
        }
        for (int i=0;i<M.getKolom();i++){ //mengisi kolom terakhir
            double sum=0;
            if (i==0){
                for(int a=0;a<M.getBaris();a++){
                    sum+=M.getIsi(a, 0);
                }
            }
            else{
                for(int a=0;a<M.getBaris();a++){
                    sum+=M.getIsi(a, 0)*M.getIsi(a, i);
                }
            }
            Spl.setIsi(i, M.getKolom(), sum); 
        }
        return Spl ;
    }
        
    void tulisregresi(Matriks Spl){
        Matriks spl;
        spl = Spl.gauss(Spl);
        spl.gaussjor(spl);
        if (spl.isBarisNol(spl.getLastIdxBrs()) || spl.isNoSolution(spl.getLastIdxBrs())){
            System.out.println("Data tidak dapat diregresi");
        }
        else{
            Matriks beta= new Matriks(1, Spl.getBaris()-1);
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
            Matriks k= new Matriks(1, Spl.getBaris()-1);
            System.out.println("Masukkan nilai-nilai yang ingin di taksir");
            for (int j=0;j<Spl.getBaris()-1;j++){
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

    void tulisregresiFile(Matriks Spl){
        try {
            FileWriter myWriter = new FileWriter("test/output.txt");
            Matriks spl;
            spl = Spl.gauss(Spl);
            spl.gaussjor(spl);
            if (spl.isBarisNol(spl.getLastIdxBrs()) || spl.isNoSolution(spl.getLastIdxBrs())){
                myWriter.write("Data tidak dapat diregresi");
            }
            else{
                Matriks beta= new Matriks(1, Spl.getBaris()-1);
                for (int i=0;i<=beta.getLastIdxBrs();i++){
                    beta.setIsi(0, i, spl.getIsi(i, spl.getLastIdxKlm()));
                }
                myWriter.write("Persamaan regresinya adalah\n");
                myWriter.write("y = ");
                double sum=0;
                for (int j=0;j<=beta.getLastIdxKlm();j++){
                    if (j!=beta.getLastIdxKlm()){
                        myWriter.write(beta.getIsi(0, j)+"(x"+(j+1)+")+");
                    }
                    else{
                        myWriter.write(beta.getIsi(0, j)+"(x"+(j+1)+")");
                    }
                }
                System.out.println();
                Matriks k= new Matriks(1, Spl.getBaris()-1);
                System.out.println("Masukkan nilai-nilai yang ingin di taksir");
                for (int j=0;j<Spl.getBaris()-1;j++){
                    System.out.print("x"+(j+1)+" ");
                }
                System.out.println();
                k.bacaMatriks();
                for (int j=0;j<=beta.getLastIdxKlm();j++){
                    sum+=beta.getIsi(0, j)*k.getIsi(0, j);
                }
                myWriter.write("Aproksimasinya adalah "+sum+"\n");
            }

            System.out.println("File berhasil disimpan");
            myWriter.close();
            } catch (IOException e) {
            System.out.println("File gagal disimpan.");
        }
        
    }
}