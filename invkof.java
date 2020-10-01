public class invkof {
    DetKofaktor det = new DetKofaktor();
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
                        cof.setIsi(i, j, (-1)*det.deterkofak(Mdet));
                    }
                    else{
                        cof.setIsi(i, j, det.deterkofak(Mdet));
                    }
                }
                else{
                    if(j%2==0){
                        cof.setIsi(i, j, (-1)*det.deterkofak(Mdet));
                    }
                    else{
                        cof.setIsi(i, j, det.deterkofak(Mdet));
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
            adj.kaliSkalar(i, 1/det(M));
        }
        return adj;
    }
    public static void main(String[] args) {
        Matriks M = new Matriks(3, 3);
        M.bacaMatriks();
        invkof inv = new invkof();
        M = inv.invKof(M);
        M.tulisMatriks();
    }
}
