import java.util.*;


public class Matriks{
    /**Data matriks*/
    int baris, kolom;
    double[][] isimatriks;
    /**Constructor */ 
    Matriks(int inputBaris, int inputKolom){
        int i, j;
        this.baris = inputBaris;
        this.kolom = inputKolom;

        //inisialisasi isi matriks dengan nilai -1
        this.isimatriks = new double[this.baris][this.kolom];
        for(i=0;i<this.baris;i++){
            for(j=0;j<this.kolom;j++){
                isimatriks[i][j] = -1;
            }
        }
    }

    /**Method */

    //Mengembalikan banyak baris
    int getBaris(){
        return this.baris;
    }

    //Mengembalikan banyak kolom
    int getKolom(){
        return this.kolom;
    }

    //Mengembalikan indeks baris terakhir
    int getLastIdxBrs(){
        return this.baris-1;
    }
    
    //Mengembalikan indeks kolom terakhir
    int getLastIdxKlm(){
        return this.kolom-1;
    }

    //Membaca nilai isi matriks dari masukan pengguna
    void bacaMatriks(){
        int i, j;
        Scanner input = new Scanner(System.in);
        for(i=0;i<this.baris;i++){
            String a[] = input.nextLine().split(" ");
            for(j=0;j<this.kolom;j++){
                this.isimatriks[i][j] = Double.parseDouble(a[j]);
                // System.out.print("Masukkan isi matriks baris ke-"+(i+1)+" kolom ke-"+(j+1)+": ");;
            }
        }
    }

    //Menampilkan isi matriks di layar
    void tulisMatriks(){
        int i;
        for(i=0;i<this.baris;i++){
            System.out.println(Arrays.toString(this.isimatriks[i]));
        }
    }

    //Mengalikan setiap nilai di baris i dengan skalar k
    //Indeks baris dimulai dari 0
    void kaliSkalar(int i, int k){
        int j;
        for(j=0;j<this.kolom;j++){
            this.isimatriks[i][j] *= k;
        }
    }

    //Mengurangi setiap nilai di baris i1 dengan x kali nilai baris i2
    void kurangBaris(int i1, int i2, double x){
        int j;
        for(j=0;j<this.kolom;j++){
            this.isimatriks[i1][j] -= this.isimatriks[i2][j]*x;
        }
    }

    //Menukar nilai di baris i1 dengan baris i2
    void tukarBaris(int i1, int i2){
        int j;
        for(j=0;j<this.kolom;j++){
            double tmp = this.isimatriks[i1][j];
            this.isimatriks[i1][j] = this.isimatriks[i2][j];
            this.isimatriks[i2][j] = tmp;
        }
    }

    //getisi
    double getIsi(int i, int j){
        return this.isimatriks[i][j];
    }

    //setisi
    void setIsi(int i, int j, double x){
        this.isimatriks[i][j] = x;
    }

    boolean isBarisNol(int i){
        boolean isNol = true;
        int j=0;
        while(j<=getLastIdxKlm() && isNol){
            if (this.isimatriks[i][j]!=0){
                isNol=false;
            }
            else{
                j++;
            }
        }
        return (isNol);
    }
    boolean isNoSolution(int i){
        boolean isNol = true;
        int j=0;
        while(j<getLastIdxKlm() && isNol){
            if (this.isimatriks[i][j]!=0){
                isNol=false;
            }
            j++;
        }
        return((this.isimatriks[i][getLastIdxKlm()]==0)&&isNol);
    }
    

    public static void main(String[] args) {
        Matriks matriks1 = new Matriks(3, 3);
        matriks1.bacaMatriks();
        matriks1.tulisMatriks();
    }
}