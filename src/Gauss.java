

public class Gauss {

    public Matriks gauss(Matriks M) {
        int i,j;
        i=0;
        j=0;
        for (i=0;i<=M.getLastIdxBrs();i++){ //iterasi tiap baris
            int a=i;
            int b=i;
            boolean tertukar=false;
            while (a<=M.getLastIdxKlm() && !tertukar){
                while(b<=M.getLastIdxBrs() && M.isNol(b, a)){
                    b++;
                }
                if (M.isNol(b, a)==false){
                    M.tukarBaris(i, b);
                    tertukar=true;
                }
                else{
                    a++;
                }
            }
            j=M.idxNotNol(i); //mencari elemen pertama tidak 0 pada suatu baris
            M.kaliSkalar(i, +(1/M.getIsi(i, j))); //mengubah elemen menjadi lead eselon
            for(int c=i+1;c<=M.getLastIdxBrs();c++){ //menjadikan setiap baris pada kolom j menjadi 0
                M.kurangBaris(c, i, M.getIsi(c, j));
            }
        }
        for (i=0;i<=M.getLastIdxBrs();i++){
            for (j=0;j<=M.getLastIdxBrs();j++){
                M.setIsi(i, j, M.getIsi(i, j)+0.0);
            }
        }
        return M;   
    }
    public static void main(String[] args){
        Gauss g= new Gauss();
        Matriks M=new Matriks(4,5);
        M.bacaMatriks();
        g.gauss(M).tulisMatriks();
    }
}
