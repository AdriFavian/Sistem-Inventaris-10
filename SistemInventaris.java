import java.util.Arrays;
import java.util.Scanner;
public class SistemInventaris {
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
                System.out.println("|1| Input Barang Masuk");
                System.out.println("|2| Input Barang Keluar");
                System.out.println("|3| Tampilkan Data Barang");
                System.out.println("|4| Update Data Barang");
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
                    if (currentRole.equalsIgnoreCase("Admin")) {
                        // Input barang baru
                        System.out.println("===================================");
                        System.out.println("         Input Barang Baru         ");
                        System.out.println("===================================");
                
                        System.out.print("Berapa jenis barang yang ingin diinput: ");
                        int jumlahInput = sc.nextInt();
                        sc.nextLine();

                        boolean kodeBarangTersedia;
                        for (int i = 0; i < jumlahInput; i++) {
                            boolean namaBarangTersedia;
                            kodeBarangTersedia = false;
                
                            do {
                                namaBarangTersedia = false;
                                System.out.print("Masukkan nama barang baru: ");
                                String namaBarang = sc.nextLine();
                
                                // Cek kesamaan barang dengan array Gudang Restoran
                                int kategori = i * 3;
                                for (int j = 0; j < GudangRestoran[kategori + 1].length; j++) {
                                    if ((GudangRestoran[1][j].equalsIgnoreCase(namaBarang))) {
                                        System.out.println("Mohon maaf, nama barang " + namaBarang + " sudah tersedia dalam data gudang. ");
                                        System.out.println("Mohon masukkan ulang nama barang baru.");
                                        namaBarangTersedia = true; // Mengulang perulangan nama barang
                                        break;
                                    }
                                }
                
                                if (!namaBarangTersedia) {
                                    kodeBarangTersedia = false;
                                    System.out.print("Masukkan kode barang baru: ");
                                    String kodeBarang = sc.nextLine();
                
                                    // Cek kesamaan kode barang dengan array
                                    for (int j = 0; j < GudangRestoran[kategori].length; j++) {
                                        if (GudangRestoran[0][j].equalsIgnoreCase(kodeBarang)) {
                                            System.out.println("Mohon maaf, kode barang " + kodeBarang + " sudah tersedia dalam data gudang. ");
                                            System.out.println("Mohon masukkan ulang kode barang baru.");
                                            kodeBarangTersedia = true; // Mengulang perulangan kode barang
                                            break;
                                        }
                                    }
                
                                    if (!kodeBarangTersedia) {
                                        System.out.print("Masukkan jumlah barang baru: ");
                                        int jumlahBarang = sc.nextInt();
                                        sc.nextLine();
                
                                        // Menambahkan data baru ke dalam array
                                        int indexBaru = GudangRestoran[kategori].length;
                                        GudangRestoran[kategori] = Arrays.copyOf(GudangRestoran[kategori], indexBaru + 1);
                                        GudangRestoran[kategori + 1] = Arrays.copyOf(GudangRestoran[kategori + 1], indexBaru + 1);
                                        GudangRestoran[kategori + 2] = Arrays.copyOf(GudangRestoran[kategori + 2], indexBaru + 1);
                
                                        GudangRestoran[kategori][indexBaru] = kodeBarang;
                                        GudangRestoran[kategori + 1][indexBaru] = namaBarang;
                                        GudangRestoran[kategori + 2][indexBaru] = String.valueOf(jumlahBarang);
                
                                        System.out.println("Data yang Anda masukkan sudah tersimpan.");
                                    }
                                }
                            } while (namaBarangTersedia || kodeBarangTersedia);
                        }
                    
                    } else if (currentRole.equals("Staff")) {
                            if (currentRole.equals("Staff")) {
                                if (isStaff) {
                                    System.out.println("===================================");
                                    System.out.println("   Input Laporan Barang Rusak      ");
                                    System.out.println("===================================");
                            
                                    System.out.print("Berapa jenis barang yang rusak: ");
                                    int jumlahRusak = sc.nextInt();
                            
                                    // Mendeklarasikan array atau struktur data untuk menyimpan informasi barang rusak
                                    String[] kodeBarangRusakArray = new String[jumlahRusak];
                                    String[] namaBarangRusakArray = new String[jumlahRusak];
                                    String[] keteranganRusakArray = new String[jumlahRusak];
                            
                                    for (int k = 0; k < jumlahRusak; k++) {
                                        System.out.println("Data barang rusak ke - " + (k + 1));
                                        System.out.print("Masukkan kode barang rusak: ");
                                        kodeBarangRusakArray[k] = sc.next();
                                        System.out.print("Masukkan nama barang rusak: ");
                                        namaBarangRusakArray[k] = sc.next();
                                        System.out.print("Masukkan keterangan kerusakan: ");
                                        keteranganRusakArray[k] = sc.next();
                                    }
                            
                                    System.out.println("===================================");
                                    System.out.println("        Laporan Barang Rusak       ");
                                    System.out.println("===================================");
                                    for (int k = 0; k < jumlahRusak; k++) {
                                        System.out.println(kodeBarangRusakArray[k] + " - " + namaBarangRusakArray[k] + " - " + keteranganRusakArray[k]);
                                    }
                                    break;
                                }
                            }
                        }
                    //////////////////////////////////////////////////////////////////////////////////////////
                    case 2:
                        if (currentRole.equals("Admin")) {
                            //input barang keluar
                            
                        } else if (currentRole.equals("Staff")) {
                            cariBarang();;
                            }
                            break;
                    /////////////////////////////////////////////////////////////////////////////////////////////////////               
                    case 3:
                    if (currentRole.equals("Admin")) {
                        //Daftar barang di Gudang Restoran
                        System.out.println("DAFTAR BARANG DI RESTORAN SEAFOOD");
                        int indexNow = 0;
                        for (int kategori = 0; kategori < 4; kategori++) {
                            String[] kodeArray = GudangRestoran[indexNow];
                            String[] namaArray = GudangRestoran[indexNow + 1];
                            String[] JmlArray = GudangRestoran[indexNow + 2];

                            String kategoriJudul = JenisJudul(kategori);
                            System.out.println("===== " + kategoriJudul + " =====");
                            System.out.printf("%-8s %-20s %-3s\n", "Kode", "Nama", "Jumlah");

                            for (int i = 0; i < kodeArray.length; i++) {
                                System.out.printf("%-8s %-20s %-3s\n", kodeArray[i], namaArray[i], JmlArray[i]);
                            }
                            System.out.println();

                            indexNow += 3;
                        }

                        System.out.println(RED + "\nPeringatan: Beberapa barang memiliki stok kurang dari 5. Segera restock!" + RESET);
                        System.out.println();
                        System.out.println("Daftar Barang yang Perlu Di-Restock:");
                        System.out.printf("%-8s %-20s %-3s\n", "Kode", "Nama", "Jumlah");

                        // Untuk menampilkan barang dengan stok sedikit.
                        for (int kategori = 0; kategori < 4; kategori++) {
                            String[] kodeArray = GudangRestoran[kategori * 3];
                            String[] namaArray = GudangRestoran[kategori * 3 + 1];
                            String[] JmlArray = GudangRestoran[kategori * 3 + 2];

                            for (int i = 0; i < kodeArray.length; i++) {
                                int jumlahStok = Integer.parseInt(JmlArray[i]);
                                if (jumlahStok < 5) {
                                    System.out.printf(RED + "%-8s %-20s %-3s\n" + RESET, kodeArray[i], namaArray[i], JmlArray[i]);
                                }
                            }
                        }
                    } else {
                        System.out.println("Anda tidak memiliki akses untuk menampilkan data barang.");
                    }
                    break;

                    ////////////////////////////////////////////////////////////////////////////////////////////////    
                    case 4:
                    if (currentRole.equals("Admin")) {
                        // Update data barang
                        System.out.println("===================================");
                        System.out.println("        Update Data Barang        ");
                        System.out.println("===================================");
                
                        System.out.print("Masukkan kode barang yang ingin diupdate: ");
                        String updateKode = sc.next();
                
                        boolean found = false;
                
                        for (int kategori = 0; kategori < 4; kategori++) {
                            String[] kodeArray = GudangRestoran[kategori * 3];
                            String[] namaArray = GudangRestoran[kategori * 3 + 1];
                            String[] JmlArray = GudangRestoran[kategori * 3 + 2];
                
                            for (int i = 0; i < kodeArray.length; i++) {
                                if (kodeArray[i].equalsIgnoreCase(updateKode)) {
                                    found = true;
                                    System.out.println("Data barang sebelum diupdate:");
                                    System.out.println("Kode: " + kodeArray[i]);
                                    System.out.println("Nama: " + namaArray[i]);
                                    System.out.println("Jumlah: " + JmlArray[i]);
                
                                    System.out.print("Masukkan jumlah barang baru (untuk menambah gunakan angka positif, untuk mengurangi gunakan angka negatif): ");
                                    int updateJumlah = sc.nextInt();
                
                                    // Update jumlah barang
                                    int currentJumlah = Integer.parseInt(JmlArray[i]);
                                    int newJumlah = currentJumlah + updateJumlah;
                
                                    // Pastikan stok tidak kurang dari 0
                                    if (newJumlah < 0) {
                                        System.out.println(RED + "\nStok tidak dapat kurang dari 0. Data barang tidak diupdate.\n" + RESET);
                                        break;
                                    }
                
                                    JmlArray[i] = String.valueOf(newJumlah);
                
                                    System.out.println(GREEN + "\nData barang telah diupdate.\n" + RESET);
                                    break;
                                }
                            }
                        }
                
                        if (!found) {
                            System.out.println("Barang dengan kode " + updateKode + " tidak ditemukan.");
                        }
                    } else {
                        System.out.println("Anda tidak memiliki akses untuk melakukan update data barang.");
                    }
                    break;
                    case 5:
                        cariBarang();
                    ///////////////////////////////////////////////////////////////////////////////////////////////////    
                    case 9:
                        // System.out.println(YELLOW+"=========================================="+RESET);
                        // System.out.println(GREEN+"Berhasil Logout dari " +currentRole + ": " + currentUser+RESET);
                        // System.out.println("Silahkan login !");
                        // // Kembali ke proses login
                        // boolean switchSuccess = false;
                        // while (true) {
                        //     System.out.print("Masukkan Username: ");
                        //     String switchUsername = sc.next();
                        //     System.out.print("Masukkan Password: ");
                        //     String switchPassword = sc.next();

                        //     for (int i = 0; i < userData.length; i++) {
                        //         if (userData[i][0].equals(switchUsername) && userData[i][1].equals(switchPassword)) {
                        //             currentUser = switchUsername;
                        //             currentRole = userData[i][2];
                        //             switchSuccess = true;
                        //             break;
                        //         }
                        //     }

                        //     if (switchSuccess) {
                        //         System.out.println(GREEN+"=== Beralih Akun Berhasil ==="+RESET);
                        //         isStaff = !isStaff;
                        //         isAdmin = !isAdmin;
                        //         break;
                        //     } else {
                        //         System.out.println("Login gagal. Silahkan coba lagi.");
                        //     }
                        // }
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
    }

    // FUNGSI
    static String[][] GudangRestoran= {
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


    // Fungsi untuk mencari barang
    public static void cariBarang() {        
        Scanner sc = new Scanner(System.in);
        System.out.print("Masukkan kode atau barang yang ingin dicari: ");
        String cariBarang = sc.nextLine();
        boolean ditemukan = false;

        for (int kategori = 0; kategori < 4; kategori++) {
            String[] kodeArray = GudangRestoran[kategori * 3];
            String[] namaArray = GudangRestoran[kategori * 3 + 1];
            String[] JmlArray = GudangRestoran[kategori * 3 + 2];

            String kategoriJudul = JenisJudul(kategori);
            for (int i = 0; i < kodeArray.length; i++) {
                if (kodeArray[i].equalsIgnoreCase(cariBarang) || namaArray[i].equalsIgnoreCase(cariBarang)) {
                    System.out.println("\n===== " + kategoriJudul + " =====");
                    System.out.println(kodeArray[i] + " - " + namaArray[i] + " = " + JmlArray[i]);
                    ditemukan = true;
                }
            }
        }
        if (!ditemukan) {
            System.out.println("Barang dengan kode atau nama " + cariBarang + " tidak ditemukan.");
        }
    }

    // Fungsi untuk menampilkan judul kategori
    public static String JenisJudul(int kategori) {
        switch (kategori) {
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
    // end of program
}
