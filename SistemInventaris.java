
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
    
    // Fungsi untuk menampilkan judul kategori
    public static String JenisJudul(int namaJudul) {
        switch (namaJudul) {
            case 0:
                return "DAFTAR IKAN";
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

// Fungsi untuk mencari barang
public static void cariBarang() {        
    Scanner sc = new Scanner(System.in);
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

    sc.close();
}


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
                        
                    } else if (currentRole.equals("Staff")) {
                        //Input data barang rusak
                    }
                    //////////////////////////////////////////////////////////////////////////////////////////
                    
                case 2:
                    if (currentRole.equals("Admin")) {
                        System.out.println(YELLOW+"=========================================="+RESET);
                        System.out.println(YELLOW+"|   SELAMAT DATANG DI SISTEM INVENTARIS   |"+RESET);
                        System.out.println(YELLOW+"=========================================="+RESET);
                        System.out.println("Sekarang Anda Berada di "+"Submenu:"+GREEN+" Input Data Barang Masuk dan Keluar"+RESET);
                        System.out.println();
                        System.out.println("|1| Input Data Barang Masuk");
                        System.out.println("|2| Input Data Barang Keluar");
                        System.out.println(RED + "|9| Keluar" + RESET);
                        System.out.println();
                        System.out.print("Pilih Menu: ");
                        int userChoice = sc.nextInt();

                        switch (userChoice) {
                            case 1:
                                sc.nextLine();
                                System.out.print("Masukkan kode barang atau nama barang dalam gudang: ");
                                String userInput = sc.nextLine();
                                boolean ditemukan = false;

                                for (int kategori = 0; kategori < 4; kategori++) {
                                    String[] kodeArray = GudangRestoran[kategori * 4];
                                    String[] namaArray = GudangRestoran[kategori * 4 + 1];
                                    String[] JmlArray = GudangRestoran[kategori * 4 + 2];
                                    String[] SatuanArray = GudangRestoran[kategori * 4 + 3];

                                    for (int i = 0; i < kodeArray.length; i++) {
                                        if (kodeArray[i].equalsIgnoreCase(userInput) || namaArray[i].equalsIgnoreCase(userInput)) {
                                            System.out.println("Berikut Data Barang Saat Ini: ");
                                            System.out.println(kodeArray[i] + " - " + namaArray[i] + " = " + JmlArray[i] + " " + SatuanArray[i]);
                                            ditemukan = true;
                                        }
                                    }
                                    
                                }
                                if (!ditemukan) {
                                    System.out.println("Barang dengan kode atau nama " + userInput + " tidak ditemukan.");
                                    }
                                
                                break;

                            case 2:
                            case 9:
                        }

                    } else if (currentRole.equals("Staff")) {
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
                
                
                    /////////////////////////////////////////////////////////////////////////////////////////////////////               
                    case 3:
                        if (currentRole.equals("Admin")) {

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
                            System.out.println(RED +"|9| KELUAR" + RESET);
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
                                    System.out.println("=======================================");
                                    System.out.println(        "LAPORAN STOCK BARANG SEKARANG"  );
                                    System.out.println("=======================================");
                                    System.out.println();
                                    
                                    for (int kategori = 0; kategori < 4; kategori++) {
                                        String[] kodeArray = GudangRestoran[kategori * 4];
                                        String[] namaArray = GudangRestoran[kategori * 4 + 1];
                                        String[] JmlArray = GudangRestoran[kategori * 4 + 2];
                                        String[] SatuanArray = GudangRestoran[kategori * 4 + 3];

                                        
                                        String kategoriJudul = JenisJudul(kategori);
                                        System.out.println("========================" + kategoriJudul + "=========================");
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
                                    System.out.println("=======================================");
                                    System.out.println( "LAPORAN DATA BARANG SEDIKIT ATAU HABIS"   );
                                    System.out.println("=======================================");
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

                                case 9:
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
    
}

