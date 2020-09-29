public class DetReduksi {
    public double detRed(Matriks M){
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
    // public static void main(String[] args){
    //     DetReduksi det = new DetReduksi();
    //     Matriks M = new Matriks(2, 2);
    //     M.bacaMatriks();
    //     System.out.println(det.detRed(M));
    // }
}