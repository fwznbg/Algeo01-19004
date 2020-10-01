public class SolveGauss {
    public void solveGauss (Matriks M) {
        if (M.isDiagonalOne()){
            double a;
            for (int i=M.getLastIdxBrs();i>=0;i--){
                a=M.getIsi(i, M.getLastIdxKlm());
                if (i==M.getLastIdxBrs()){
                    System.out.print("X"+(i+1)+" = "+a+";");
                }
                else{
                    double sum=a;
                    double x;
                    System.out.print("X"+(i+1)+" = "+sum);
                    for (int j=M.getLastIdxKlm()-1;j>M.idxKlmOne(i);j--){
                        x=M.getIsi(i, j)*M.getIsi(j, M.getLastIdxKlm());
                        sum-=x;
                        System.out.print("-"+x);
                    }
                    System.out.print(" = "+sum+";");
                }
                System.out.println();
            }
        }
        else{
            System.out.println("error");
        }
    }
    public static void main(String[] args){
        Gauss g= new Gauss();
        SolveGauss sg= new SolveGauss();
        Matriks M=new Matriks(3,4);
        M.bacaMatriks();
        sg.solveGauss(g.gauss(M));
    }
}