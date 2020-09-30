import java.util.Scanner;

public class Main {
    boolean bacafile = false;
    void isbacafile(){
        System.out.println("1. Keyboard\n2. File");
        System.out.println("Pilih cara input: ");
        Scanner scanner = new Scanner(System.in);
        int file = scanner.nextInt();
        if(file==1){
            bacafile = false;
        }
        else{
            bacafile =true;
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
                //gauss
                case 1: 
                    break;
                //gauss-jordan
                case 2: 
                    break;
                //metode invers
                case 3: 
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
                    System.out.println(M.splinvers(M));
                }
                else{
                    M.bacaMatriks();
                    System.out.println(M.splinvers(M));
                }
                scanner.close();
                    break;
                //cramer
                case 4: 
                    break;
            }
                break;
            case 2: //determinan
            int submenu1;
            System.out.println("\n1. Metode reduksi baris\n2. Metode ekspansi kofaktor");
            System.out.print("Pilih sub-menu: ");
            submenu1 = scanner.nextInt();
            while(submenu1<0 || submenu1>4){
                System.out.println("\nMasukan tidak valid");
                System.out.println("\n1. Metode reduksi baris\n2. Metode ekspansi kofaktor");
                System.out.print("Pilih sub-menu: ");
                submenu1 = scanner.nextInt();
            }
            switch(submenu1){
                //deteminan reduksi
                case 1: 
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
                        System.out.println("Determinan: "+ M.detRed(M));
                    }
                    else{
                        M.bacaMatriks();
                        System.out.println("Determinan: "+ M.detRed(M));
                    }
                    scanner.close();
                    break;

                //determinan kofaktor
                case 2: 
                    break;
            }
                break;

            //invers
            case 3:
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
                System.out.println("Matriks Invers: ");
                M.inverse(M);
            }
            else{
                M.bacaMatriks();
                System.out.println("Matriks Invers: ");
                M.inverse(M);
            } 
            scanner.close();
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
