import java.util.Scanner;

public class DetReduksi {
    public void detRed(){
        int i, j, k, brs, klm;
        int tkrbrs = 0;
        double ratio, determinan;
        Scanner scanner = new Scanner(System.in);
        System.out.print("Masukkan banyak baris: ");
        brs = scanner.nextInt();
        System.out.print("Masukkan banyak kolom: ");
        klm = scanner.nextInt();
        
        Matriks matriks = new Matriks(brs, klm);
        matriks.bacaMatriks();
        
        for(j=0;j<matriks.getLastIdxKlm();j++){
            // for(i=0;i<matriks.getLastIdxBrs();i++){
            k = j;
            while((matriks.getIsi(j, j) == 0) && k<=matriks.getLastIdxBrs()){
                matriks.tukarBaris(j, k+1);
                tkrbrs++;
                k++;
            };

            for(i=j+1;i<=matriks.getLastIdxBrs();i++){
                ratio = matriks.getIsi(i, j)/matriks.getIsi(j, j);
                matriks.kurangBaris(i, j, ratio);
            }
        }

        determinan = Math.pow(-1, tkrbrs);
        for(i=0;i<=matriks.getLastIdxBrs();i++){
            determinan *= matriks.getIsi(i, i);
        }
        System.out.println("Determinanya adalah "+determinan);
        scanner.close();
    }
    /*public static void main(String[] args){
        DetReduksi det = new DetReduksi();
        det.detRed();
    }*/
}