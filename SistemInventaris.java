import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SistemInventaris {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[][] GudangRestoran= {
            //Daftar ikan
            {"I-001", "I-002", "I-003", "I-004", "I-005", "I-006"},
            {"Patin", "Kakap", "Bawal", "Kerapu", "Gurame", "Bandeng"},
            {"5", "8", "7", "5", "4", "3"},

            // Daftar Hidangan laut
            {"H-001", "H-002", "H-003", "H-004", "H-005"},
            {"Udang", "Cumi", "Kepiting", "Babycrap", "Kerang" },
            {"8", "7", "5", "7", "6"},

            //Daftar Bumbu Dapur
            {"B-001", "B-002", "B-003", "B-004", "B-005", "B-006", "B-007", "B-008", "B-009", 
            "B-010", "B-011", "B-012", "B-013", "B-014", "B-015", "B-016", "B-017", "B-018"},
            {"Jeruk Nipis", "Lemon", "Bawang Putih", "Bawang Merah", "Merica","Garam", "Minyak Goreng", 
            "Saos Tiram", "Kecap manis", "Kecap Asin", "Saos Tomat", "Saos sambal", "Jahe", "Kunyit", 
            "Lengkuas", "Serai", "Daun jeruk", "Daun kemangi"},
            {"1", "3", "5", "7", "4", "9", "10", "5", "6", "4", "7", "8", "11", "7", "8", "17", "5", "9"},

            //Daftar Bahan Minuman
            {"M-001", "M-002", "M-003", "M-004"},
            {"Teh", "Lemon", "Air putih", "Es Batu"},
            {"7", "6", "10", "3"},
        };

        String[][] userData = {{"admin","mimin"},{"afifah", "fifi"},{"pasha", "pipi"},{"adri", "riri"}};    

        String[][] userArr = new String[10][3]; // username, password, role
        String[][] gudangArr = new String[100][3]; // nama, jumlah, tanggal


        boolean login = false;
        boolean isAdmin = false;
        boolean IsStaff = false;
        boolean isLoop = false;

        int userCount = 0;
        int gudangCount = 0;
        String currentUser = "";

        //Warna Teks
        String RESET = "\u001B[0m";
        String RED = "\u001B[31m";
        String GREEN = "\u001B[32m";
        String YELLOW = "\u001B[33m";
        
        ////////////////////////////////////////////////////////////////////////////////////////////////
        System.out.println("===================================\n");
        System.out.println(YELLOW+"|   SELAMAT DATANG DI SISTEM INVENTARIS   |"+RESET);
        System.out.println("\n===================================");

        //login
        while (!login) {
            System.out.print("Masukkan Username: ");
            String inputUsername = sc.next();
            System.out.print("Masukkan Password: ");  
            String inputPassword = sc.next();

            for (int i = 0; i < userData.length ; i++ ) {
                if (userData[i][0].equals(inputUsername) && userData[i][1].equals(inputPassword)) {
                    System.out.println(GREEN+"Berhasil login!"+RESET);
                    login = true;
                    if("admin".equals(inputUsername)){
                        isAdmin=true;
                    }
                    break;
                }
            }
            if(!login){
                System.out.println("Gagal login!");
            }
        }

        do {//MENU UTAMA (AWAL)
            System.out.println("===================================\n");
            System.out.println(YELLOW+"|   SELAMAT DATANG DI SISTEM INVENTARIS   |"+RESET);
            System.out.println("\n===================================");

            if (isAdmin) {
                
            }
            do {
                //submenu
            } while (isLoop);
        } while (!keluar);    
            
        
            
            // System.out.println("1. Buat akun");
            // System.out.println("2. Login");
            // System.out.println("3. Tampilkan pengguna");
            // System.out.println("4. Keluar");
            // System.out.print("\nPilih Menu: ");
            // int choice = sc.nextInt();
            
            // if (choice == 1) {
            //     // Menu Buat Akun
            //     System.out.println("===================================");
            //     System.out.println("             BUAT AKUN              ");
            //     System.out.println("===================================");
            //     System.out.print("Username: ");
            //     String username = sc.next();
            //     System.out.print("Password: ");
            //     String password = sc.next();
            //     System.out.print("Role (staff/admin): ");
            //     String role = sc.next();
                
            //     for (int l=0; l< userArr.length; l++) {
            //         if (userArr[l][0] == null) {
            //             userArr[l][0] = username;
            //             userArr[l][1] = password;
            //             userArr[l][2] = role;
            //             userCount++;
            //             break; 
            //         }
            //     }

            //     System.out.println("===================================");
            //     System.out.println(GREEN+"       AKUN BERHASIL DIBUAT !      "+RESET);
            //     System.out.println("===================================");

            // } else if (choice == 2) {
            //     // Menu Login
            //     System.out.println("===================================");
            //     System.out.println("            MASUK AKUN             ");
            //     System.out.println("===================================");
            //     System.out.print("Username: ");
            //     String username = sc.next();
            //     System.out.print("Password: ");
            //     String password = sc.next();
                
            //     for (int i = 0; i < userArr.length; i++) {
            //         if (userArr[i][0].equals(username) && userArr[i][1].equals(password)) {
            //             login = true;
            //             currentUser = username;
                        
            //             if (userArr[i][2].equals("admin")) {
            //                 IsAdmin = true;
            //             } else if (userArr[i][2].equals("staff")) {
            //                 IsStaff = true;
            //             }
                        
            //             break;
            //         }
            //     }
                
            // } else if (choice == 3) {
            //     // Menu Tampilkan akun
            //     System.out.println("===================================");
            //     System.out.println("            DAFTAR AKUN            ");
            //     System.out.println("===================================");
            //     for (int i = 0; i < userCount; i++) {
            //         System.out.println("Username: " + userArr[i][0] + 
            //                             " Password: " + userArr[i][1] +
            //                             " Role: " + userArr[i][2] + "\n");
            //     }

            // } else if (choice == 4) {
            //     break;
                
            // } else {
            //     System.out.println("===================================");
            //     System.out.println(RED+"      Pilihan tidak valid!         "+RESET);
            // }



            
            // //masuk menu
            // while (IsAdmin) {
            //         System.out.println("\n===================================");
            //         System.out.println("          Selamat Datang           ");
            //         System.out.println("             Admin " + currentUser    );
            //         System.out.println("===================================");

            //         System.out.println("1. Input Barang Masuk");
            //         System.out.println("2. Input Barang Keluar");
            //         System.out.print("\nPilih Menu: ");
            //         int choicee = sc.nextInt();

            //         switch (choicee) {
            //             case 1:
            //                 System.out.println("===================================");
            //                 System.out.println("         Input Barang Masuk        ");
            //                 System.out.println("===================================");

            //                 System.out.print("Berapa jenis barang yang ingin diinput: ");
            //                 int jumlahInput = sc.nextInt();

            //                 for (int k=0; k<jumlahInput; k++) {
            //                     System.out.println("Data barang ke - "+(k+1));
            //                     System.out.print("Nama barang: ");
            //                     String namaBarang = sc.next();
            //                     System.out.print("Jumlah barang: ");
            //                     int jumlahBarang = sc.nextInt();
            //                     System.out.print("Masukkan tanggal barang masuk (dd/MM/yyyy): ");
            //                     String input = sc.next();
            //                     DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            //                     LocalDate tanggalMasuk = LocalDate.parse(input, formatter);
                                
            //                     String informasiBarangMasuk = "Nama: " + namaBarang + " Jumlah: " + jumlahBarang + " Tanggal Masuk: " + tanggalMasuk;
            //                     for (int i = 0; i < gudangArr.length; i++) {
            //                         if (gudangArr[i][0] == null) {
            //                             gudangArr[i][0] = informasiBarangMasuk;
            //                             gudangCount++;
            //                             break; 
            //                         }
            //                     }
            //                     System.out.println("\nData barang masuk telah disimpan.\n");
            //                 }
            //             break;
            //         } 
            //         break;
            //     }

            //     while (IsStaff) {
            //         System.out.println("\n===================================");
            //         System.out.println("          Selamat Datang           ");
            //         System.out.println("             Staff " + currentUser    );
            //         System.out.println("===================================");
            //         break;
            //     }

         }// sc.close();
    }

