public class SPLinversMat {
    public void splinvers(Matriks M){
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

        // Membuat matriks segitiga atas
        for(j=0; j<Minv.getLastIdxKlm(); j++){
            if(Minv.getIsi(j, j) == 0){
                System.out.println("Matriks tidak memiliki balikan");
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
                System.out.println("Matriks tidak memiliki balikan");
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
        hasil.tulisMatriks();
    }
    public static void main(String[] args) {
        Matriks M = new Matriks(4, 5);
        M.bacaMatriks();
        SPLinversMat inv = new SPLinversMat();
        inv.splinvers(M);  
    }
}
