import java.util.*;
import java.io.*;
public class Main {
    boolean bacafile = false;
    boolean savefile = false;
    void isbacafile(){
        System.out.println("\n1. Keyboard\n2. File");
        System.out.print("Pilih cara input: ");
        Scanner baca = new Scanner(System.in);
        int file = baca.nextInt();
        if(file==1){
            bacafile = false;
        }
        else{
            bacafile =true;
        }
    }
    void issavefile(){
        System.out.println("Apakah anda ingin menyimpan output? (Y/n)");
        Scanner save = new Scanner(System.in);
        char file = save.next().charAt(0);
        if((file == 'Y') || (file == 'y')){
            savefile = true;
        }
        else{
            savefile =false;
        }
    }

    public static void main(String[] args){
        Main main = new Main();
        Scanner inputmenu = new Scanner(System.in);
        int baris, kolom;
        int menu = 0;

        boolean exit = false;
        
        while(!exit && inputmenu.hasNextInt()){
            System.out.println("\n1. Sistem Persamaan Linear\n2. Determinan\n3. Matriks Balikan\n4. Interpolasi Polinom\n5. Regresi Linier Berganda\n6. Keluar");
            System.out.print("Pilih menu: ");
            menu = inputmenu.nextInt();
            // menu = main.mainmenu();
            switch(menu){
                case 1: //spl
                    int submenu;
                    Scanner sub = new Scanner(System.in);
                    System.out.println("\n1. Metode eliminasi Gauss\n2. Metode eliminasi Gauss-Jordan\n3. Metode matriks balikan\n4. Kaidah Cramer");
                    System.out.print("Pilih sub-menu: ");
                    submenu = sub.nextInt();
                    switch(submenu){
                        case 1: //gauss
                            main.isbacafile();
                            Scanner gauss = new Scanner(System.in);
                            System.out.print("Masukkan jumlah baris: ");
                            baris = gauss.nextInt();
                            System.out.print("Masukkan jumlah kolom: ");
                            kolom = gauss.nextInt();
                            Matriks Mgauss = new Matriks(baris, kolom);
                            if(main.bacafile){
                                Mgauss.bacaMatriksFile();
                            }
                            else{
                                Mgauss.bacaMatriks();
                            }
                                main.issavefile();
                                boolean solveable = Mgauss.solveGauss(Mgauss.gauss(Mgauss));
                                if(main.savefile){
                                    if(solveable){
                                        Mgauss.tulisSolveGaussFile(Mgauss.gauss(Mgauss));
                                    }
                                    else{
                                        System.out.println("An error occured");
                                    }
                                }
                                else{
                                    if(solveable){
                                        Mgauss.tulisSolveGauss(Mgauss.gauss(Mgauss));
                                    }
                                    else{
                                        System.out.println("An error occured");
                                    }
                                }
                            break;
                        case 2:     //gauss-jordan
                            main.isbacafile();
                            Scanner gaussj = new Scanner(System.in);
                            System.out.print("Masukkan jumlah baris: ");
                            baris = gaussj.nextInt();
                            System.out.print("Masukkan jumlah kolom: ");
                            kolom = gaussj.nextInt();
                            Matriks Mgaussj = new Matriks(baris, kolom);
                            if(main.bacafile){
                                Mgaussj.bacaMatriksFile();
                            }
                            else{
                                Mgaussj.bacaMatriks();
                            }
                            main.issavefile();
                            if(main.savefile){
                                Mgaussj.gaussjor(Mgaussj.gauss(Mgaussj));
                                Mgaussj.tulisSolveGaussJFile(Mgaussj);
                                }
                            else{
                                Mgaussj.gaussjor(Mgaussj.gauss(Mgaussj));
                                Mgaussj.tulisSolveGaussJ(Mgaussj);
                            }
                            break;
                        case 3:     //metode invers
                            main.isbacafile();
                            Scanner input = new Scanner(System.in);
                            System.out.print("Masukkan jumlah baris: ");
                            baris = input.nextInt();
                            System.out.print("Masukkan jumlah kolom: ");
                            kolom = input.nextInt();
                            Matriks M = new Matriks(baris, kolom);
                            if(main.bacafile){
                                M.bacaMatriksFile();
                            }
                            else{
                                M.bacaMatriks();
                            }
                            main.issavefile();
                            if(main.savefile){
                                try {
                                    FileWriter myWriter = new FileWriter("test/output.txt");
                                    if(M.splinvers(M)){
                                        M.splinvers(M);
                                        for(int m=0; m<=M.getLastIdxBrs();m++){
                                            myWriter.write("X"+(m+1)+" = "+M.getIsi(m, 0)+"\n");
                                            System.out.println("X"+(m+1)+" = "+M.getIsi(m, 0));
                                        }
                                        System.out.println("File berhasil disimpan");
                                    }
                                    else{
                                        myWriter.write("Matriks tidak memiliki balikan, solusi tidak ada");
                                    }
                                    myWriter.close();
                                    } catch (IOException e) {
                                    System.out.println("File gagal disimpan.");
                                }
                            }
                            else{
                                if(M.splinvers(M)){
                                    M.splinvers(M);
                                    for(int m=0; m<=M.getLastIdxBrs();m++){
                                        System.out.println("X"+(m+1)+" = "+M.getIsi(m, 0));
                                    }
                                }
                                else{
                                    System.out.println("Matriks tidak memiliki balikan, solusi tidak ada");
                                }
                            }
                            input.close();
                            break;
                        case 4: //cramer
                            main.isbacafile();
                            Scanner userinput = new Scanner(System.in);
                            System.out.print("Masukkan jumlah baris: ");
                            baris = userinput.nextInt();
                            System.out.print("Masukkan jumlah kolom: ");
                            kolom = userinput.nextInt();
                            Matriks Mcram = new Matriks(baris, kolom);
                            if(main.bacafile){
                                Mcram.bacaMatriksFile();
                            }
                            else{
                                Mcram.bacaMatriks();
                            }
                            Mcram.cramer(Mcram);
                            main.issavefile();
                            if(main.savefile){
                                try {
                                    FileWriter myWriter = new FileWriter("test/output.txt");
                                    for(int m=0; m<=Mcram.getLastIdxKlm()-1;m++){
                                        myWriter.write("X"+(m+1)+" = "+Mcram.getIsi(m, 0)+"\n");
                                        System.out.println("X["+(m+1)+"] = "+Mcram.getIsi(m, 0));
                                    }
                                    System.out.println("File berhasil disimpan");
                                myWriter.close();
                                } 
                                catch (IOException e) {
                                    System.out.println("File gagal disimpan.");
                                }

                            }
                            else{
                                for(int m=0; m<=Mcram.getLastIdxKlm()-1;m++){
                                    System.out.println("X["+(m+1)+"] = "+Mcram.getIsi(m, 0));
                                }
                            }
                            userinput.close();
                            break;
                }
                
                case 2: //determinan
                    int submenu1;
                    Scanner sub1 = new Scanner(System.in);
                    System.out.println("\n1. Metode reduksi\n2. Metode kofaktor");
                    System.out.print("Pilih sub-menu: ");
                    submenu1 = sub1.nextInt();
                    switch(submenu1){
                        case 1: //determinan reduksi
                            main.isbacafile();
                            Scanner input = new Scanner(System.in);
                            System.out.print("Masukkan jumlah baris: ");
                            baris = input.nextInt();
                            System.out.print("Masukkan jumlah kolom: ");
                            kolom = input.nextInt();
                            Matriks M = new Matriks(baris, kolom);
                            if(main.bacafile){
                                M.bacaMatriksFile();
                            }
                            else{
                                M.bacaMatriks();
                            }
                            main.issavefile();
                            if(main.savefile){
                                M.tulisDoubleFile(M.detRed(M)+0.0);
                                System.out.println("Determinan: "+M.detRed(M));
                            }
                            else{
                                System.out.println("Determinan: "+M.detRed(M));
                            }

                            input.close();
                            break;
                        case 2: //determinan kofaktor
                            main.isbacafile();
                            Scanner input1 = new Scanner(System.in);
                            System.out.print("Masukkan jumlah baris: ");
                            baris = input1.nextInt();
                            System.out.print("Masukkan jumlah kolom: ");
                            kolom = input1.nextInt();
                            Matriks Mdet = new Matriks(baris, kolom);
                            if(main.bacafile){
                                Mdet.bacaMatriksFile();
                            }
                            else{
                                Mdet.bacaMatriks();
                            }
                            main.issavefile();
                            if(main.savefile){
                                Mdet.tulisDoubleFile(Mdet.deterkofak(Mdet)+0.0);
                                System.out.println("Determinan: "+Mdet.deterkofak(Mdet));
                            }
                            else{
                                System.out.println("Determinan: "+Mdet.deterkofak(Mdet));
                            }
                            input1.close();
                            break;
                    }
                case 3: //invers
                    int submenu2;
                    Scanner sub2 = new Scanner(System.in);
                    System.out.println("\n1. Metode Gauss\n2. Metode kofaktor/adjoin");
                    System.out.print("Pilih sub-menu: ");
                    submenu2 = sub2.nextInt();
                    switch(submenu2){
                        case 1: //gauss
                            main.isbacafile();
                            Scanner xyz = new Scanner(System.in);
                            System.out.print("Masukkan jumlah baris: ");
                            baris = xyz.nextInt();
                            System.out.print("Masukkan jumlah kolom: ");
                            kolom = xyz.nextInt();
                            Matriks Minv = new Matriks(baris, kolom);
                            if(main.bacafile){
                                Minv.bacaMatriksFile();
                            }
                            else{
                                Minv.bacaMatriks();
                            }
                            main.issavefile();
                            if(main.savefile){
                                try {
                                    FileWriter myWriter = new FileWriter("test/output.txt");
                                    if(Minv.inverse(Minv)){
                                        for(int m=0; m<=Minv.getLastIdxBrs();m++){
                                            myWriter.write(Arrays.toString(Minv.isimatriks[m])+"\n");
                                            System.out.println(Arrays.toString(Minv.isimatriks[m]));
                                        }
                                        System.out.println("File berhasil disimpan");
                                    }
                                    else{
                                        myWriter.write("Matriks tidak memiliki balikan, solusi tidak ada");
                                    }
                                    myWriter.close();
                                    } catch (IOException e) {
                                    System.out.println("File gagal disimpan.");
                                }
                            }
                            else{
                                if(Minv.inverse(Minv)){
                                    for(int m=0; m<=Minv.getLastIdxBrs();m++){
                                        System.out.println(Arrays.toString(Minv.isimatriks[m]));
                                    }
                                }
                                else{
                                    System.out.println("Matriks tidak memiliki balikan, solusi tidak ada");
                                }
                            }
                            xyz.close();
                            break;
                        case 2: //inv kofaktor
                            main.isbacafile();
                            Scanner qqq = new Scanner(System.in);
                            System.out.print("Masukkan jumlah baris: ");
                            baris = qqq.nextInt();
                            System.out.print("Masukkan jumlah kolom: ");
                            kolom = qqq.nextInt();
                            Matriks Madj = new Matriks(baris, kolom);
                            if(main.bacafile){
                                Madj.bacaMatriksFile();
                            }
                            else{
                                Madj.bacaMatriks();
                            }
                            main.issavefile();
                            if(main.savefile){
                                Madj.tulisMatriksFile(Madj.invKof(Madj));
                                for(int m=0; m<=Madj.getLastIdxBrs();m++){
                                    System.out.println(Arrays.toString(Madj.invKof(Madj).isimatriks[m]));
                                }
                            }
                            else{
                                for(int m=0; m<=Madj.getLastIdxBrs();m++){
                                    System.out.println(Arrays.toString(Madj.invKof(Madj).isimatriks[m]));
                                }
                            }
                            qqq.close();
                            break;
                    }
                    break;

                
                case 4: //interpolasi
                    main.isbacafile();
                    double x;
                    Scanner intp = new Scanner(System.in);
                    System.out.print("Masukkan banyak titik: ");
                    baris = intp.nextInt();
                    Matriks Mint = new Matriks(baris, 2);
                    if(main.bacafile){
                        Mint.bacaMatriksFile();
                    }
                    else{
                        System.out.println("Masukkan titik: ");
                        Mint.bacaMatriks();
                    }
                    Mint = Mint.interpolasi(Mint);
                    System.out.print("Masukkan nilai yang akan ditaksir: ");
                    x = intp.nextDouble();
                    main.issavefile();
                    if(main.savefile){
                        Mint.tulisintplFile(Mint, x);
                    }
                    else{
                        Mint.tulisintpl(Mint, x);
                    }
                    break;

                case 5: //regresi linier
                    main.isbacafile();
                    Scanner xinput = new Scanner(System.in);
                    System.out.println("Masukkan jumlah variabel");
                    int n = xinput.nextInt();
                    System.out.println("Masukkan jumlah data");
                    int data = xinput.nextInt();
                    while (data<n){
                        System.out.println("jumlah data tidak mencukupi!");
                        data = xinput.nextInt();
                    }
                    Matriks M = new Matriks(data, n+1);
                    if(main.bacafile){
                        M.bacaMatriksFile();
                    }
                    else{
                        System.out.println("Masukkan nilai x dan y");
                        System.out.print("y ");
                        for (int j=0;j<n;j++){
                            System.out.print("x"+(j+1)+" ");
                        }
                        M.bacaMatriks();
                    }
                    M = M.regresi(M);
                    main.issavefile();
                    if(main.savefile){
                        M.tulisregresiFile(M);
                    }
                    else{
                        M.tulisregresi(M);
                    }
                    
                    break;

                
                case 6: //exit
                    exit = true;
                    break;
            }
        }
        // inputmenu.close();
    }
}
