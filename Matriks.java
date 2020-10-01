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
        
        intpls = intpls.gauss(intpls);
        //nunggu solve gauss
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
    // public static void main(String[] args){
    //     Matriks matriks1 = new Matriks(3, 4);
    //     matriks1.bacaMatriks();
    //     if(matriks1.splinvers(matriks1)){
    //         System.out.println("bisa");
    //     }
    //     else{
    //         System.out.println("ga");
    //     }
    //     matriks1.tulisMatriks();
    // }
}
