public class DetReduksi {
    public void detRed(Matriks M){
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
        System.out.print("Determinanya adalah "+determinan);
        }
    /*public static void main(String[] args){
        DetReduksi det = new DetReduksi();
        Matriks M = new Matriks(4, 4);
        M.bacaMatriks();
        det.detRed(M);
    }*/
}