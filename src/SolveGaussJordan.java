public class SolveGaussJordan {
    public void solveGJ (Matriks M) {
        if (M.isBarisNol(M.getLastIdxBrs())){
            int a=M.getLastIdxBrs();
            while(M.isBarisNol(a) && a>=0){
                a--;
            }
            if (a!=0){
                for (int j=M.getLastIdxKlm()-1;j>=0;j--){
                    int z=M.idxBrsNotNol(j);
                    double elmt=1/M.getIsi(z, j);
                    System.out.print("x"+(j+1)+" = ");
                    if (!M.isIdxAfterKlmNotNol(z, j)){
                        if(M.idxNotNol(z)!=j){
                            System.out.print(elmt+"("+M.getIsi(z, M.getLastIdxKlm()));
                            for (int b=j-1;b>=M.idxNotNol(z);b--){
                                if(!M.isNol(z, b)){
                                    if (M.getIsi(z, b)>=0){
                                        System.out.print(M.getIsi(z, b)*-1+"(u"+(b+1)+")");
                                    }
                                    else{
                                        System.out.print("+"+M.getIsi(z, b)*-1+"(u"+(b+1)+")");
                                    }
                                }
                            }
                            System.out.print(")");
                        }
                        else{
                            System.out.print(elmt*M.getIsi(z, M.getLastIdxKlm()));
                        }
                    }
                    else{
                        System.out.print("u"+(j+1));
                    }
                    System.out.println();
                }
            }
            else{
                System.out.println("Matriks memiliki jumlah solusi yang tak terhingga");
            }
            
        }
        else if(M.isNoSolution(M.getLastIdxBrs())){
            System.out.println("Matriks tidak memiliki solusi");
        }
        else{
            Matriks Sol= new Matriks(1, M.getLastIdxKlm());
            for (int i=0;i<=M.getLastIdxBrs();i++){
                System.out.println("x"+(i+1)+" = "+M.getIsi(i, M.getLastIdxKlm()));
                Sol.setIsi(0, i, M.getIsi(i, M.getLastIdxKlm()));
            }
        }
    }
    public static void main(String[] args){
        GaussJordan gj= new GaussJordan();
        Gauss g= new Gauss();
        SolveGaussJordan sgj= new SolveGaussJordan();
        Matriks M=new Matriks(3,4);
        M.bacaMatriks();
        Matriks x;
        x = gj.gaussjor(g.gauss(M));
        sgj.solveGJ(M);
    }
}