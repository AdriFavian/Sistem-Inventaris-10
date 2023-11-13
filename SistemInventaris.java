import java.util.Scanner;
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

        String[][] userData = {{"admin","mimin","Admin"},
                                {"afifah", "fifi", "Staff"},
                                {"pasha", "pipi", "Staff"},
                                {"adri", "riri", "Staff"}};    

        boolean login = false;
        boolean isAdmin = false;
        boolean isStaff = false;
        boolean isLoop = false;
        boolean exit = false;
        
        int mainChoice, subChoice;

        int userCount = 0;
        int gudangCount = 0;
        String currentUser = "";
        String currentRole = "";

        //Warna Teks
        String RESET = "\u001B[0m";
        String RED = "\u001B[31m";
        String GREEN = "\u001B[32m";
        String YELLOW = "\u001B[33m";
        
        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        System.out.println(YELLOW+"=========================================="+RESET);
        System.out.println(YELLOW+"|   SELAMAT DATANG DI SISTEM INVENTARIS   |"+RESET);
        System.out.println(YELLOW+"=========================================="+RESET);

        //login
        while (!login) {
            System.out.println("Silahkan login !");
            System.out.print("Masukkan Username: ");
            String inputUsername = sc.next();
            System.out.print("Masukkan Password: ");  
            String inputPassword = sc.next();

            for (int i = 0; i < userData.length ; i++ ) {
                if (userData[i][0].equals(inputUsername) && userData[i][1].equals(inputPassword)) {
                    System.out.println(GREEN+"Berhasil login!"+RESET);
                    login = true;
                    currentUser = inputUsername;
                    currentRole = userData[i][2];

                    if("admin".equalsIgnoreCase(inputUsername)){
                        isAdmin=true;
                    } else if (!"admin".equalsIgnoreCase(inputUsername)){
                        isStaff=true;
                    }
                    break;
                }
            }
            if(!login){
                System.out.println("Gagal login!");
            }
        }

        do {//MENU UTAMA (AWAL)
            System.out.println(YELLOW+"=========================================="+RESET);
            System.out.println(YELLOW+"|   SELAMAT DATANG DI SISTEM INVENTARIS   |"+RESET);
            System.out.println(YELLOW+"=========================================="+RESET);
            
            if (isAdmin) {
                System.out.println("Sekarang Anda Berada di "+"Menu: "+GREEN+currentRole+RESET+"\n");
                System.out.println("|1| Input Barang Masuk");
                System.out.println("|2| Input Barang Keluar");
                System.out.println("|3| Tampilkan Data Barang");
                System.out.println("|4| Udpate Data Barang");
                System.out.println("|5| Cari Barang");
                System.out.println(YELLOW+"|9| Beralih Akun"+RESET);
                System.out.println(RED+"|0| Keluar"+RESET);
                
            } else if (isStaff) {
                System.out.println("Sekarang Anda Berada di "+"Menu: "+GREEN+currentRole+RESET+"\n");
                System.out.println("|1| input barang rusak");
                System.out.println("|2| Cari Barang");
                System.out.println(YELLOW+"|9| Beralih Akun"+RESET);
                System.out.println(RED+"|0| Keluar"+RESET);
            }

            System.out.print("\nPilih Menu: ");
            mainChoice = sc.nextInt();
            sc.nextLine();  
            isLoop = true;

            do {//submenu
                switch (mainChoice) {
                    case 1:
                        if (currentRole.equals("Admin")) {
                            //input barang masuk
                            System.out.println("===================================");
                            System.out.println("         Input Barang Masuk        ");
                            System.out.println("===================================");

                            System.out.print("Berapa jenis barang yang ingin diinput: ");
                            int jumlahInput = sc.nextInt();

                            for (int k=0; k<jumlahInput; k++) {
                                System.out.println("Data barang ke - "+(k+1));
                                System.out.print("Masukkan nama barang: ");
                                String namaBarang = sc.next();
                                System.out.print("Jumlah barang: ");
                                int jumlahBarang = sc.nextInt();
                                System.out.print("Masukkan kode barang: ");
                                String kodeBarang = sc.next();
                                
                                gudangCount++;
                                System.out.println(GREEN+"\nData barang masuk telah disimpan.\n"+RESET);
                            break;
                            }
                        } else if (currentRole.equals("Staff")) {
                            //input barang rusak
                            System.out.println("ini input barang rusak");
                        }
                        break;
                    //////////////////////////////////////////////////////////////////////////////////////////
                    case 2:
                        if (currentRole.equals("Admin")) {
                            //input barang keluar
                            System.out.println("ini input barang keluar");
                        } else if (currentRole.equals("Staff")) {
                            System.out.print("Masukkan kode atau barang yang ingin dicari: ");
                            String cariBarang = sc.nextLine();
                            boolean ditemukan = false;

                            for (int kategori = 0; kategori < 4; kategori++) {
                                String[] kodeArray = GudangRestoran[kategori * 3];
                                String[] namaArray = GudangRestoran[kategori * 3 + 1];
                                String[] JmlArray  = GudangRestoran [kategori * 3 + 2];

                                String kategoriJudul = " ";
                                if (kategori == 0) {
                                    kategoriJudul = "DAFTAR IKAN";
                                } else if (kategori == 1) {
                                    kategoriJudul = "DAFTAR HIDANGAN LAUT";
                                } else if (kategori == 2) {
                                    kategoriJudul = "DAFTAR BUMBU DAPUR";
                                } else if (kategori == 3) {
                                    kategoriJudul = "DAFTAR BAHAN MINUMAN";
                                }
                        
                            for (int i = 0; i < kodeArray.length; i++) {
                                if (kodeArray[i].equalsIgnoreCase(cariBarang) || namaArray[i].equalsIgnoreCase(cariBarang)) {
                                    System.out.println("===== " + kategoriJudul + " =====");
                                    System.out.println(kodeArray[i] + " - " + namaArray[i] + " = " + JmlArray[i]);
                                    ditemukan = true;
                                    } 
                                }
                            }
                            if (!ditemukan) {
                                System.out.println("Barang dengan kode atau nama " + cariBarang + " tidak ditemukan.");
                            }
                            break;
                            }
                            break;
                    /////////////////////////////////////////////////////////////////////////////////////////////////////               
                    case 3:
                        if (currentRole.equals("Admin")) {
                            System.out.println("DAFTAR BARANG DI RESTORAN SEAFOOD");
                            int indexNow =0;
                            for (int kategori = 0; kategori < 4; kategori++) {
                                String[] kodeArray = GudangRestoran[indexNow];
                                String[] namaArray = GudangRestoran[indexNow + 1];
                                String[] JmlArray = GudangRestoran [indexNow + 2];

                                String kategoriJudul = " ";
                                if (kategori == 0) {
                                    kategoriJudul = "DAFTAR IKAN";
                                } else if (kategori == 1) {
                                    kategoriJudul = "DAFTAR HIDANGAN LAUT";
                                } else if (kategori == 2) {
                                    kategoriJudul = "DAFTAR BUMBU DAPUR";
                                } else if (kategori == 3) {
                                    kategoriJudul = "DAFTAR BAHAN MINUMAN";
                                }

                                System.out.println("===== " + kategoriJudul + " =====");
                                System.out.printf("%-8s %-20s %-3s\n", "Kode", "Nama", "Jumlah");

                                for (int  i = 0; i < kodeArray.length; i++) {
                                    System.out.printf("%-8s %-20s %-3s\n", kodeArray[i], namaArray[i], JmlArray[i]);
                                }

                                System.out.println();
                                indexNow += 3;
                            }
                        }
                        break;
                    ////////////////////////////////////////////////////////////////////////////////////////////////        
                    case 5:
                        System.out.print("Masukkan kode atau barang yang ingin dicari: ");
                        String cariBarang = sc.nextLine();
                        boolean ditemukan = false;

                        for (int kategori = 0; kategori < 4; kategori++) {
                            String[] kodeArray = GudangRestoran[kategori * 3];
                            String[] namaArray = GudangRestoran[kategori * 3 + 1];
                            String[] JmlArray  = GudangRestoran [kategori * 3 + 2];

                            String kategoriJudul = " ";
                            if (kategori == 0) {
                                kategoriJudul = "DAFTAR IKAN";
                            } else if (kategori == 1) {
                                kategoriJudul = "DAFTAR HIDANGAN LAUT";
                            } else if (kategori == 2) {
                                kategoriJudul = "DAFTAR BUMBU DAPUR";
                            } else if (kategori == 3) {
                                kategoriJudul = "DAFTAR BAHAN MINUMAN";
                            }

                    
                        for (int i = 0; i < kodeArray.length; i++) {
                            if (kodeArray[i].equalsIgnoreCase(cariBarang) || namaArray[i].equalsIgnoreCase(cariBarang)) {
                                System.out.println("===== " + kategoriJudul + " =====");
                                System.out.println(kodeArray[i] + " - " + namaArray[i] + " = " + JmlArray[i]);
                                ditemukan = true;
                                } 
                            }
                        }
                        if (!ditemukan) {
                            System.out.println("Barang dengan kode atau nama " + cariBarang + " tidak ditemukan.");
                        }
                        break;
                    ///////////////////////////////////////////////////////////////////////////////////////////////////    
                    case 9:
                        System.out.println(YELLOW+"=========================================="+RESET);
                        System.out.println(GREEN+"Berhasil Logout dari " +currentRole + ": " + currentUser+RESET);
                        System.out.println("Silahkan login !");
                        // Kembali ke proses login
                        boolean switchSuccess = false;
                        while (true) {
                            System.out.print("Masukkan Username: ");
                            String switchUsername = sc.next();
                            System.out.print("Masukkan Password: ");
                            String switchPassword = sc.next();

                            for (int i = 0; i < userData.length; i++) {
                                if (userData[i][0].equals(switchUsername) && userData[i][1].equals(switchPassword)) {
                                    currentUser = switchUsername;
                                    currentRole = userData[i][2];
                                    switchSuccess = true;
                                    break;
                                }
                            }

                            if (switchSuccess) {
                                System.out.println(GREEN+"=== Beralih Akun Berhasil ==="+RESET);
                                isStaff = !isStaff;
                                isAdmin = !isAdmin;
                                break;
                            } else {
                                System.out.println("Login gagal. Silahkan coba lagi.");
                            }
                        }
                        break;
                    /////////////////////////////////////////////////////////////////////////////////////////////////        
                    case 0:
                        System.out.println(YELLOW+"Anda telah Logout" +RESET);
                        System.out.println("Terimakasih ! ^_^");
                        exit = true;
                        break;

                    default:
                        System.out.println("Pilihan tidak valid. Silahkan coba lagi.");
                        break;
                    }
                break;
            } while (isLoop);
        } while (!exit);    
            
        
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

