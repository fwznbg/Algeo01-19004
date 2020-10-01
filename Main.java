import java.util.*;
import java.io.*;
public class Main {
    boolean bacafile = false;
    boolean savefile = false;
    void isbacafile(){
        System.out.println("1. Keyboard\n2. File");
        System.out.print("Pilih cara input: ");
        Scanner scanner = new Scanner(System.in);
        int file = scanner.nextInt();
        if(file==1){
            bacafile = false;
        }
        else{
            bacafile =true;
        }
    }
    void issavefile(){
        System.out.println("1. Apakah anda ingin menyimpan output? (Y/n)");
        Scanner scanner = new Scanner(System.in);
        char file = scanner.next().charAt(0);
        if((file == 'Y') || (file == 'y')){
            savefile = true;
        }
        else{
            savefile =false;
        }
    }
    public static void main(String[] args){
        Main main = new Main();
        Scanner scanner = new Scanner(System.in);
        int menu;
        System.out.println("1. Sistem Persamaan Linear\n2. Determinan\n3. Matriks Balikan\n4. Interpolasi Polinom\n5. Regresi Linier Berganda\n6. Keluar");
        System.out.print("Pilih menu: ");
        menu = scanner.nextInt();
        while(menu<0 || menu>6){
            System.out.println("\nMasukan tidak valid");
            System.out.println("1. Sistem Persamaan Linear\n2. Determinan\n3. Matriks Balikan\n4. Interpolasi Polinom\n5. Regresi Linier Berganda\n6. Keluar");
            System.out.print("Pilih menu: ");
            menu = scanner.nextInt();
        }
        switch(menu){
            case 1: //spl
            int submenu;
            System.out.println("\n1. Metode eliminasi Gauss\n2. Metode eliminasi Gauss-Jordan\n3. Metode matriks balikan\n4. Kaidah Cramer");
            System.out.print("Pilih sub-menu: ");
            submenu = scanner.nextInt();
            while(submenu<0 || submenu>4){
                System.out.println("\nMasukan tidak valid");
                System.out.println("1. Metode eliminasi Gauss\n2. Metode eliminasi Gauss-Jordan\n3. Metode matriks balikan\n4. Kaidah Cramer");
                System.out.print("Pilih sub-menu: ");
                submenu = scanner.nextInt();
            }
            switch(submenu){
                case 1: //gauss
                    break;
                
                case 2:     //gauss-jordan
                    break;
                
                case 3:     //metode invers
                main.isbacafile();
                Scanner input = new Scanner(System.in);
                int brs, klm;
                System.out.print("Masukkan jumlah baris: ");
                brs = input.nextInt();
                System.out.print("Masukkan jumlah kolom: ");
                klm = input.nextInt();
                Matriks M = new Matriks(brs, klm);
                if(main.bacafile){
                    M.bacaMatriksFile();
                    main.issavefile();
                    if(main.savefile){
                        try {
                            FileWriter myWriter = new FileWriter("test/output.txt");
                            if(M.splinvers(M)){
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
                            for(int m=0; m<=M.getLastIdxBrs();m++){
                                System.out.println("X"+(m+1)+" = "+M.getIsi(m, 0));
                            }
                        }
                        else{
                            System.out.println("Matriks tidak memiliki balikan, solusi tidak ada");
                        }
                    }
                }
                else{
                    M.bacaMatriks();
                    main.issavefile();
                    if(main.savefile){
                        try {
                            FileWriter myWriter = new FileWriter("test/output.txt");
                            if(M.splinvers(M)){
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
                            for(int m=0; m<=M.getLastIdxBrs();m++){
                                System.out.println("X"+(m+1)+" = "+M.getIsi(m, 0));
                            }
                        }
                        else{
                            System.out.println("Matriks tidak memiliki balikan, solusi tidak ada");
                        }
                    }                  
                }
                input.close();
                break;

                case 4: //cramer
                    main.isbacafile();
                    Scanner userinput = new Scanner(System.in);
                    int a, b;
                    System.out.print("Masukkan jumlah baris: ");
                    a = userinput.nextInt();
                    System.out.print("Masukkan jumlah kolom: ");
                    b = userinput.nextInt();
                    Matriks Mcram = new Matriks(a, b);
                    if(main.bacafile){
                        Mcram.bacaMatriksFile();
                        Mcram.cramer(Mcram);
                    }
                    else{
                        Mcram.bacaMatriks();
                        Mcram.cramer(Mcram);
                    }
                    userinput.close();
                    break;
            }
                break;
            case 2: //determinan
            main.isbacafile();
            Scanner input = new Scanner(System.in);
            int brs, klm;
            System.out.print("Masukkan jumlah baris: ");
            brs = input.nextInt();
            System.out.print("Masukkan jumlah kolom: ");
            klm = input.nextInt();
            Matriks M = new Matriks(brs, klm);
            if(main.bacafile){
                M.bacaMatriksFile();
                main.issavefile();
                if(main.savefile){
                    try {
                        FileWriter myWriter = new FileWriter("test/output.txt");
                        myWriter.write("Determinan: "+M.detRed(M));
                        System.out.println("Determinan: "+M.detRed(M));
                        System.out.println("File berhasil disimpan");
                        myWriter.close();
                        } catch (IOException e) {
                        System.out.println("File gagal disimpan.");
                      }
                }
                else{
                    System.out.println("Determinan: "+M.detRed(M));
                }
            }
            else{
                M.bacaMatriks();
                main.issavefile();
                if(main.savefile){
                    try {
                        FileWriter myWriter = new FileWriter("test/output.txt");
                        myWriter.write("Determinan: "+M.detRed(M));
                        System.out.println("Determinan: "+M.detRed(M));
                        System.out.println("File berhasil disimpan");
                        myWriter.close();
                        } catch (IOException e) {
                        System.out.println("File gagal disimpan.");
                      }
                }
                else{
                    System.out.println("Determinan: "+M.detRed(M));
                }                 
            }
            input.close();;
        

            //invers
            case 3:
            main.isbacafile();
                Scanner xyz = new Scanner(System.in);
                int x, y;
                System.out.print("Masukkan jumlah baris: ");
                x = xyz.nextInt();
                System.out.print("Masukkan jumlah kolom: ");
                y = xyz.nextInt();
                Matriks Minv = new Matriks(x, y);
                if(main.bacafile){
                    Minv.bacaMatriksFile();
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
                }
                else{
                    Minv.bacaMatriks();
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
                }
                xyz.close();
                break;

            //interpolasi
            case 4: 
                break;

            //regresi linier
            case 5: 
                break;

            //exit
            case 6:
                break;
        }
    }
}
