public class DetKofaktor {
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
    public static void main(String[] args) {
        Matriks M = new Matriks(4, 4);
        M.bacaMatriks();
        DetKofaktor det = new DetKofaktor();
        System.out.println(det.deterkofak(M)); 
    }
}
