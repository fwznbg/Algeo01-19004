public class DetReduksi {
    public double detRed(Matriks M){
        int i, j;
        int tkrbrs = 0;
        double ratio;
        double determinan = 1;

        // Membuat matriks segitiga
        for(j=0;j<M.getLastIdxKlm();j++){
            int k = j+1;
            while(M.getIsi(j, j) == 0 && k<=M.getLastIdxBrs()){
                tukarBaris(j, k);
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
    // public static void main(String[] args){
    //     DetReduksi det = new DetReduksi();
    //     Matriks M = new Matriks(2, 2);
    //     M.bacaMatriks();
    //     System.out.println(det.detRed(M));
    // }
}