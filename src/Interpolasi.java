import java.util.*;


public class Interpolasi {
    public void hitungInterpolasi(){
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
    
    public static void main(String[] args) {
        Interpolasi a = new Interpolasi();
        a.hitungInterpolasi();
    }
}
