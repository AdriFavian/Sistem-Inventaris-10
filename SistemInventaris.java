import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
// import java.time.format.DateTimeParseException;

public class SistemInventaris {
    static String[][] GudangRestoran = {
        //Daftar ikan
        {"I-001", "I-002", "I-003", "I-004", "I-005"},
        {"Patin", "Kakap", "Bawal", "Gurame", "Bandeng"},
        {"0", "0", "0", "0", "0"},
        {"kg", "kg", "kg", "kg", "kg"},
        {"", "", "", "", ""},
        
        // Daftar Hidangan laut
        {"H-001", "H-002", "H-003", "H-004"},
        {"Udang", "Cumi", "Kepiting", "Kerang" },
        {"0", "0", "0", "0"},
        {"kg", "kg", "kg", "kg"},
        {"", "", "", ""},

        //Daftar Bumbu Dapur
        {"B-001", "B-002", "B-003", "B-004", "B-005", "B-006", "B-007", "B-008", "B-009", 
        "B-010", "B-011", "B-012"},
        {"Bawang Putih", "Bawang Merah", "Merica","Garam", "Minyak Goreng", 
        "Saos Tiram", "Kecap", "Saos", "Jahe", "Kunyit", "Lengkuas", "Serai"},
        {"1", "3", "5", "7", "4", "9", "10", "5", "6", "4", "7", "8"},
        {"kg", "kg", "buah", "buah", "liter", "buah", "buah", "buah", "kg", "kg", "buah", "buah"},
        {"29-12-2023", "29-12-2023", "29-12-2023", "29-12-2023", "29-12-2023", "29-12-2023", "29-12-2023", "29-12-2023", "29-12-2023", "29-12-2023", "29-12-2023", "29-12-2023"},

        //Daftar Bahan Minuman
        {"M-001", "M-002", "M-003", "M-004"},
        {"Teh", "Lemon", "Air putih", "Es Batu"},
        {"7", "6", "10", "9"},
        {"buah", "kg", "galon", "box"},
        {"29-12-2023", "29-12-2023", "29-12-2023", "29-12-2023"},
    };
    
    // Warna Teks
    static String RESET = "\u001B[0m";
    static String RED = "\u001B[31m";
    static String GREEN = "\u001B[32m";
    static String YELLOW = "\u001B[33m";
    static String ORANGE = "\u001B[38;5;208m";
    static String BLUE = "\u001B[94m";
    static String CYAN = "\u001B[36m";

    //Array list
    static List<String[]> barangRusakList = new ArrayList<>();
    static List<String[]> barangMasukList = new ArrayList<>();
    static List<String[]> barangKeluarList = new ArrayList<>();
    static List<String[]> historyPenggunaan = new ArrayList<>();

    //Penanda waktu
    static LocalDateTime now = LocalDateTime.now();
    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    static String formattedDateTime = now.format(formatter);


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
        String currentUser = "";
        String currentRole = "";

        System.out.println(YELLOW+"=========================================="+RESET);
        System.out.println(YELLOW+"|   SELAMAT DATANG DI SISTEM INVENTARIS   |"+RESET);
        System.out.println(YELLOW+"=========================================="+RESET);

        //login
        while (!login) {
            System.out.println("LOGIN");
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
                System.out.println(RED+"\nMohon input data anda dengan benar !"+RESET);
                System.out.println();
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
                System.out.println("|6| History penggunaan sistem");
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
                    //|1| Input Data Barang ke Master
                        System.out.print(ORANGE+"Masukkan kode baru: "+RESET);
                        String newKode = sc.next();
                        System.out.print(ORANGE+"Masukkan nama baru: "+RESET);
                        String newNama = sc.next();
                        sc.nextLine();
                        System.out.print(ORANGE+"Masukkan jumlah baru: "+RESET);
                        String newJumlah = sc.next();
                        System.out.print(ORANGE+"Masukkan satuan baru: "+RESET);
                        String newSatuan = sc.next();
                        System.out.print(ORANGE+"Masukkan Tanggal Kadaluarsa (dd-MM-yyyy): "+RESET);
                        String tanggalKadaluarsa = sc.next();

                        DateTimeFormatter formatTanggal = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                        LocalDate tanggal = LocalDate.from(formatTanggal.parse(tanggalKadaluarsa));
                        
                        System.out.println();
                        System.out.println(GREEN + "Data barang baru telah disimpan!" + RESET);

                        inputData(newKode, newNama, newJumlah, newSatuan, tanggal );
                        System.out.println(BLUE + "[Data terakhir di update oleh Admin pada tanggal " + formattedDateTime  + "]"+ RESET);

                        historyPenggunaan.add(new String[] {
                            "Admin", "Input data barang ke master", formattedDateTime
                        });

                        break;
                        
                    } else if (currentRole.equals("Staff")) {
                        //|1| Input Data Barang Rusak
                        boolean ditemukan;
                        System.out.print("Masukkan berapa jenis barang yang ingin Anda input rusak: ");
                        int jumlahBarangRusak = sc.nextInt();
                        sc.nextLine(); // membersihkan newline dari buffer

                        for (int j = 0; j < jumlahBarangRusak; j++) {
                            System.out.print("Masukkan kode atau nama barang yang rusak: ");
                            String dataBarangRusak = sc.nextLine();

                            ditemukan = false;

                            int indeksKategori = -1;
                            int indeksBarang = -1;

                            for (int kategori = 0; kategori < 4; kategori++) {
                                String[] kodeArray = GudangRestoran[kategori * 5];
                                String[] namaArray = GudangRestoran[kategori * 5 + 1];

                                for (int i = 0; i < kodeArray.length; i++) {
                                    if (kodeArray[i] != null && namaArray[i] != null &&
                                        (kodeArray[i].equalsIgnoreCase(dataBarangRusak) || namaArray[i].equalsIgnoreCase(dataBarangRusak))) {
                                        indeksKategori = kategori;
                                        indeksBarang = i;
                                        ditemukan = true;
                                        break;
                                    }
                                }
                                if (ditemukan) {
                                    break;
                                }
                            }
                            //proses input data barang rusak
                            if (ditemukan) {
                                String[] kodeArray = GudangRestoran[indeksKategori * 5];
                                String[] namaArray = GudangRestoran[indeksKategori * 5 + 1];
                                String[] JmlArray = GudangRestoran[indeksKategori * 5 + 2];
                                String[] SatuanArray = GudangRestoran[indeksKategori * 5 + 3];
                                String[] KadaluarsaArray = GudangRestoran[indeksKategori * 5 + 4];

                                System.out.print("Masukkan Tanggal Kerusakan (dd-MM-yyyy): ");
                                String tanggalRusak = sc.next();
                                DateTimeFormatter formatTanggal = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                                LocalDate tanggal = LocalDate.from(formatTanggal.parse(tanggalRusak));

                                sc.nextLine(); // membersihkan newline dari buffer

                                System.out.print("Masukkan Keterangan Kerusakan: ");
                                String keteranganRusak = sc.nextLine();

                                System.out.print("Masukkan Jumlah Kerusakan: ");
                                int jumlahKerusakan = sc.nextInt();

                                barangRusakList.add(new String[]{
                                        kodeArray[indeksBarang], namaArray[indeksBarang], JmlArray[indeksBarang],
                                        SatuanArray[indeksBarang], KadaluarsaArray[indeksBarang], tanggal.format(formatTanggal),
                                        keteranganRusak, String.valueOf(jumlahKerusakan)
                                });

                                System.out.println();
                                System.out.println(GREEN + "Barang berhasil dicatat sebagai rusak." + RESET);
                                System.out.println(GREEN + "-------------------------------------"+ RESET);
                                sc.nextLine();
                            } else {
                                System.out.println(RED +"Note: " + "Barang dengan kode atau nama " + dataBarangRusak + " tidak ditemukan.\n" + RESET);
                            }
                        }
                    }
                    System.out.println(BLUE + "[Data terakhir di update oleh Staff pada tanggal " + now.format(formatter)  + "]"+ RESET);

                    historyPenggunaan.add(new String[] {
                        "Staff", "Input data barang rusak", now.format(formatter)
                    });
                    break;
                    //////////////////////////////////////////////////////////////////////////////////////////
                    
                case 2:
                    if (currentRole.equals("Admin")) {
                    //|2| Input Data Barang Masuk dan Keluar
                        boolean konfirmasiUser = false;
                        do {
                            System.out.println(YELLOW+"=========================================="+RESET);
                            System.out.println(YELLOW+"|   SELAMAT DATANG DI SISTEM INVENTARIS  |"+RESET);
                            System.out.println(YELLOW+"=========================================="+RESET);
                            System.out.println("Sekarang Anda Berada di "+"Submenu:"+GREEN+" Input Data Barang Masuk dan Keluar"+RESET);
                            System.out.println();
                            System.out.println("|1| Input Data Barang Masuk");
                            System.out.println("|2| Input Data Barang Keluar");
                            System.out.println(RED + "|0| Keluar" + RESET);
                            System.out.println();
                            System.out.print("Pilih Menu: ");
                            int userChoice = sc.nextInt();
                            System.out.println();
                            boolean ditemukan = false;
                            // boolean konfirmasi = false;

                            switch (userChoice) {
                                case 1:
                                // Input data masuk
                                System.out.print("Masukkan berapa jenis barang yang ingin anda masukkan: ");
                                int jumlahBarangInput = sc.nextInt();
                                sc.nextLine(); // membersihkan newline dari buffer

                                for (int j = 0; j < jumlahBarangInput; j++) {
                                    System.out.print("Masukkan kode atau nama barang: ");
                                    String dataBarang = sc.nextLine();
                                    System.out.println();

                                    ditemukan = false;

                                    int indeksKategori = -1;
                                    int indeksBarang = -1;

                                    for (int kategori = 0; kategori < 4; kategori++) {
                                        String[] kodeArray = GudangRestoran[kategori * 5];
                                        String[] namaArray = GudangRestoran[kategori * 5 + 1];
                                        String[] JmlArray = GudangRestoran[kategori * 5 + 2];
                                        String[] SatuanArray = GudangRestoran[kategori * 5 + 3];
                                        String[] KadaluarsaArray = GudangRestoran[kategori * 5 + 4];

                                        String kategoriJudul = JenisJudul(kategori);
                                        for (int i = 0; i < kodeArray.length; i++) {
                                            if (kodeArray[i].equalsIgnoreCase(dataBarang) || namaArray[i].equalsIgnoreCase(dataBarang)) {
                                                System.out.println(GREEN+"Barang ditemukan!"+RESET);
                                                System.out.println(CYAN + "                     =================================               " + RESET);
                                                System.out.println(CYAN + "\t\t     |\t    " + kategoriJudul + "     |"                          + RESET);
                                                System.out.println(CYAN + "                     =================================               " + RESET);
                                                System.out.println("=========================================================================================");
                                                System.out.println("| KODE   |       Nama Barang       | Jumlah Barang |     Satuan     |     Kadaluarsa    |");
                                                System.out.println("=========================================================================================");
                                                System.out.printf("| %-6s | %-23s |       %-8s| %-14.5s |     %-10s    |\n", kodeArray[i], namaArray[i], JmlArray[i], SatuanArray[i], KadaluarsaArray[i]);
                                                System.out.println("=========================================================================================");
                                                System.out.println();
                                                indeksKategori = kategori;
                                                indeksBarang = i;
                                                ditemukan = true;
                                                break;
                                            }
                                        }
                                        if (ditemukan) {
                                            break;
                                        }
                                    }

                                    if (ditemukan) {
                                        String[] kodeArray = GudangRestoran[indeksKategori * 5];
                                        String[] namaArray = GudangRestoran[indeksKategori * 5 + 1];
                                        String[] JmlArray = GudangRestoran[indeksKategori * 5 + 2];
                                        String[] SatuanArray = GudangRestoran[indeksKategori * 5 + 3];
                                        String[] KadaluarsaArray = GudangRestoran[indeksKategori * 5 + 4];
                                        // Menambah jumlah barang
                                        System.out.print("Masukkan jumlah barang yang ingin ditambahkan: ");
                                        int jumlahTambah = sc.nextInt();
                                        
                                        sc.nextLine(); // membersihkan newline dari buffer

                                        int jumlahSebelum = Integer.parseInt(JmlArray[indeksBarang]);
                                        int jumlahSetelah = jumlahSebelum + jumlahTambah;
                                        JmlArray[indeksBarang] = String.valueOf(jumlahSetelah);

                                        System.out.print("Apakah Anda ingin me-reset tanggal kadaluarsa?(ya/tidak): ");
                                        String konfirmasiTanggal = sc.nextLine();

                                        if (konfirmasiTanggal.equalsIgnoreCase("ya")) {
                                            KadaluarsaArray[indeksBarang] = "";
                                            System.out.print(ORANGE + "Tanggal kadaluarsa berhasil di reset." + RESET);

                                        } else if (konfirmasiTanggal.equalsIgnoreCase("tidak")) {
                                            System.out.print("Apakah Anda ingin mengganti tanggal kadaluarsa? (ya/tidak): ");
                                            String KonfirmasiTanggal2 = sc.nextLine();
                                            System.out.println();

                                            if(KonfirmasiTanggal2.equalsIgnoreCase("ya")) {
                                                System.out.print("Masukkan Tanggal Kadaluarsa Baru: ");
                                                KadaluarsaArray[indeksBarang] = sc.nextLine();
                                                System.out.println(ORANGE + "Tanggal kadaluarsa Berhasil Di Ganti." + RESET);

                                            } else {
                                                System.out.println(ORANGE + "Tanggal Kadaluarsa Tidak Di Update." + RESET);
                                            }

                                            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                                            barangMasukList.add(new String[]{
                                                kodeArray[indeksBarang], namaArray[indeksBarang], Integer.toString(jumlahSebelum), Integer.toString(jumlahSetelah),
                                                SatuanArray[indeksBarang], KadaluarsaArray[indeksBarang], LocalDate.now().format(dateFormatter).toString()
                                            });
                                        }

                                        System.out.println();
                                        System.out.println(GREEN + "Jumlah barang berhasil ditambahkan." + RESET);
                                        System.out.println(GREEN + "----------------------------------" + RESET);

                                    } else {
                                        System.out.println(RED + "Note: " +  "Barang dengan kode atau nama " + dataBarang + " tidak ditemukan.\n" + RESET);
                                    }

                                    historyPenggunaan.add(new String[] {
                                        "Admin", "Input data barang masuk", getFormattedDateTime()
                                    });

                                }
                                System.out.println(BLUE + "[Data terakhir di update oleh Admin pada tanggal " + formattedDateTime  + "]"+ RESET);
                                System.out.println();
                                break;

                                case 2:
                                 // Input data keluar
                                System.out.print("Masukkan berapa jenis barang yang ingin Anda input keluar: ");
                                jumlahBarangInput = sc.nextInt();
                                sc.nextLine(); // membersihkan newline dari buffer

                                for (int j = 0; j < jumlahBarangInput; j++) {
                                    System.out.print("Masukkan kode atau nama barang: ");
                                    String dataBarang = sc.nextLine();

                                    ditemukan = false;

                                    int indeksKategori = -1;
                                    int indeksBarang = -1;

                                    for (int kategori = 0; kategori < 4; kategori++) {
                                        String[] kodeArray = GudangRestoran[kategori * 5];
                                        String[] namaArray = GudangRestoran[kategori * 5 + 1];
                                        String[] JmlArray = GudangRestoran[kategori * 5 + 2];
                                        String[] SatuanArray = GudangRestoran[kategori * 5 + 3];
                                        String[] KadaluarsaArray = GudangRestoran[kategori * 5 + 4];

                                        String kategoriJudul = JenisJudul(kategori);
                                        for (int i = 0; i < kodeArray.length; i++) {
                                            if (kodeArray[i].equalsIgnoreCase(dataBarang) || namaArray[i].equalsIgnoreCase(dataBarang)) {
                                                System.out.println(GREEN+"Barang ditemukan!"+RESET);
                                                System.out.println(CYAN + "                     =================================               " + RESET);
                                                System.out.println(CYAN + "\t\t     |\t    " + kategoriJudul + "     |"                          + RESET);
                                                System.out.println(CYAN + "                     =================================               " + RESET);
                                                System.out.println("=========================================================================================");
                                                System.out.println("| KODE   |       Nama Barang       | Jumlah Barang |     Satuan     |     Kadaluarsa    |");
                                                System.out.println("=========================================================================================");
                                                System.out.printf("| %-6s | %-23s |       %-8s| %-14.5s |     %-10s    |\n", kodeArray[i], namaArray[i], JmlArray[i], SatuanArray[i], KadaluarsaArray[i]);
                                                System.out.println("=========================================================================================");
                                                System.out.println();
                                                indeksKategori = kategori;
                                                indeksBarang = i;
                                                ditemukan = true;
                                                break;
                                            }
                                        }
                                        if (ditemukan) {
                                            break;
                                        }
                                    }

                                    if (ditemukan) {
                                        String[] kodeArray = GudangRestoran[indeksKategori * 5];
                                        String[] namaArray = GudangRestoran[indeksKategori * 5 + 1];
                                        String[] JmlArray = GudangRestoran[indeksKategori * 5 + 2];
                                        String[] SatuanArray = GudangRestoran[indeksKategori * 5 + 3];
                                        String[] KadaluarsaArray = GudangRestoran[indeksKategori * 5 + 4];
                                        // Mengurangi jumlah barang
                                        System.out.print("Masukkan jumlah barang yang ingin dikeluarkan: ");
                                        int jumlahKurang = sc.nextInt();
                                        sc.nextLine(); // membersihkan newline dari buffer

                                        int jumlahSebelum = Integer.parseInt(JmlArray[indeksBarang]);
                                        int jumlahSetelah;

                                        if (jumlahSebelum > jumlahKurang) {
                                            jumlahSetelah = jumlahSebelum - jumlahKurang;

                                            System.out.print("Apakah Anda ingin me-reset tanggal kadaluarsa?(ya/tidak): ");
                                            String konfirmasiTanggal = sc.nextLine();

                                            if (konfirmasiTanggal.equalsIgnoreCase("ya")) {
                                                KadaluarsaArray[indeksBarang] = "";
                                                System.out.print(ORANGE + "Tanggal kadaluarsa berhasil di reset." + RESET);

                                            } else if (konfirmasiTanggal.equalsIgnoreCase("tidak")) {
                                                System.out.print("Apakah Anda ingin mengganti tanggal kadaluarsa? (ya/tidak): ");
                                                String KonfirmasiTanggal2 = sc.nextLine();
                                                System.out.println();

                                                if(KonfirmasiTanggal2.equalsIgnoreCase("ya")) {
                                                    System.out.print("Masukkan Tanggal Kadaluarsa Baru: ");
                                                    KadaluarsaArray[indeksBarang] = sc.nextLine();
                                                    System.out.println(ORANGE + "Tanggal kadaluarsa Berhasil Di Ganti." + RESET);

                                                } else {
                                                    System.out.println(ORANGE + "Tanggal Kadaluarsa Tidak Di Update." + RESET);
                                                }
                                            }

                                            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                                                barangKeluarList.add(new String[]{
                                                    kodeArray[indeksBarang], namaArray[indeksBarang], Integer.toString(jumlahSebelum), Integer.toString(jumlahSetelah),
                                                    SatuanArray[indeksBarang], KadaluarsaArray[indeksBarang], LocalDate.now().format(dateFormatter).toString()
                                                });

                                            System.out.println();
                                            System.out.println(GREEN + "Jumlah barang berhasil dikurangi." + RESET);
                                            System.out.println(GREEN + "---------------------------------" + RESET);
                                        } else {
                                            jumlahSetelah = jumlahSebelum;
                                            System.out.println(RED + "Jumlah barang yang Anda ingin dikeluarkan lebih besar dari stok. Data tetap." + RESET);
                                            System.out.println(RED + "---------------------------------------------------------------------------"+ RESET);
                                        }
                                        JmlArray[indeksBarang] = String.valueOf(jumlahSetelah);
                                        
                                    } else {
                                        System.out.println(RED + "Note: " + "Barang dengan kode atau nama " + dataBarang + " tidak ditemukan.\n" + RESET);
                                    }
                                    

                                    historyPenggunaan.add(new String[] {
                                        "Admin", "Input data barang keluar", getFormattedDateTime()
                                    });
                                }
                                System.out.println(BLUE + "[Data terakhir di update oleh Admin pada tanggal " + formattedDateTime  + "]"+ RESET);
                                System.out.println();
                                break;

                                case 0:
                                konfirmasiUser = true;
                                break;
                            }
                        } while (!konfirmasiUser);
                        

                    } else if (currentRole.equals("Staff")) {
                        //Pencarian Barang
                        cariDataBarang();

                        System.out.println(BLUE + "[Data terakhir di update oleh Staff pada tanggal " + formattedDateTime  + "]"+ RESET);

                        historyPenggunaan.add(new String[] {
                            "Staff", "Membuka Pencarian Barang", now.format(formatter)
                        });
                    }
                        System.out.println();
                        break;
                
                    /////////////////////////////////////////////////////////////////////////////////////////////////////               
                    case 3:
                        if (currentRole.equals("Admin")) {
                            // update data barang
                            // Dapatkan input dari pengguna untuk mencari nama atau kode
                            System.out.print(ORANGE+"Masukkan nama atau kode item yang akan diupdate: "+RESET);
                            String searchTerm = sc.nextLine();
                    
                            // Pilih jenis update
                            System.out.println("Pilih jenis update:");
                            System.out.println("1. Kode");
                            System.out.println("2. Nama Barang");
                            System.out.println("3. Satuan Barang");
                            System.out.println("4. Tanggal Kadaluarsa");
                            System.out.print(ORANGE+"Masukkan pilihan (1/2/3/4): "+RESET);
                            int choice = sc.nextInt();
                    
                            // Cari item dan update informasinya
                            boolean itemDitemukan = updateItem(searchTerm, choice, sc);
                    
                            // Tampilkan array yang sudah diupdate
                            if (itemDitemukan) {
                                System.out.println();
                                System.out.println(GREEN + "Data berhasil diupdate!" + RESET);
                                System.out.println();
                                for (int kategori = 0; kategori < 4; kategori++) {
                                    String[] kodeArray = GudangRestoran[kategori * 5];
                                    String[] namaArray = GudangRestoran[kategori * 5 + 1];
                                    String[] JmlArray = GudangRestoran[kategori * 5 + 2];
                                    String[] SatuanArray = GudangRestoran[kategori * 5 + 3];
                                    String[] KadaluarsaArray = GudangRestoran[kategori * 5 + 4];

                                    String kategoriJudul = JenisJudul(kategori);
                                    System.out.println(CYAN + "                     =================================               " + RESET);
                                    System.out.println(CYAN + "\t\t      \t    " + kategoriJudul + "     "                          + RESET);
                                    System.out.println(CYAN + "                     =================================               "+   RESET);
                                    System.out.println("=========================================================================================");
                                    System.out.println("| KODE   |       Nama Barang       | Jumlah Barang |     Satuan     |     Kadaluarsa    |");
                                    System.out.println("=========================================================================================");
                                    for (int i =0; i<kodeArray.length; i++) {
                                        System.out.printf("| %-6s | %-23s |       %-8s| %-14.5s |     %-10s    |\n", kodeArray[i], namaArray[i], JmlArray[i], SatuanArray[i], KadaluarsaArray[i]);

                                    }
                                    System.out.println("=========================================================================================");
                                    System.out.println();
                                }
                                } else {
                                    System.out.println();
                                    System.out.println(RED+ "Item tidak ditemukan."+ RESET);
                                }

                                System.out.println(BLUE + "[Data terakhir di update oleh Admin pada tanggal " + formattedDateTime  + "]"+ RESET);

                                    historyPenggunaan.add(new String[] {
                                        "Admin", "Update data barang", now.format(formatter)
                                    });
                                break;
                                
                        } else if (currentRole.equals("Staff")) {
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
                                        //Laporan barang masuk
                                        TampilkanBarangMasuk(barangMasukList);
                                        break;
                                    case 2:
                                        //Laporan barang keluar
                                        TampilkanBarangKeluar(barangKeluarList);
                                        break;
                                    case 3:
                                        // Laporan barang rusak
                                        if (!barangRusakList.isEmpty()) {
                                            System.out.println(GREEN + "DEAR STAFF, BERIKUT INI ADALAH DATA BARANG YANG SUDAH ANDA CATAT SEBAGAI BARANG RUSAK." + RESET);
                                            tampilkanLaporanBarangRusak(barangRusakList);
                                        } else {
                                            System.out.println(GREEN + "TIDAK ADA LAPORAN BARANG RUSAK.\n" + RESET);
                                        }
                                        break;
                                    case 4:
                                        //Laporan Stock Barang sekarang
                                        TampilkanStockSekarang();
                                        break;
                                    case 5:
                                        LaporanBarangPalingBanyakKeluar();
                                        break;
                                    case 6:
                                        // Laporan barang sedikit atau habis
                                        TampilkanBarangHabis();
                                        break;
                                    case 0:
                                    konfirmasiUser = false;
                                    break;                    
                                }

                            } while (konfirmasiUser);
                                break;
                            }
                            break;
                    ////////////////////////////////////////////////////////////////////////////////////////////////    
                    case 4:
                        if (currentRole.equals("Admin")) {
                            //Mencari data barang
                            cariDataBarang();
                        }
                                
                        System.out.println(BLUE + "[Data terakhir di update oleh Admin pada tanggal " + formattedDateTime  + "]"+ RESET);

                        historyPenggunaan.add(new String[] {
                            "Admin", "Mencari data barang", now.format(formatter)
                        });
                            System.out.println();
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
                                    //Laporan barang masuk
                                    TampilkanBarangMasuk(barangMasukList);
                                    break;
                                case 2:
                                    //Laporan barang keluar
                                    TampilkanBarangKeluar(barangKeluarList);
                                    break;
                                case 3:
                                    // Laporan barang rusak
                                    if (!barangRusakList.isEmpty()) {
                                        System.out.println(RED + "NOTE : DEAR ADMIN, SEGERA KELUARKAN BARANG BERIKUT INI KARENA MENGALAMI KERUSAKAN." + RESET);
                                        tampilkanLaporanBarangRusak(barangRusakList);
                                    } else {
                                        System.out.println(GREEN + "Belum ada data laporan barang rusak dari Staff" + RESET);
                                    }
                                    break;
                                case 4:
                                    //Laporan Stock Barang sekarang
                                    TampilkanStockSekarang();
                                    break;
                                case 5:
                                    LaporanBarangPalingBanyakKeluar();
                                    break;
                                case 6:
                                    // Laporan barang sedikit atau habis
                                    TampilkanBarangHabis();
                                    break;
                                case 0:
                                konfirmasiUser = false;
                                break;                               
                            }

                        } while (konfirmasiUser);
                        break;
                    case 6:
                    //History penggunaan sistem
                        historyPenggunaanSistem(historyPenggunaan);
                        break;
                    ///////////////////////////////////////////////////////////////////////////////////////////////////    
                    case 9:
                    //|9|Beralih Akun
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
                                System.out.println(GREEN+"Beralih Akun Berhasil"+RESET);
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
                        System.out.println(RED+"Pilihan tidak valid !"+RESET);
                        break;
                    }
                break;
            } while (isLoop);
        } while (!exit);    
        //selamat anda berada di akhir program :D
        sc.close();
    }





////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////    
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

    //Fungsi Mencari Data Barang
    static void cariDataBarang(){
        Scanner sc = new Scanner(System.in);
        System.out.print(ORANGE+"Masukkan kode atau barang yang ingin dicari: "+RESET);
        String cariBarang = sc.nextLine();
        boolean ditemukan = false;

        for (int kategori = 0; kategori < 4; kategori++) {
            String[] kodeArray = GudangRestoran[kategori * 5];
            String[] namaArray = GudangRestoran[kategori * 5 + 1];
            String[] JmlArray = GudangRestoran[kategori * 5 + 2];
            String[] SatuanArray = GudangRestoran[kategori * 5 + 3];
            String[] KadaluarsaArray = GudangRestoran[kategori * 5 + 4];

            String kategoriJudul = JenisJudul(kategori);
            for (int i = 0; i < kodeArray.length; i++) {
                if (kodeArray[i].equalsIgnoreCase(cariBarang) || namaArray[i].equalsIgnoreCase(cariBarang)) {
                    System.out.println(CYAN + "                     =================================               " + RESET);
                    System.out.println(CYAN + "\t\t     |\t    " + kategoriJudul + "     |"                          + RESET);
                    System.out.println(CYAN + "                     =================================               " + RESET);
                    System.out.println("=========================================================================================");
                    System.out.println("| KODE   |       Nama Barang       | Jumlah Barang |     Satuan     |     Kadaluarsa    |");
                    System.out.println("=========================================================================================");
                    System.out.printf("| %-6s | %-23s |       %-8s| %-14.5s |     %-10s    |\n", kodeArray[i], namaArray[i], JmlArray[i], SatuanArray[i], KadaluarsaArray[i]);
                    System.out.println("=========================================================================================");
                    System.out.println();
                    ditemukan = true;
                }
            }
        }
        if (!ditemukan) {
            System.out.println(RED + "Note: " + "Barang dengan kode atau nama " + cariBarang + " tidak ditemukan.\n" + RESET);
        }
    }
    //Fungsi untuk meng-update data barang
    static boolean updateItem(String searchTerm, int choice, Scanner sc) {
        boolean ditemukan = false;
        
        for (int kategori = 0; kategori < 4; kategori++) {
            String[] kodeArray = GudangRestoran[kategori * 5];
            String[] namaArray = GudangRestoran[kategori * 5 + 1];
            String[] JmlArray = GudangRestoran[kategori * 5 + 2];
            String[] SatuanArray = GudangRestoran[kategori * 5 + 3];
            String[] KadaluarsaArray = GudangRestoran[kategori * 5 + 4];
    
            for (int i = 0; i < kodeArray.length; i++) {
                if (kodeArray[i].equalsIgnoreCase(searchTerm) || namaArray[i].equalsIgnoreCase(searchTerm)) {
                    System.out.println("\n===== Update Data =====");
                    System.out.println("Kode\t\t: " + kodeArray[i]);
                    System.out.println("Nama Barang\t: " + namaArray[i]);
                    System.out.println("Jumlah\t\t: " + JmlArray[i]);
                    System.out.println("Satuan\t\t: " + SatuanArray[i]);
                    System.out.println("Kadaluarsa\t: "+ KadaluarsaArray[i]);
                    sc.nextLine();
    
                    // Meminta pengguna untuk memasukkan informasi baru
                    switch (choice) {
                        case 1:
                            System.out.print(ORANGE+"Masukkan kode baru: "+RESET);
                            kodeArray[i] = sc.nextLine();
                            break;
                        case 2:
                            System.out.print(ORANGE+"Masukkan nama baru: "+RESET);
                            namaArray[i] = sc.nextLine();
                            break;
                        case 3:
                            System.out.print(ORANGE+"Masukkan satuan baru: "+RESET);
                            SatuanArray[i] = sc.nextLine();
                            break;
                        case 4:
                            System.out.print(ORANGE+"Masukkan tanggal kadaluarsa baru: "+RESET);
                            KadaluarsaArray[i] = sc.nextLine();
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
    
//Fungsi untuk menambahkan data ke master
    static void inputData(String kode, String nama, String jumlah, String satuan, LocalDate kadaluarsa) {
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
            System.out.println(RED+"Kategori tidak valid."+RESET);
            return;
        }

        // Menambahkan data baru ke dalam array
        GudangRestoran[kategori * 5] = Arrays.copyOf(GudangRestoran[kategori * 5], GudangRestoran[kategori * 5].length + 1);
        GudangRestoran[kategori * 5 + 1] = Arrays.copyOf(GudangRestoran[kategori * 5 + 1], GudangRestoran[kategori * 5 + 1].length + 1);
        GudangRestoran[kategori * 5 + 2] = Arrays.copyOf(GudangRestoran[kategori * 5 + 2], GudangRestoran[kategori * 5 + 2].length + 1);
        GudangRestoran[kategori * 5 + 3] = Arrays.copyOf(GudangRestoran[kategori * 5 + 3], GudangRestoran[kategori * 5 + 3].length + 1);
        GudangRestoran[kategori * 5 + 4] = Arrays.copyOf(GudangRestoran[kategori * 5 + 4], GudangRestoran[kategori * 5 + 4].length + 1);

        int lastIndex = GudangRestoran[kategori * 5].length - 1;
        GudangRestoran[kategori * 5][lastIndex] = kode;
        GudangRestoran[kategori * 5 + 1][lastIndex] = nama;
        GudangRestoran[kategori * 5 + 2][lastIndex] = jumlah;
        GudangRestoran[kategori * 5 + 3][lastIndex] = satuan;
        GudangRestoran[kategori * 5 + 4][lastIndex] = kadaluarsa.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));

    }

    static void sortArrayDescending() {
        for (int kategori = 0; kategori < 4; kategori++) {
            int length = GudangRestoran[kategori * 5].length;
            String[][] tempArray = new String[5][length];

            // Menggabungkan data ke dalam array sementara
            for (int i = 0; i < length; i++) {
                tempArray[0][i] = GudangRestoran[kategori * 5][i];
                tempArray[1][i] = GudangRestoran[kategori * 5 + 1][i];
                tempArray[2][i] = GudangRestoran[kategori * 5 + 2][i];
                tempArray[3][i] = GudangRestoran[kategori * 5 + 3][i];
                tempArray[4][i] = GudangRestoran[kategori * 5 + 3][i];
            }

            // Mengurutkan array sementara berdasarkan kode secara descending
            Arrays.sort(tempArray, Comparator.comparing((String[] arr) -> arr[0]).reversed());

            // Menggabungkan array sementara ke dalam array utama
            for (int i = 0; i < length; i++) {
                GudangRestoran[kategori * 5][i] = tempArray[0][i];
                GudangRestoran[kategori * 5+ 1][i] = tempArray[1][i];
                GudangRestoran[kategori * 5 + 2][i] = tempArray[2][i];
                GudangRestoran[kategori * 5 + 3][i] = tempArray[3][i];
                GudangRestoran[kategori * 5 + 4][i] = tempArray[4][i];
            }
        }
    }

    //Fungsi untuk menampilkan laporan barang rusak
    public static void tampilkanLaporanBarangRusak(List<String[]> barangRusakList) {
        if (!barangRusakList.isEmpty()) {
            System.out.println(CYAN+"                                                    =======================================");
            System.out.println("                                                    |       LAPORAN DATA BARANG RUSAK     |"      );
            System.out.println("                                                    ======================================="+RESET);
            System.out.println("============================================================================================================================================");
            System.out.println("| KODE   |       Nama Barang       | Jumlah |     Satuan     |     Kadaluarsa    | Tanggal Rusak | Keterangan Kerusakan | Jumlah Kerusakan |");
            System.out.println("============================================================================================================================================");

                for (String[] detailBarang : barangRusakList) {
                    System.out.printf("| %-6s | %-23s | %-6s | %-14s | %-17s | %-13s | %-21s | %-16.5s |\n",
                    detailBarang[0], detailBarang[1], detailBarang[2], detailBarang[3], detailBarang[4], detailBarang[5],
                    detailBarang[6], detailBarang[7]);
                }

                    System.out.println("============================================================================================================================================");
                    
        } else {
                    System.out.println("Tidak ada barang yang rusak.");
            }
    }
    
    //Fungsi untuk menampilkan stok data barang saat ini
    public static void TampilkanStockSekarang() {
        System.out.println(ORANGE + "=====================================================================" + RESET);
        System.out.println(ORANGE +"|                     LAPORAN STOCK BARANG SEKARANG                 |"  + RESET);
        System.out.println(ORANGE+"=====================================================================" + RESET);
        System.out.println();
                                    
        for (int kategori = 0; kategori < 4; kategori++) {
            String[] kodeArray = GudangRestoran[kategori * 5];
            String[] namaArray = GudangRestoran[kategori * 5 + 1];
            String[] JmlArray = GudangRestoran[kategori * 5 + 2];
            String[] SatuanArray = GudangRestoran[kategori * 5 + 3];
            String[] KadaluarsaArray = GudangRestoran[kategori * 5 + 4];

            String kategoriJudul = JenisJudul(kategori);
            System.out.println(CYAN + "                     =================================               " + RESET);
            System.out.println(CYAN + "\t\t     |\t    " + kategoriJudul + "     |"                          + RESET);
            System.out.println(CYAN + "                     =================================               " + RESET);
            System.out.println("=========================================================================================");
            System.out.println("| KODE   |       Nama Barang       | Jumlah Barang |     Satuan     |     Kadaluarsa    |");
            System.out.println("=========================================================================================");
            for (int i =0; i<kodeArray.length; i++) {
                System.out.printf("| %-6s | %-23s |       %-8s| %-14.5s |     %-10s    |\n", kodeArray[i], namaArray[i], JmlArray[i], SatuanArray[i], KadaluarsaArray[i]);

            }
                System.out.println("=========================================================================================");
                System.out.println();

        }
    }
    
    //Fungsi untuk menampilkan laporan barang yang sedikit atau habis
    public static void TampilkanBarangHabis() {
    boolean konfirmasiBarangSedikit = false;
    System.out.println(RED + "NOTE :  TERDAPAT STOK BARANG SEDIKIT ATAU HABIS" + RESET);
    System.out.println(RED + "SEGERA RE-STOCK BARANG-BARANG !" + RESET);
    System.out.println();
    System.out.println(BLUE + "                 ==========================================            " + RESET);
    System.out.println(BLUE + "                 | LAPORAN DATA BARANG SEDIKIT ATAU HABIS |            " + RESET);
    System.out.println(BLUE + "                 ==========================================            " + RESET);
    System.out.println();
    if (konfirmasiBarangSedikit = true) {
        System.out.println("=========================================================================================");
        System.out.println("| KODE   |       Nama Barang       | Jumlah Barang |     Satuan     |     Kadaluarsa    |");
        System.out.println("=========================================================================================");
    }
                                    
        for (int kategori = 0; kategori < 4; kategori++) {
            String[] kodeArray = GudangRestoran[kategori * 5];
            String[] namaArray = GudangRestoran[kategori * 5 + 1];
            String[] JmlArray = GudangRestoran[kategori * 5 + 2];
            String[] SatuanArray = GudangRestoran[kategori * 5 + 3];
            String[] KadaluarsaArray = GudangRestoran[kategori * 5 + 4 ];

            for (int i =0; i<kodeArray.length; i++) {
                int jumlahStok = Integer.parseInt(JmlArray[i]);
                    if (jumlahStok <= 3) {
                        System.out.printf("| %-6s | %-23s |       %-8s| %-14.5s |     %-10s    |\n", kodeArray[i], namaArray[i], JmlArray[i], SatuanArray[i], KadaluarsaArray[i]);
                            konfirmasiBarangSedikit = true;
                    } 
                }
            }
            System.out.println("=========================================================================================");
            System.out.println();

            if (!konfirmasiBarangSedikit) {
                System.out.println(GREEN + "               TIDAK ADA BARANG HABIS ATAU TINGGAL SEDIKIT            " + RESET);
                System.out.println(GREEN + "                 CEK KEMBALI DATA BARANG NANTI ATAU BESOK           " + RESET);
            }
    }

    //Fungsi untuk menampilkan laporan barang masuk
    public static void TampilkanBarangMasuk(List<String[]> barangMasukList) {
        if (!barangMasukList.isEmpty()) {
            System.out.println("                           =======================================");
            System.out.println("                                    LAPORAN DATA BARANG MASUK"      );
            System.out.println("                           =======================================");
            System.out.println("========================================================================================================================");
            System.out.println("| KODE   |       Nama Barang       | Jumlah Sebelum | Jumlah Saat ini |  Satuan  | Kadaluarsa |     Tanggal Input      |");
            System.out.println("========================================================================================================================");

            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            for (String[] detailBarang : barangMasukList) {
                System.out.printf("| %-6s | %-23s | %-14s | %-15.5s | %-8s | %-10s | %-23s |\n",
                detailBarang[0], detailBarang[1], detailBarang[2], detailBarang[3], detailBarang[4], detailBarang[5], LocalDate.now().format(dateFormatter).toString());
            }
                System.out.println("========================================================================================================================");
                    
        } else {
                System.out.println(RED +  "Tidak ada barang yang di input." + RESET);
                System.out.println(RED + "Note : Silahkan input barang terlebih dahulu!" + RESET);
            }
    }

    //Fungsi untuk menampilkan laporan barang keluar
    public static void TampilkanBarangKeluar(List<String[]> barangKeluarList) {
        if (!barangKeluarList.isEmpty()) {
            System.out.println("                           =======================================");
            System.out.println("                                   LAPORAN DATA BARANG KELUAR"    );
            System.out.println("                           =======================================");
            System.out.println();
            System.out.println("========================================================================================================================");
            System.out.println("| KODE   |       Nama Barang       | Jumlah Sebelum | Jumlah Saat ini |  Satuan  | Kadaluarsa |     Tanggal Input      |");
            System.out.println("========================================================================================================================");

            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            for (String[] detailBarang : barangMasukList) {
                System.out.printf("| %-6s | %-23s | %-14s | %-15.5s | %-8s | %-10s | %-23s |\n",
                detailBarang[0], detailBarang[1], detailBarang[2], detailBarang[3], detailBarang[4], detailBarang[5], LocalDate.now().format(dateFormatter).toString());
            }
                System.out.println("================================================================================================================");
                    
        } else {
                System.out.println(RED + "Tidak ada barang yang di input." + RESET);
                System.out.println(RED + "Note : Silahkan input barang terlebih dahulu!" + RESET);
            }
    }

    //Fungsi Laporan data yang paling banyak keluar
    public static void LaporanBarangPalingBanyakKeluar() {
        if (!barangKeluarList.isEmpty()) {
            System.out.println("                           =======================================");
            System.out.println("                           LAPORAN DATA BARANG PALING BANYAK KELUAR"    );
            System.out.println("                           =======================================");
            System.out.println("========================================================================================================================");
            System.out.println("| KODE   |       Nama Barang       | Jumlah Sebelum | Jumlah Saat ini |  Satuan  | Kadaluarsa |     Tanggal Input      |");
            System.out.println("========================================================================================================================");

            int maxKeluar = 0;
            String[] detailBarangMaxKeluar = new String[7]; 
        
            for (String[] detailBarang : barangKeluarList) {
                int jumlahKeluar = Integer.parseInt(detailBarang[3]); 
        
                if (jumlahKeluar > maxKeluar) {
                    maxKeluar = jumlahKeluar;
                    detailBarangMaxKeluar = Arrays.copyOf(detailBarang, detailBarang.length);
                }
            }
        
            // Tampilkan barang dengan jumlah keluar paling banyak
            System.out.printf("| %-6s | %-23s | %-14s | %-15.5s | %-8s | %-10s | %-23s |\n",
                    detailBarangMaxKeluar[0], detailBarangMaxKeluar[1], detailBarangMaxKeluar[2],
                    detailBarangMaxKeluar[3], detailBarangMaxKeluar[4], detailBarangMaxKeluar[5],
                    detailBarangMaxKeluar[6]);
            
            System.out.println("========================================================================================================================");
        } else {
            System.out.println(RED + "Tidak ada barang yang dikeluarkan.\n" + RESET);
        }
        
    }

    //Fungsi untuk menampilkan history Penggunaan 
    public static void historyPenggunaanSistem (List<String[]> historyPenggunaan) {
        if (!historyPenggunaan.isEmpty()) {
            System.out.println("                           =======================================");
            System.out.println("                                   HISTORY PENGGUNAAN SISTEM"    );
            System.out.println("                           =======================================");
            System.out.println("===============================================================================================");
            System.out.println("| Role  |                       Kegiatan                               | Tanggal Penggunaan   |");
            System.out.println("===============================================================================================");
            
            for (String[] history : historyPenggunaan) {
                System.out.printf("| %-5s | %-60s | %-20s |\n",
                history[0], history[1], history[2]
                );
            }
            System.out.println("===============================================================================================");

        } else {
            System.out.println(GREEN + "Note : Belum ada history penggunaan sistem ini.\n" + RESET);
        }
    }

    // Fungsi untuk mendapatkan waktu aktual
    static String getFormattedDateTime() {
        LocalDateTime now = LocalDateTime.now();
        return now.format(formatter);
    }
}
