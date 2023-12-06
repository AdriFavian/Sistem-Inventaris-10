import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class SistemInventaris {
    static String[][] GudangRestoran= {
            //Daftar ikan
            {"I-001", "I-002", "I-003", "I-004", "I-005"},
            {"Patin", "Kakap", "Bawal", "Gurame", "Bandeng"},
            {"5", "8", "7", "5", "4"},
            {"kg", "kg", "kg", "kg", "kg"},

            // Daftar Hidangan laut
            {"H-001", "H-002", "H-003", "H-004"},
            {"Udang", "Cumi", "Kepiting", "Kerang" },
            {"8", "7", "5", "7"},
            {"kg", "kg", "kg", "kg"},

            //Daftar Bumbu Dapur
            {"B-001", "B-002", "B-003", "B-004", "B-005", "B-006", "B-007", "B-008", "B-009", 
            "B-010", "B-011", "B-012"},
            {"Bawang Putih", "Bawang Merah", "Merica","Garam", "Minyak Goreng", 
            "Saos Tiram", "Kecap", "Saos", "Jahe", "Kunyit", "Lengkuas", "Serai"},
            {"1", "3", "5", "7", "4", "9", "10", "5", "6", "4", "7", "8"},
            {"kg", "kg", "buah", "buah", "liter", "buah", "buah", "buah", "kg", "kg", "buah", "buah"},

            //Daftar Bahan Minuman
            {"M-001", "M-002", "M-003", "M-004"},
            {"Teh", "Lemon", "Air putih", "Es Batu"},
            {"7", "6", "10", "3"},
            {"buah", "kg", "galon", "box"},
        };
    

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        String[][] userData = {{"admin","mimin","Admin"},
                                {"afifah", "fifi", "Staff"},
                                {"pasha", "pipi", "Staff"},
                                {"adri", "riri", "Staff"}};    

        boolean login = false;
        boolean isAdmin = false;
        boolean isStaff = false;
        boolean isLoop = false;
        boolean exit = false;
        
        int mainChoice;
        // int subChoice;

        // int userCount = 0;
        // int gudangCount = 0;
        String currentUser = "";
        String currentRole = "";

        //Warna Teks
        String RESET = "\u001B[0m";
        String RED = "\u001B[31m";
        String GREEN = "\u001B[32m";
        String YELLOW = "\u001B[33m";
        String ORANGE = "\u001B[38;5;208m";
        String BLUE = "\u001B[94m";
        
        
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
                System.out.println("|1| Input Data Barang ke Master");
                System.out.println("|2| Input Data Barang Masuk dan Keluar");
                System.out.println("|3| Update Data Barang Ke Master");
                System.out.println("|4| Pencarian Data Barang");
                System.out.println("|5| Laporan Data Barang");
                // System.out.println("|6| Daftar Data Barang");
                System.out.println(YELLOW+"|9| Beralih Akun"+RESET);
                System.out.println(RED+"|0| Keluar"+RESET);
                
            } else if (isStaff) {
                System.out.println("Sekarang Anda Berada di "+"Menu: "+GREEN+currentRole+RESET+"\n");
                System.out.println("|1| Input Data Barang Rusak");
                System.out.println("|2| Pencarian Data Barang");
                System.out.println("|3| Laporan Data Barang");
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
                    if (currentRole.equalsIgnoreCase("Admin")) {
                        //Input data Barang ke Master
                        System.out.print("Masukkan kode baru: ");
                        String newKode = sc.next();
                        System.out.print("Masukkan nama baru: ");
                        String newNama = sc.next();
                        sc.nextLine();
                        System.out.print("Masukkan jumlah baru: ");
                        String newJumlah = sc.next();
                        System.out.print("Masukkan satuan baru: ");
                        String newSatuan = sc.next();
                        System.out.println();
                        System.out.println(GREEN + "Data barang baru telah disimpan!" + RESET);

                        inputData(newKode, newNama, newJumlah, newSatuan);
                        break;
                        
                    } else if (currentRole.equals("Staff")) {
                        //Input data barang rusak
                        System.out.print("Masukkan data barang rusak: ");

                        break;
                    }
                    //////////////////////////////////////////////////////////////////////////////////////////
                    
                case 2:
                    if (currentRole.equals("Admin")) {
                        boolean konfirmasiUser = false;
                        do {
                            System.out.println(YELLOW+"=========================================="+RESET);
                            System.out.println(YELLOW+"|   SELAMAT DATANG DI SISTEM INVENTARIS   |"+RESET);
                            System.out.println(YELLOW+"=========================================="+RESET);
                            System.out.println("Sekarang Anda Berada di "+"Submenu:"+GREEN+" Input Data Barang Masuk dan Keluar"+RESET);
                            System.out.println();
                            System.out.println("|1| Input Data Barang Masuk");
                            System.out.println("|2| Input Data Barang Keluar");
                            System.out.println(RED + "|0| Keluar" + RESET);
                            System.out.println();
                            System.out.print("Pilih Menu: ");
                            int userChoice = sc.nextInt();

                            switch (userChoice) {
                                case 1:
                                    sc.nextLine();
                                    boolean ditemukan = false;
                                    boolean konfirmasi = false;
                                    do {
                                        System.out.print("Masukkan berapa barang yang ingin Anda input masuk:");
                                        int jumlahBarangInput = sc.nextInt();
                                        sc.nextLine();

                                        for (int j =0; j <jumlahBarangInput; j++) {
                                            System.out.print("Masukkan kode atau nama barang: ");
                                            String dataBarang = sc.nextLine();

                                            ditemukan = false;

                                            for (int kategori = 0; kategori < 4; kategori++) {
                                                String[] kodeArray = GudangRestoran[kategori * 4];
                                                String[] namaArray = GudangRestoran[kategori * 4 + 1];
                                                String[] JmlArray = GudangRestoran[kategori * 4 + 2];
                                                String[] SatuanArray = GudangRestoran[kategori * 4 + 3];

                                                String kategoriJudul = JenisJudul(kategori);
                                                for (int i = 0; i < kodeArray.length; i++) {
                                                    if (kodeArray[i].equalsIgnoreCase(dataBarang) || namaArray[i].equalsIgnoreCase(dataBarang)) {
                                                        System.out.println("\n===== " + kategoriJudul + " =====");
                                                        System.out.println(kodeArray[i] + " - " + namaArray[i] + " = " + JmlArray[i] + " " + SatuanArray[i]);

                                                        // Menambah jumlah barang
                                                        System.out.println();
                                                        System.out.print("Masukkan jumlah barang yang ingin ditambahkan: ");
                                                        int jumlahTambah = sc.nextInt();
                                                        int jumlahSebelum = Integer.parseInt(JmlArray[j]);
                                                        int jumlahSetelah = jumlahSebelum + jumlahTambah;
                                                        JmlArray[j] = String.valueOf(jumlahSetelah);

                                                        System.out.println();
                                                        System.out.println(GREEN+ "Jumlah barang berhasil ditambahkan." + RESET);
                                                        System.out.println(GREEN + "----------------------------------" + RESET);
                                                        ditemukan = true;
                                                    }
                                                }
                                            }
                                            if (!ditemukan) {
                                                System.out.println(RED + "Barang dengan kode atau nama " + dataBarang + " tidak ditemukan." + RESET);
                                            }

                                            
                                            sc.nextLine();
                                            if (jumlahBarangInput - j == 1) {
                                                konfirmasi = true;
                                            }
                                            }

                                        } while (!konfirmasi);
                                        break;

                                case 2:
                                sc.nextLine();
                                ditemukan = false;
                                konfirmasi = false;

                                    do {
                                        System.out.print("Masukkan berapa barang yang ingin Anda input keluar:");
                                        int jumlahBarangInput = sc.nextInt();
                                        sc.nextLine();

                                        for (int j =0; j <jumlahBarangInput; j++) {
                                            System.out.print("Masukkan kode atau nama barang: ");
                                            String dataBarang = sc.nextLine();

                                            ditemukan = false;

                                            for (int kategori = 0; kategori < 4; kategori++) {
                                                String[] kodeArray = GudangRestoran[kategori * 4];
                                                String[] namaArray = GudangRestoran[kategori * 4 + 1];
                                                String[] JmlArray = GudangRestoran[kategori * 4 + 2];
                                                String[] SatuanArray = GudangRestoran[kategori * 4 + 3];

                                                String kategoriJudul = JenisJudul(kategori);
                                                for (int i = 0; i < kodeArray.length; i++) {
                                                    if (kodeArray[i].equalsIgnoreCase(dataBarang) || namaArray[i].equalsIgnoreCase(dataBarang)) {
                                                        System.out.println("\n===== " + kategoriJudul + " =====");
                                                        System.out.println(kodeArray[i] + " - " + namaArray[i] + " = " + JmlArray[i] + " " + SatuanArray[i]);

                                                        // Mengurangi jumlah barang
                                                        System.out.println();
                                                        System.out.print("Masukkan jumlah barang yang ingin dikurangi: ");
                                                        int jumlahKurang = sc.nextInt();
                                                        int jumlahSebelum = Integer.parseInt(JmlArray[j]);
                                                        int jumlahSetelah = jumlahSebelum - jumlahKurang;
                                                        JmlArray[j] = String.valueOf(jumlahSetelah);

                                                        if (jumlahSebelum < jumlahKurang) {
                                                            System.out.println(RED + "Mohon maaf, jumlah barang yang ingin dikurangi tidak mencukupi." + RESET);
                                                            System.out.println(RED + "---------------------------------------------------------------" + RESET);
                                                            break;
                                                        }

                                                        System.out.println();
                                                        System.out.println(GREEN + "Jumlah barang berhasil dikurangi." + RESET);
                                                        System.out.println(GREEN + "---------------------------------" + RESET);
                                                        ditemukan = true;
                                                    }
                                                }
                                            }
                                            if (!ditemukan) {
                                                System.out.println("Barang dengan kode atau nama " + dataBarang + " tidak ditemukan.");
                                            }
                                            
                                            sc.nextLine();
                                            if (jumlahBarangInput - j == 1) {
                                                konfirmasi = true;
                                            }
                                            }

                                        } while (!konfirmasi);
                                        break;

                                case 0:
                                konfirmasiUser = true;
                                break;
                            }
                        } while (!konfirmasiUser);
                        

                    } else if (currentRole.equals("Staff")) {
                        

                        }
                        break;
                
                
                    /////////////////////////////////////////////////////////////////////////////////////////////////////               
                    case 3:
                        if (currentRole.equals("Admin")) {
                            // update data barang
                            // Dapatkan input dari pengguna untuk mencari nama atau kode
                            System.out.print("Masukkan nama atau kode item yang akan diupdate: ");
                            String searchTerm = sc.nextLine();
                    
                            // Pilih jenis update
                            System.out.println("Pilih jenis update:");
                            System.out.println("1. Kode");
                            System.out.println("2. Nama Barang");
                            System.out.println("3. Satuan Barang");
                            System.out.print("Masukkan pilihan (1/2/3): ");
                            int choice = sc.nextInt();
                    
                            // Cari item dan update informasinya
                            boolean itemDitemukan = updateItem(searchTerm, choice, sc);
                    
                            // Tampilkan array yang sudah diupdate
                            if (itemDitemukan) {
                                System.out.println();
                                System.out.println(GREEN + "Data berhasil diupdate!" + RESET);
                                System.out.println();
                                for (int kategori = 0; kategori < 4; kategori++) {
                                    String[] kodeArray = GudangRestoran[kategori * 4];
                                    String[] namaArray = GudangRestoran[kategori * 4 + 1];
                                    String[] JmlArray = GudangRestoran[kategori * 4 + 2];
                                    String[] SatuanArray = GudangRestoran[kategori * 4 + 3];

                                    
                                    String kategoriJudul = JenisJudul(kategori);
                                    System.out.println("                     ================================="               );
                                    System.out.println(                                 kategoriJudul                           );
                                    System.out.println("                     =================================               ");
                                    System.out.println();
                                    System.out.println("=====================================================================");
                                    System.out.println("| KODE   |       Nama Barang       | Jumlah Barang |     Satuan     |");
                                    System.out.println("=====================================================================");
                                    for (int i =0; i<kodeArray.length; i++) {
                                        System.out.printf("| %-6s | %-23s | %-13.5s | %-14.5s |%n", kodeArray[i], namaArray[i], JmlArray[i], SatuanArray[i]);
                                        }

                                    System.out.println("=====================================================================");
                                    System.out.println();
                                }
                                } else 
                                System.out.println("Item tidak ditemukan.");
                                break;
                                
                        } else if (currentRole.equals("Staff")) {

                        }
                        
                    ////////////////////////////////////////////////////////////////////////////////////////////////    
                    case 4:
                        if (currentRole.equals("Admin")) {
                            System.out.print("Masukkan kode atau barang yang ingin dicari: ");
                            String cariBarang = sc.nextLine();
                            boolean ditemukan = false;

                            for (int kategori = 0; kategori < 4; kategori++) {
                                String[] kodeArray = GudangRestoran[kategori * 4];
                                String[] namaArray = GudangRestoran[kategori * 4 + 1];
                                String[] JmlArray = GudangRestoran[kategori * 4 + 2];
                                String[] SatuanArray = GudangRestoran[kategori * 4 + 3];

                                String kategoriJudul = JenisJudul(kategori);
                                for (int i = 0; i < kodeArray.length; i++) {
                                    if (kodeArray[i].equalsIgnoreCase(cariBarang) || namaArray[i].equalsIgnoreCase(cariBarang)) {
                                        System.out.println("\n===== " + kategoriJudul + " =====");
                                        System.out.println(kodeArray[i] + " - " + namaArray[i] + " = " + JmlArray[i] + " " + SatuanArray[i]);
                                        ditemukan = true;
                                    }
                                }
                            }
                            if (!ditemukan) {
                                System.out.println("Barang dengan kode atau nama " + cariBarang + " tidak ditemukan.");
                                }
                            }
                            break;

                    case 5:
                        boolean konfirmasiUser = true;
                        do {
                            System.out.println(YELLOW+"=========================================="+RESET);
                            System.out.println(YELLOW+"|   SELAMAT DATANG DI SISTEM INVENTARIS   |"+RESET);
                            System.out.println(YELLOW+"=========================================="+RESET);
                            System.out.println("Sekarang Anda Berada di "+"Submenu:"+GREEN+" Laporan Data Barang"+RESET);
                            System.out.println();
                            System.out.println("|1| Laporan Data Barang Masuk");
                            System.out.println("|2| Laporan Data Barang Keluar");
                            System.out.println("|3| Laporan Data Barang Rusak");
                            System.out.println("|4| Laporan Stock Barang Sekarang");
                            System.out.println("|5| Laporan Stock Paling Banyak Keluar");
                            System.out.println("|6| Laporan Data Barang Habis Atau Sedikit");
                            System.out.println(RED +"|0| KELUAR" + RESET);
                            System.out.println();
                            System.out.print("Pilih Menu: ");
                            int laporanUser = sc.nextInt();
                            

                            switch (laporanUser) {
                                case 1:
                                    System.out.println("=======================================");
                                    System.out.println(        "LAPORAN DATA BARANG MASUK"      );
                                    System.out.println("=======================================");
                                    break;

                                case 2:
                                    System.out.println("=======================================");
                                    System.out.println(        "LAPORAN DATA BARANG KELUAR"     );
                                    System.out.println("=======================================");
                                    break;

                                case 3:
                                    System.out.println("=======================================");
                                    System.out.println(        "LAPORAN DATA BARANG RUSAK"      );
                                    System.out.println("=======================================");
                                    break;

                                case 4:
                                    System.out.println(ORANGE + "=====================================================================" + RESET);
                                    System.out.println(ORANGE +"                     LAPORAN STOCK BARANG SEKARANG"  + RESET);
                                    System.out.println(ORANGE+"=====================================================================" + RESET);
                                    System.out.println();
                                    
                                    for (int kategori = 0; kategori < 4; kategori++) {
                                        String[] kodeArray = GudangRestoran[kategori * 4];
                                        String[] namaArray = GudangRestoran[kategori * 4 + 1];
                                        String[] JmlArray = GudangRestoran[kategori * 4 + 2];
                                        String[] SatuanArray = GudangRestoran[kategori * 4 + 3];

                                        String kategoriJudul = JenisJudul(kategori);
                                        System.out.println("                     =================================               ");
                                        System.out.println("\t\t\t     " + kategoriJudul                           );
                                        System.out.println("                     =================================               ");
                                        System.out.println("=====================================================================");
                                        System.out.println("| KODE   |       Nama Barang       | Jumlah Barang |     Satuan     |");
                                        System.out.println("=====================================================================");
                                        for (int i =0; i<kodeArray.length; i++) {
                                            System.out.printf("| %-6s | %-23s | %-13.5s | %-14.5s |%n", kodeArray[i], namaArray[i], JmlArray[i], SatuanArray[i]);

                                        }
                                        System.out.println("=====================================================================");
                                        System.out.println();
                                    }
                                    break;

                                case 5:
                                    System.out.println("=======================================");
                                    System.out.println(   "LAPORAN STOCK PALING BANYAK KELUAR"  );
                                    System.out.println("=======================================");
                                    break;

                                case 6:
                                    System.out.println(RED + "SEGERA RE-STOCK BARANG-BARANG BERIKUT KARENA MEMILIKI JUMLAH SEDIKIT DAN HAMPIR HABIS" + RESET);
                                    System.out.println();
                                    System.out.println(BLUE + "                 =======================================            " + RESET);
                                    System.out.println(BLUE + "                  LAPORAN DATA BARANG SEDIKIT ATAU HABIS            " + RESET);
                                    System.out.println(BLUE + "                 =======================================            " + RESET);
                                    System.out.println();
                                    System.out.println("=====================================================================");
                                    System.out.println("| KODE   |       Nama Barang       | Jumlah Barang |     Satuan     |");
                                    System.out.println("=====================================================================");

                                    for (int kategori = 0; kategori < 4; kategori++) {
                                        String[] kodeArray = GudangRestoran[kategori * 4];
                                        String[] namaArray = GudangRestoran[kategori * 4 + 1];
                                        String[] JmlArray = GudangRestoran[kategori * 4 + 2];
                                        String[] SatuanArray = GudangRestoran[kategori * 4 + 3];

                                        for (int i =0; i<kodeArray.length; i++) {
                                            int jumlahStok = Integer.parseInt(JmlArray[i]);
                                            if (jumlahStok <= 3) {
                                                System.out.printf("| %-6s | %-23s | %-13.5s | %-14.5s |%n", kodeArray[i], namaArray[i], JmlArray[i], SatuanArray[i]);
                                            }
                                        }
                                    }
                                    System.out.println("=====================================================================");
                                    System.out.println();
                                    break;

                                case 0:
                                konfirmasiUser = false;
                                break;
                                
                            }

                        } while (konfirmasiUser);
                        break;
                    case 6:
                        
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
            
        sc.close();
    }
    

    // Fungsi untuk menampilkan judul kategori
    public static String JenisJudul(int namaJudul) {
        switch (namaJudul) {
            case 0:
                return "DAFTAR IKAN LAUT";
            case 1:
                return "DAFTAR HIDANGAN LAUT";
            case 2:
                return "DAFTAR BUMBU DAPUR";
            case 3:
                return "DAFTAR BAHAN MINUMAN";
            default:
                return "";
        }
    }

    static boolean updateItem(String searchTerm, int choice, Scanner sc) {
        boolean ditemukan = false;
        for (int kategori = 0; kategori < 4; kategori++) {
            String[] kodeArray = GudangRestoran[kategori * 4];
            String[] namaArray = GudangRestoran[kategori * 4 + 1];
            String[] JmlArray = GudangRestoran[kategori * 4 + 2];
            String[] SatuanArray = GudangRestoran[kategori * 4 + 3];

            for (int i = 0; i < kodeArray.length; i++) {
                if (kodeArray[i].equalsIgnoreCase(searchTerm) || namaArray[i].equalsIgnoreCase(searchTerm)) {
                    System.out.println("\n===== Update Data =====");
                    System.out.println("Kode\t\t: " + kodeArray[i]);
                    System.out.println("Nama Barang\t: " + namaArray[i]);
                    System.out.println("Jumlah\t\t: " + JmlArray[i]);
                    System.out.println("Satuan\t\t: " + SatuanArray[i]);
                    sc.nextLine();

                    // Meminta pengguna untuk memasukkan informasi baru
                    switch (choice) {
                        case 1:
                            System.out.print("Masukkan kode baru: ");
                            kodeArray[i] = sc.next();
                            break;
                        case 2:
                            System.out.print("Masukkan nama baru: ");
                            namaArray[i] = sc.nextLine();
                            break;
                        case 3:
                            System.out.print("Masukkan satuan baru: ");
                            SatuanArray[i] = sc.next();
                            break;
                        default:
                            System.out.println("Pilihan tidak valid.");
                            return false;
                    }

                    ditemukan = true;
                    break; // Item ditemukan dan diupdate, keluar dari loop
                }
            }
        }
        return ditemukan;
    }


static void inputData(String kode, String nama, String jumlah, String satuan) {
        // Menentukan kategori berdasarkan kode
        int kategori;
        if (kode.startsWith("I-")) {
            kategori = 0;
        } else if (kode.startsWith("H-")) {
            kategori = 1;
        } else if (kode.startsWith("B-")) {
            kategori = 2;
        } else if (kode.startsWith("M-")) {
            kategori = 3;
        } else {
            System.out.println("Kategori tidak valid.");
            return;
        }

        // Menambahkan data baru ke dalam array
        GudangRestoran[kategori * 4] = Arrays.copyOf(GudangRestoran[kategori * 4], GudangRestoran[kategori * 4].length + 1);
        GudangRestoran[kategori * 4 + 1] = Arrays.copyOf(GudangRestoran[kategori * 4 + 1], GudangRestoran[kategori * 4 + 1].length + 1);
        GudangRestoran[kategori * 4 + 2] = Arrays.copyOf(GudangRestoran[kategori * 4 + 2], GudangRestoran[kategori * 4 + 2].length + 1);
        GudangRestoran[kategori * 4 + 3] = Arrays.copyOf(GudangRestoran[kategori * 4 + 3], GudangRestoran[kategori * 4 + 3].length + 1);

        int lastIndex = GudangRestoran[kategori * 4].length - 1;
        GudangRestoran[kategori * 4][lastIndex] = kode;
        GudangRestoran[kategori * 4 + 1][lastIndex] = nama;
        GudangRestoran[kategori * 4 + 2][lastIndex] = jumlah;
        GudangRestoran[kategori * 4 + 3][lastIndex] = satuan;
    }

    static void sortArrayDescending() {
        for (int kategori = 0; kategori < 4; kategori++) {
            int length = GudangRestoran[kategori * 4].length;
            String[][] tempArray = new String[4][length];

            // Menggabungkan data ke dalam array sementara
            for (int i = 0; i < length; i++) {
                tempArray[0][i] = GudangRestoran[kategori * 4][i];
                tempArray[1][i] = GudangRestoran[kategori * 4 + 1][i];
                tempArray[2][i] = GudangRestoran[kategori * 4 + 2][i];
                tempArray[3][i] = GudangRestoran[kategori * 4 + 3][i];
            }

            // Mengurutkan array sementara berdasarkan kode secara descending
            Arrays.sort(tempArray, Comparator.comparing((String[] arr) -> arr[0]).reversed());

            // Menggabungkan array sementara ke dalam array utama
            for (int i = 0; i < length; i++) {
                GudangRestoran[kategori * 4][i] = tempArray[0][i];
                GudangRestoran[kategori * 4 + 1][i] = tempArray[1][i];
                GudangRestoran[kategori * 4 + 2][i] = tempArray[2][i];
                GudangRestoran[kategori * 4 + 3][i] = tempArray[3][i];
            }
        }
    }
}

