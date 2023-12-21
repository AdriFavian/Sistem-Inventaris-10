import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SistemInventaris {
    static String[][] GudangRestoran = {
        //Daftar ikan
        {"I-001", "I-002", "I-003", "I-004"},
        {"Patin", "Bawal", "Gurame", "Bandeng"},
        {"2", "3", "8", "6"},
        {"kg", "kg", "kg", "kg"},
        {"16-12-2023", "23-12-2023", "12-12-2023", "27-12-2023"},
        
        // Daftar Hidangan laut
        {"H-001", "H-002", "H-003", "H-004"},
        {"Udang", "Cumi", "Kepiting", "Kerang" },
        {"5", "8", "6", "7"},
        {"kg", "kg", "kg", "kg"},
        {"26-12-2023", "27-12-2023", "22-12-2023", "25-12-2023"},

        //Daftar Bumbu Dapur
        {"B-001", "B-002", "B-003", "B-004", "B-005", "B-006", "B-007", "B-008", "B-009", 
        "B-010", "B-011", "B-012"},
        {"Bawang Putih", "Bawang Merah", "Merica","Garam", "Minyak Goreng", 
        "Saos Tiram", "Kecap", "Saos", "Jahe", "Kunyit", "Lengkuas", "Serai"},
        {"1", "3", "5", "7", "4", "9", "10", "5", "6", "4", "7", "8"},
        {"kg", "kg", "buah", "buah", "liter", "buah", "buah", "buah", "kg", "kg", "buah", "buah"},
        {"29-12-2023", "29-12-2023", "22-12-2023", "29-12-2023", "29-12-2023", "29-12-2023", "29-12-2023", "29-12-2023", "29-12-2023", "29-12-2023", "29-12-2023", "29-12-2023"},

        //Daftar Bahan Minuman
        {"M-001", "M-002", "M-003", "M-004"},
        {"Teh", "Lemon", "Air putih", "Es Batu"},
        {"7", "6", "10", "9"},
        {"buah", "kg", "galon", "box"},
        {"29-12-2023", "29-12-2023", "29-12-2023", "18-12-2023"},
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
    static LocalDate hariIni = LocalDate.now();
    static DateTimeFormatter format= DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public static int langIndex = 0; //default 

    public static Scanner sc = new Scanner(System.in); //Scanner

    public static void main(String[] args) {
    
    while (true) {
        System.out.println(YELLOW+"=========================================="+RESET);
        System.out.println("      Choose language / Pilih bahasa");
        System.out.println(YELLOW+"=========================================="+RESET);
        System.out.println("|1| Bahasa Indonesia");
        System.out.println("|2| English");
        System.out.print("Enter your choice / Masukkan pilihan Anda (1-2): ");
        int langChoice = sc.nextInt();

        if (langChoice == 1) {
            langIndex = 0;
        } else if (langChoice == 2) {
            langIndex = 1;
        } else {
            System.out.println(RED+"Please enter a valid choice !"+RESET);
            System.out.println(RED+"Mohon cek inputan anda !"+RESET);
            continue;
        }    

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
        System.out.println(YELLOW+"|   "+messages[langIndex][96]+"   |"+RESET);
        System.out.println(YELLOW+"=========================================="+RESET);

        //login
        while (!login) {
            System.out.println("LOGIN");
            System.out.print(messages[langIndex][84]+" ");
            String inputUsername = sc.next();
            System.out.print(messages[langIndex][77]+" ");  
            String inputPassword = sc.next();

            for (int i = 0; i < userData.length ; i++ ) {
                if (userData[i][0].equals(inputUsername) && userData[i][1].equals(inputPassword)) {
                    System.out.println(GREEN+messages[langIndex][8]+" "+RESET);
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
                System.out.println(RED+"\n"+messages[langIndex][87]+RESET);
                System.out.println();
            }
        }

        do {//MENU UTAMA (AWAL)

            System.out.println(YELLOW+"=========================================="+RESET);
            System.out.println(YELLOW+"|   "+messages[langIndex][96]+"   |"+RESET);
            System.out.println(YELLOW+"=========================================="+RESET);
            
            if (isAdmin) {
                System.out.println(messages[langIndex][98]+" "+GREEN+currentRole+RESET+"\n");
                System.out.println("|1| "+messages[langIndex][28]);
                System.out.println("|2| "+messages[langIndex][27]);
                System.out.println("|3| "+messages[langIndex][121]);
                System.out.println("|4| "+messages[langIndex][89]);
                System.out.println("|5| "+messages[langIndex][49]);
                System.out.println("|6| "+messages[langIndex][23]);
                System.out.println(YELLOW+"|9| "+messages[langIndex][6]+RESET);
                System.out.println(RED+"|0| "+messages[langIndex][45]+RESET);
                
            } else if (isStaff) {
                System.out.println(messages[langIndex][98]+GREEN+currentRole+RESET+"\n");
                System.out.println("|1| "+messages[langIndex][31]);
                System.out.println("|2| "+messages[langIndex][89]);
                System.out.println("|3| "+messages[langIndex][49]);
                System.out.println(YELLOW+"|9| "+messages[langIndex][6]+RESET);
                System.out.println(RED+"|0| "+messages[langIndex][45]+RESET);
            }

            System.out.print("\n"+messages[langIndex][91]+" ");
            mainChoice = sc.nextInt();
            sc.nextLine();  
            clear();
            isLoop = true;

            do {//submenu
                switch (mainChoice) {
                case 1:
                    if (currentRole.equalsIgnoreCase("Admin")) {
                    //|1| Input Data Barang ke Master
                        inputDataMaster();
                        System.out.println(BLUE + "["+messages[langIndex][19]+" " + formattedDateTime  + "]"+ RESET);

                        historyPenggunaan.add(new String[] {
                            "Admin", "Input data barang ke master", formattedDateTime
                        });

                        break;
                        
                    } else if (currentRole.equals("Staff")) {
                        //|1| Input Data Barang Rusak
                        boolean ditemukan;
                        System.out.print(messages[langIndex][63]+" ");
                        int jumlahBarangRusak = sc.nextInt();
                        sc.nextLine(); // membersihkan newline dari buffer

                        for (int j = 0; j < jumlahBarangRusak; j++) {
                            System.out.print(messages[langIndex][73]+" ");
                            String dataBarangRusak = sc.nextLine();

                            ditemukan = false;

                            int indeksKategori = -1;
                            int indeksBarang = -1;

                            for (int kategori = 0; kategori < 4; kategori++) {
                                String[] kodeArray = GudangRestoran[kategori * 5];
                                String[] namaArray = GudangRestoran[kategori * 5 + 1];
                                String[] JmlArray = GudangRestoran[kategori * 5 + 2];
                                String[] SatuanArray = GudangRestoran[kategori * 5 + 3];
                                String[] KadaluarsaArray = GudangRestoran[kategori * 5 + 4];

                                for (int i = 0; i < kodeArray.length; i++) {
                                    if (kodeArray[i] != null && namaArray[i] != null &&
                                        (kodeArray[i].equalsIgnoreCase(dataBarangRusak) || namaArray[i].equalsIgnoreCase(dataBarangRusak))) {
                                            System.out.println("\n===== Data Saat Ini =====");
                                            System.out.println(messages[langIndex][47]+"\t\t: " + kodeArray[i]);
                                            System.out.println(messages[langIndex][88]+"\t: " + namaArray[i]);
                                            System.out.println(messages[langIndex][34]+"\t\t: " + JmlArray[i]);
                                            System.out.println(messages[langIndex][94]+"\t\t: " + SatuanArray[i]);
                                            System.out.println(messages[langIndex][42]+"\t: "+ KadaluarsaArray[i]);
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

                                System.out.print(messages[langIndex][83]+" ");
                                String tanggalRusak = sc.next();
                                DateTimeFormatter formatTanggal = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                                LocalDate tanggal = LocalDate.from(formatTanggal.parse(tanggalRusak));

                                sc.nextLine(); // membersihkan newline dari buffer

                                System.out.println();
                                System.out.print(messages[langIndex][70]+" ");
                                System.out.println("1. "+messages[langIndex][42]);
                                System.out.println("2. "+messages[langIndex][123]);
                                System.out.println("3. "+messages[langIndex][124]);
                                System.out.println("4. "+messages[langIndex][125]);
                                System.out.print(messages[langIndex][126]+" ");
                                String konfirmasiKeteranganRusak = sc.nextLine();

                                String keteranganRusak;
                                if (konfirmasiKeteranganRusak.equals("1")) {
                                    keteranganRusak = messages[langIndex][42];
                                } else if (konfirmasiKeteranganRusak.equals("2")) {
                                    keteranganRusak = messages[langIndex][123];
                                } else if (konfirmasiKeteranganRusak.equals("3")) {
                                    keteranganRusak = messages[langIndex][124];
                                } else {
                                    System.out.println(messages[langIndex][127]);
                                    keteranganRusak = sc.nextLine();
                                }

                                System.out.print(messages[langIndex][69]+" ");
                                int jumlahKerusakan = sc.nextInt();

                                barangRusakList.add(new String[]{
                                        kodeArray[indeksBarang], namaArray[indeksBarang], JmlArray[indeksBarang],
                                        SatuanArray[indeksBarang], KadaluarsaArray[indeksBarang], tanggal.format(formatTanggal),
                                        keteranganRusak, String.valueOf(jumlahKerusakan)
                                });

                                System.out.println();
                                System.out.println(GREEN + messages[langIndex][3] + RESET);
                                System.out.println(GREEN + "-------------------------------------"+ RESET);
                                sc.nextLine();
                            } else {
                                System.out.println(RED +"Note: " + messages[langIndex][4]+" " + dataBarangRusak + messages[langIndex][116]+" \n" + RESET);
                            }
                        }
                    }
                    System.out.println(BLUE + "["+messages[langIndex][20]+" " + now.format(formatter)  + "]"+ RESET);

                    historyPenggunaan.add(new String[] {
                        "Staff", messages[langIndex][32], now.format(formatter)
                    });
                    break;
                    //////////////////////////////////////////////////////////////////////////////////////////
                    
                case 2:
                    if (currentRole.equals("Admin")) {
                    //|2| Input Data Barang Masuk dan Keluar
                        boolean konfirmasiUser = false;
                        do {
                            System.out.println(YELLOW+"=========================================="+RESET);
                            System.out.println(YELLOW+"|   +"+messages[langIndex][96]+"  |"+RESET);
                            System.out.println(YELLOW+"=========================================="+RESET);
                            System.out.println(messages[langIndex][99]+" "+GREEN+messages[langIndex][27]+RESET);
                            System.out.println();
                            System.out.println("|1| "+messages[langIndex][25]);
                            System.out.println("|2| "+messages[langIndex][30]);
                            System.out.println(RED + "|0| "+messages[langIndex][45] + RESET);
                            System.out.println();
                            System.out.print(messages[langIndex][91]+" ");
                            int userChoice = sc.nextInt();
                            clear();
                            System.out.println();
                            boolean ditemukan = false;
                            // boolean konfirmasi = false;

                            switch (userChoice) {
                                case 1:
                                // Input data masuk
                                System.out.print(messages[langIndex][64]+" ");
                                int jumlahBarangInput = sc.nextInt();
                                sc.nextLine(); // membersihkan newline dari buffer

                                for (int j = 0; j < jumlahBarangInput; j++) {
                                    System.out.print(messages[langIndex][72]+" ");
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
                                                System.out.println(GREEN+messages[langIndex][5]+RESET);

                                                System.out.println(CYAN + "                                            =================================               " + RESET);
                                                System.out.println(CYAN + "                                            |      " + kategoriJudul +"     |" + RESET);
                                                System.out.println(CYAN + "                                            =================================               " + RESET);
                                                System.out.println("=========================================================================================================================");
                                                System.out.println("| "+messages[langIndex][48]+"   |       "+messages[langIndex][88]+"       | "+messages[langIndex][35]+" |     "+messages[langIndex][95]+"     |     "+messages[langIndex][94]+"    |     "+messages[langIndex][128]+"   |");
                                                System.out.println("=========================================================================================================================");
                                                
                                                if (KadaluarsaArray[i] != " ") {
                                                    LocalDate selisihTanggal = LocalDate.parse(KadaluarsaArray[i], format);
                                                    long selisihHari = hitungSelisihHari(selisihTanggal, hariIni);

                                                        if (selisihTanggal.isAfter(hariIni)) {
                                                            System.out.printf("| %-6s | %-23s |       %-8s| %-21s |     %-10s    |        %-10s      |\n", kodeArray[i], namaArray[i], JmlArray[i], SatuanArray[i], KadaluarsaArray[i], selisihHari + " "+messages[langIndex][129]);
                                                        } else if (selisihTanggal.isBefore(hariIni)) {
                                                            // Kadaluarsa kemarin, tampilkan pesan "sudah kadaluarsa" dengan warna merah
                                                            System.out.printf("| %-6s | %-23s |       %-8s| %-21s |     %-10s    |%-23s |\n",
                                                                    kodeArray[i], namaArray[i], JmlArray[i], SatuanArray[i], KadaluarsaArray[i], RED + messages[langIndex][129]+" " + selisihHari + " "+messages[langIndex][130] + RESET);
                                                        } else if (selisihTanggal == hariIni){
                                                            // Tanggal kadaluarsa hari ini
                                                            System.out.printf("| %-6s | %-23s |       %-8s| %-21s |     %-10s    |  %-23s  |\n", kodeArray[i], namaArray[i], JmlArray[i], SatuanArray[i], KadaluarsaArray[i], GREEN + " "+messages[langIndex][131] + RESET);
                                                        }
                                                } else {
                                                    System.out.printf("| %-6s | %-23s |       %-8s| %-21s |     %-10s    |  %-20s  |\n", kodeArray[i], namaArray[i], JmlArray[i], SatuanArray[i], KadaluarsaArray[i]," ");
                                                }
                                                    System.out.println("=========================================================================================================================");
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
                                        System.out.print(messages[langIndex][66]+" ");
                                        int jumlahTambah = sc.nextInt();
                                        
                                        sc.nextLine(); // membersihkan newline dari buffer

                                        System.out.println(messages[langIndex][132]+" ");
                                        System.out.println("1. "+messages[langIndex][133]);
                                        System.out.println("2. "+messages[langIndex][134]);
                                        System.out.println("3. "+messages[langIndex][135]);
                                        System.out.print(messages[langIndex][126]+" ");
                                        int pilihan = sc.nextInt();

                                        int jumlahSebelum, jumlahSetelah;
                                        switch (pilihan) {
                                            case 1:
                                                jumlahSebelum = Integer.parseInt(JmlArray[indeksBarang]);
                                                jumlahSetelah = jumlahSebelum + jumlahTambah;
                                                JmlArray[indeksBarang] = String.valueOf(jumlahSetelah);
                                                KadaluarsaArray[indeksBarang] = " ";
                                                System.out.print(ORANGE + messages[langIndex][106] + RESET);

                                                    barangMasukList.add(new String[]{
                                                        kodeArray[indeksBarang], namaArray[indeksBarang], Integer.toString(jumlahSebelum), Integer.toString(jumlahSetelah),
                                                        SatuanArray[indeksBarang], KadaluarsaArray[indeksBarang], LocalDate.now().format(format).toString()
                                                    });
                                                break;

                                            case 2:
                                                System.out.print(messages[langIndex][136]+" ");
                                                String tanggalKadaluarsa = sc.next();
                                                LocalDate tanggal = LocalDate.from(format.parse(tanggalKadaluarsa));

                                                inputData(kodeArray[indeksBarang] , namaArray[indeksBarang] + " add", Integer.toString(jumlahTambah), SatuanArray[indeksBarang], tanggal );
                                                break;

                                            case 3:
                                                jumlahSebelum = Integer.parseInt(JmlArray[indeksBarang]);
                                                jumlahSetelah = jumlahSebelum + jumlahTambah;
                                                JmlArray[indeksBarang] = String.valueOf(jumlahSetelah);
                                                System.out.println(ORANGE + messages[langIndex][107] + RESET);

                                                    barangMasukList.add(new String[]{
                                                        kodeArray[indeksBarang], namaArray[indeksBarang], Integer.toString(jumlahSebelum), Integer.toString(jumlahSetelah),
                                                        SatuanArray[indeksBarang], KadaluarsaArray[indeksBarang], LocalDate.now().format(format).toString()
                                                    });
                                                break;
                                                
                                        }

                                        System.out.println();
                                        System.out.println(GREEN + messages[langIndex][37] + RESET);
                                        System.out.println(GREEN + "----------------------------------" + RESET);

                                    } else {
                                        System.out.println(RED + "Note: " +  messages[langIndex][4]+" " + dataBarang + " "+messages[langIndex][118]+"\n" + RESET);
                                    }

                                    historyPenggunaan.add(new String[] {
                                        "Admin", messages[langIndex][26], getFormattedDateTime()
                                    });

                                    // LocalDate selisihTanggal;
                                    // for (int kategori = 0; kategori < 4; kategori++) {
                                    //     String[] kodeArray = GudangRestoran[kategori * 5];
                                    //     String[] namaArray = GudangRestoran[kategori * 5 + 1];
                                    //     String[] JmlArray = GudangRestoran[kategori * 5 + 2];
                                    //     String[] SatuanArray = GudangRestoran[kategori * 5 + 3];
                                    //     String[] KadaluarsaArray = GudangRestoran[kategori * 5 + 4];

                                    //     String kategoriJudul = JenisJudul(kategori);
                                    //     for (int i = 0; i < kodeArray.length; i++) {
                                    //         if (kodeArray[i].equalsIgnoreCase(dataBarang) || namaArray[i].equalsIgnoreCase(dataBarang)) {
                                    //             System.out.println(CYAN + "                                            =================================               " + RESET);
                                    //             System.out.println(CYAN + "                                            |      " + kategoriJudul +"     |" + RESET);
                                    //             System.out.println(CYAN + "                                            =================================               " + RESET);
                                    //             System.out.println("=========================================================================================================================");
                                    //             System.out.println("| KODE   |       Nama Barang       | Jumlah Barang |     Satuan Barang     |     Kadaluarsa    |     Kadaluarsa dalam   |");
                                    //             System.out.println("=========================================================================================================================");
                                                
                                    //             if (KadaluarsaArray[i] != " ") {
                                    //                 selisihTanggal = LocalDate.parse(KadaluarsaArray[i], format);
                                    //                 long selisihHari = hitungSelisihHari(selisihTanggal, hariIni);

                                    //                     if (selisihTanggal.isAfter(hariIni)) {
                                    //                         System.out.printf("| %-6s | %-23s |       %-8s| %-21s |     %-10s    |        %-10s      |\n", kodeArray[i], namaArray[i], JmlArray[i], SatuanArray[i], KadaluarsaArray[i], selisihHari + " hari");
                                    //                     } else if (selisihTanggal.isBefore(hariIni)) {
                                    //                         // Kadaluarsa kemarin, tampilkan pesan "sudah kadaluarsa" dengan warna merah
                                    //                         System.out.printf("| %-6s | %-23s |       %-8s| %-21s |     %-10s    |%-23s |\n",
                                    //                                 kodeArray[i], namaArray[i], JmlArray[i], SatuanArray[i], KadaluarsaArray[i], RED + "Sudah Kadaluarsa " + selisihHari + " hari" + RESET);
                                    //                     } else if (selisihTanggal == hariIni){
                                    //                         // Tanggal kadaluarsa hari ini
                                    //                         System.out.printf("| %-6s | %-23s |       %-8s| %-21s |     %-10s    |  %-23s  |\n", kodeArray[i], namaArray[i], JmlArray[i], SatuanArray[i], KadaluarsaArray[i], GREEN + " Kadaluarsa Hari Ini" + RESET);
                                    //                     }
                                    //             } else {
                                    //                 System.out.printf("| %-6s | %-23s |       %-8s| %-21s |     %-10s    |  %-20s  |\n", kodeArray[i], namaArray[i], JmlArray[i], SatuanArray[i], KadaluarsaArray[i]," ");
                                    //         }
                                    //             System.out.println("=========================================================================================================================");
                                    //             System.out.println();
                                                    
                                    //             }
                                    //         }
                                    //     }
                                    sc.nextLine();
                                }
                                System.out.println(BLUE + "["+messages[langIndex][19]+" " + formattedDateTime  + "]"+ RESET);
                                System.out.println();
                                break;

                                case 2:
                                 // Input data keluar
                                if (!barangRusakList.isEmpty()) {
                                    System.out.println(RED + messages[langIndex][137] + RESET);
                                    System.out.println(CYAN+"                                                    =======================================");
                                    System.out.println("                                                    |       "+messages[langIndex][56]+"     |"      );
                                    System.out.println("                                                    ======================================="+RESET);
                                    System.out.println("============================================================================================================================================");
                                    System.out.println("| "+messages[langIndex][48]+"   |       "+messages[langIndex][88]+"       | "+messages[langIndex][34]+" |     "+messages[langIndex][94]+"     |     "+messages[langIndex][42]+"    | "+messages[langIndex][103]+" | "+messages[langIndex][46]+" | "+messages[langIndex][39]+" |");
                                    System.out.println("============================================================================================================================================");

                                        for (String[] detailBarang : barangRusakList) {
                                            System.out.printf("| %-6s | %-23s |    %-4.9s| %-14s |     %-13s  |  %-11s  | %-21s|        %-10s|\n",
                                            detailBarang[0], detailBarang[1], detailBarang[2], detailBarang[3], detailBarang[4], detailBarang[5],
                                            detailBarang[6], detailBarang[7]);
                                        }

                                            System.out.println("============================================================================================================================================");
                                            System.out.println();
                                                        }
                                    System.out.print(messages[langIndex][65]+" ");
                                    jumlahBarangInput = sc.nextInt();
                                    sc.nextLine(); // membersihkan newline dari buffer

                                    for (int j = 0; j < jumlahBarangInput; j++) {
                                        System.out.print(messages[langIndex][72]+" ");
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
                                                System.out.println(GREEN+messages[langIndex][5]+RESET);
                                                System.out.println(CYAN + "                                            =================================               " + RESET);
                                                System.out.println(CYAN + "                                            |      " + kategoriJudul +"     |" + RESET);
                                                System.out.println(CYAN + "                                            =================================               " + RESET);
                                                System.out.println("=========================================================================================================================");
                                                System.out.println("| "+messages[langIndex][48]+"   |       "+messages[langIndex][88]+"       | "+messages[langIndex][35]+" |     "+messages[langIndex][95]+"     |     "+messages[langIndex][42]+"    |     "+messages[langIndex][128]+"   |");
                                                System.out.println("=========================================================================================================================");
                                                
                                                if (KadaluarsaArray[i] != " ") {
                                                    LocalDate selisihTanggal = LocalDate.parse(KadaluarsaArray[i], format);
                                                    long selisihHari = hitungSelisihHari(selisihTanggal, hariIni);

                                                    if (selisihTanggal.isAfter(hariIni)) {
                                                        System.out.printf("| %-6s | %-23s |       %-8s| %-21s |     %-10s    |        %-10s      |\n", kodeArray[i], namaArray[i], JmlArray[i], SatuanArray[i], KadaluarsaArray[i], selisihHari + " hari");
                                                        } else if (selisihTanggal.isBefore(hariIni)) {
                                                            // Kadaluarsa kemarin, tampilkan pesan "sudah kadaluarsa" dengan warna merah
                                                            System.out.printf("| %-6s | %-23s |       %-8s| %-21s |     %-10s    |%-23s |\n",
                                                                    kodeArray[i], namaArray[i], JmlArray[i], SatuanArray[i], KadaluarsaArray[i], RED + messages[langIndex][129]+" " + selisihHari + " "+messages[langIndex][130] + RESET);
                                                        } else if (selisihTanggal == hariIni){
                                                            // Tanggal kadaluarsa hari ini
                                                            System.out.printf("| %-6s | %-23s |       %-8s| %-21s |     %-10s    |  %-23s  |\n", kodeArray[i], namaArray[i], JmlArray[i], SatuanArray[i], KadaluarsaArray[i], GREEN + " "+messages[langIndex][131] + RESET);
                                                        }
                                                } else {
                                                    System.out.printf("| %-6s | %-23s |       %-8s| %-21s |     %-10s    |  %-20s  |\n", kodeArray[i], namaArray[i], JmlArray[i], SatuanArray[i], KadaluarsaArray[i]," ");
                                                }
                                                System.out.println("=========================================================================================================================");
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
                                        System.out.print(messages[langIndex][67]+" ");
                                        int jumlahKurang = sc.nextInt();
                                        sc.nextLine(); // membersihkan newline dari buffer

                                        int jumlahSebelum = Integer.parseInt(JmlArray[indeksBarang]);
                                        int jumlahSetelah;

                                        if (jumlahSebelum > jumlahKurang) {
                                            jumlahSetelah = jumlahSebelum - jumlahKurang;
                                            
                                            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                                                barangKeluarList.add(new String[]{
                                                    kodeArray[indeksBarang], namaArray[indeksBarang], Integer.toString(jumlahSebelum), Integer.toString(jumlahSetelah),
                                                    SatuanArray[indeksBarang], KadaluarsaArray[indeksBarang], LocalDate.now().format(dateFormatter).toString()
                                                });

                                            System.out.println();
                                            System.out.println(GREEN + messages[langIndex][36] + RESET);
                                            System.out.println(GREEN + "---------------------------------" + RESET);

                                        } else if (jumlahSebelum == jumlahKurang) {
                                            jumlahSetelah = jumlahSebelum - jumlahKurang;
                                            KadaluarsaArray[indeksBarang] = " ";

                                            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                                                barangKeluarList.add(new String[]{
                                                    kodeArray[indeksBarang], namaArray[indeksBarang], Integer.toString(jumlahSebelum), Integer.toString(jumlahSetelah),
                                                    SatuanArray[indeksBarang], KadaluarsaArray[indeksBarang], LocalDate.now().format(dateFormatter).toString()
                                                });

                                            System.out.println();
                                            System.out.println(RED + messages[langIndex][138] + RESET);
                                            System.out.println(RED + "----------------------------------------------------------------------" + RESET);
                                        
                                        } else  {
                                            jumlahSetelah = jumlahSebelum;
                                            System.out.println(RED + messages[langIndex][38] + RESET);
                                            System.out.println(RED + "---------------------------------------------------------------------------"+ RESET);
                                        }
                                        JmlArray[indeksBarang] = String.valueOf(jumlahSetelah);
                                        
                                    } else {
                                        System.out.println(RED + "Note: " + messages[langIndex][4]+" " + dataBarang + " "+messages[langIndex][116]+".\n" + RESET);                                    }
                                    
                                    // Menghapus Barang rusak jika sudah di keluarkan
                                    if(!barangRusakList.isEmpty()) {
                                    
                                        // Menyimpan indeks baris yang akan dihapus
                                        ArrayList<Integer> indeksBarisHapus = new ArrayList<>();
                                    
                                        for (int k = 0; k < barangRusakList.size(); k++) {
                                            String[] detailBarang = barangRusakList.get(k);
                                            if (dataBarang.equalsIgnoreCase(detailBarang[0]) || dataBarang.equalsIgnoreCase(detailBarang[1])) {
                                                indeksBarisHapus.add(k);
                                            }
                                        }
                                    
                                        // Menghapus baris sesuai dengan indeks yang disimpan
                                        for (int i = indeksBarisHapus.size() - 1; i >= 0; i--) {
                                            int indeksHapus = indeksBarisHapus.get(i);
                                            barangRusakList.remove(indeksHapus);
                                        }
                                    }
                                    
                                    //Menampilkan data barang setelah dikurangi
                                    LocalDate selisihTanggal;
                                    for (int kategori = 0; kategori < 4; kategori++) {
                                        String[] kodeArray = GudangRestoran[kategori * 5];
                                        String[] namaArray = GudangRestoran[kategori * 5 + 1];
                                        String[] JmlArray = GudangRestoran[kategori * 5 + 2];
                                        String[] SatuanArray = GudangRestoran[kategori * 5 + 3];
                                        String[] KadaluarsaArray = GudangRestoran[kategori * 5 + 4];

                                        String kategoriJudul = JenisJudul(kategori);
                                        for (int i = 0; i < kodeArray.length; i++) {
                                            if (kodeArray[i].equalsIgnoreCase(dataBarang) || namaArray[i].equalsIgnoreCase(dataBarang)) {
                                                System.out.println(CYAN + "                                            =================================               " + RESET);
                                                System.out.println(CYAN + "                                            |      " + kategoriJudul +"     |" + RESET);
                                                System.out.println(CYAN + "                                            =================================               " + RESET);
                                                System.out.println("=========================================================================================================================");
                                                System.out.println("| "+messages[langIndex][48]+"   |       "+messages[langIndex][88]+"       | "+messages[langIndex][35]+" |     "+messages[langIndex][95]+"     |     "+messages[langIndex][42]+"    |     "+messages[langIndex][128]+"   |");
                                                System.out.println("=========================================================================================================================");
                                                
                                                if (KadaluarsaArray[i] != " ") {
                                                    selisihTanggal = LocalDate.parse(KadaluarsaArray[i], format);
                                                    long selisihHari = hitungSelisihHari(selisihTanggal, hariIni);
                                                        if (selisihTanggal.isAfter(hariIni)) {
                                                            System.out.printf("| %-6s | %-23s |       %-8s| %-21s |     %-10s    |        %-10s      |\n", kodeArray[i], namaArray[i], JmlArray[i], SatuanArray[i], KadaluarsaArray[i], selisihHari + " "+messages[langIndex][130]);
                                                        } else if (selisihTanggal.isBefore(hariIni)) {
                                                            // Kadaluarsa kemarin, tampilkan pesan "sudah kadaluarsa" dengan warna merah
                                                            System.out.printf("| %-6s | %-23s |       %-8s| %-21s |     %-10s    |%-23s |\n",
                                                                    kodeArray[i], namaArray[i], JmlArray[i], SatuanArray[i], KadaluarsaArray[i], RED + messages[langIndex][129]+" " + selisihHari + " "+messages[langIndex][130] + RESET);
                                                        } else if (selisihTanggal == hariIni){
                                                            // Tanggal kadaluarsa hari ini
                                                            System.out.printf("| %-6s | %-23s |       %-8s| %-21s |     %-10s    |  %-23s  |\n", kodeArray[i], namaArray[i], JmlArray[i], SatuanArray[i], KadaluarsaArray[i], GREEN + " "+messages[langIndex][131] + RESET);
                                                        }
                                                } else {
                                                    System.out.printf("| %-6s | %-23s |       %-8s| %-21s |     %-10s    |  %-20s  |\n", kodeArray[i], namaArray[i], JmlArray[i], SatuanArray[i], KadaluarsaArray[i]," ");
                                                }
                                                System.out.println("=========================================================================================================================");
                                                System.out.println();
                                                    
                                                }
                                            }
                                        }
                                    

                                    historyPenggunaan.add(new String[] {
                                        "Admin ", messages[langIndex][30], getFormattedDateTime()
                                    });
                                }

                                
                                System.out.println(BLUE + "["+messages[langIndex][19]+" " + formattedDateTime  + "]"+ RESET);
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

                        System.out.println(BLUE + "["+messages[langIndex][20]+" " + formattedDateTime  + "]"+ RESET);

                        historyPenggunaan.add(new String[] {
                            "Staff ", messages[langIndex][85], now.format(formatter)
                        });
                    }
                        System.out.println();
                        break;
                
                    /////////////////////////////////////////////////////////////////////////////////////////////////////               
                    case 3:
                        if (currentRole.equals("Admin")) {
                            // update data barang
                            TampilkanStockSekarang();
                            System.out.print(ORANGE+messages[langIndex][75]+" "+RESET);
                            String searchTerm = sc.nextLine();
                    
                            // Pilih jenis update
                            System.out.println(messages[langIndex][90]);
                            System.out.println("1. "+messages[langIndex][47]);
                            System.out.println("2. "+messages[langIndex][88]);
                            System.out.println("3. "+messages[langIndex][95]);
                            System.out.println("4. "+messages[langIndex][104]);
                            System.out.print(ORANGE+messages[langIndex][78]+" "+RESET);
                            int choice = sc.nextInt();
                    
                            // Cari item dan update informasinya
                            boolean itemDitemukan = updateItem(searchTerm, choice, sc);
                    
                            // Tampilkan array yang sudah diupdate
                            if (itemDitemukan) {
                                System.out.println();
                                System.out.println(GREEN + messages[langIndex][18] + RESET);
                                System.out.println();

                                LocalDate selisihTanggal;
                                for (int kategori = 0; kategori < 4; kategori++) {
                                    String[] kodeArray = GudangRestoran[kategori * 5];
                                    String[] namaArray = GudangRestoran[kategori * 5 + 1];
                                    String[] JmlArray = GudangRestoran[kategori * 5 + 2];
                                    String[] SatuanArray = GudangRestoran[kategori * 5 + 3];
                                    String[] KadaluarsaArray = GudangRestoran[kategori * 5 + 4];

                                    String kategoriJudul = JenisJudul(kategori);
                                    for (int i = 0; i < kodeArray.length; i++) {
                                        if (kodeArray[i].equalsIgnoreCase(searchTerm) || namaArray[i].equalsIgnoreCase(searchTerm)) {
                                            System.out.println(CYAN + "                                            =================================               " + RESET);
                                            System.out.println(CYAN + "                                            |      " + kategoriJudul +"     |" + RESET);
                                            System.out.println(CYAN + "                                            =================================               " + RESET);
                                            System.out.println("=========================================================================================================================");
                                            System.out.println("| "+messages[langIndex][48]+"   |       "+messages[langIndex][88]+"       | "+messages[langIndex][35]+" |     "+messages[langIndex][95]+"     |     "+messages[langIndex][94]+"    |     "+messages[langIndex][128]+"   |");
                                            System.out.println("=========================================================================================================================");
                                            
                                            selisihTanggal = LocalDate.parse(KadaluarsaArray[i], format);
                                            long selisihHari = hitungSelisihHari(selisihTanggal, hariIni);
                                            if (selisihTanggal.isAfter(hariIni)) {
                                                System.out.printf("| %-6s | %-23s |       %-8s| %-21s |     %-10s    |        %-10s      |\n", kodeArray[i], namaArray[i], JmlArray[i], SatuanArray[i], KadaluarsaArray[i], selisihHari + " "+messages[langIndex][130]);
                                            } else if (selisihTanggal.isBefore(hariIni)) {
                                                // Kadaluarsa kemarin, tampilkan pesan "sudah kadaluarsa" dengan warna merah
                                                System.out.printf("| %-6s | %-23s |       %-8s| %-21s |     %-10s    |%-23s |\n",
                                                        kodeArray[i], namaArray[i], JmlArray[i], SatuanArray[i], KadaluarsaArray[i], RED + messages[langIndex][129]+" " + selisihHari + " "+messages[langIndex][130] + RESET);
                                            } else {
                                                // Tanggal kadaluarsa hari ini
                                                System.out.printf("| %-6s | %-23s |       %-8s| %-21s |     %-10s    |  %-23s  |\n", kodeArray[i], namaArray[i], JmlArray[i], SatuanArray[i], KadaluarsaArray[i], GREEN + " "+messages[langIndex][131] + RESET);
                                            }
                                            System.out.println("=========================================================================================================================");
                                            System.out.println();
                                                
                                            }
                                        }
                                    }
                                        
                                } else {
                                    System.out.println();
                                    System.out.println(RED+ messages[langIndex][33]+ RESET);
                                }
                                System.out.println(BLUE + "["+messages[langIndex][19]+" " + formattedDateTime  + "]"+ RESET);

                                    historyPenggunaan.add(new String[] {
                                        "Admin ", messages[langIndex][120], now.format(formatter)
                                    });
                            
                                
                        } else if (currentRole.equals("Staff")) {
                            boolean konfirmasiUser = true;
                            do {
                                System.out.println(YELLOW+"=========================================="+RESET);
                                System.out.println(YELLOW+"|   "+messages[langIndex][96]+"   |"+RESET);
                                System.out.println(YELLOW+"=========================================="+RESET);
                                System.out.println(messages[langIndex][99]+" "+GREEN+" "+messages[langIndex][49]+RESET);
                                System.out.println();
                                System.out.println("|1| "+messages[langIndex][52]);
                                System.out.println("|2| "+messages[langIndex][50]);
                                System.out.println("|3| "+messages[langIndex][55]);
                                System.out.println("|4| "+messages[langIndex][59]);
                                System.out.println("|5| "+messages[langIndex][61]);
                                System.out.println("|6| "+messages[langIndex][57]);
                                System.out.println(RED +"|0| "+messages[langIndex][45] + RESET);
                                System.out.println();
                                System.out.print(messages[langIndex][91]+" ");
                                int laporanUser = sc.nextInt();
                                clear();
                                
                                switch (laporanUser) {
                                    case 1:
                                        //Laporan barang masuk
                                        TampilkanBarangMasuk(barangMasukList);

                                        historyPenggunaan.add(new String[] {
                                        "Staff", messages[langIndex][139], now.format(formatter)
                                        });
                                        break;
                                    case 2:
                                        //Laporan barang keluar
                                        TampilkanBarangKeluar(barangKeluarList);

                                        historyPenggunaan.add(new String[] {
                                        "Staff", messages[langIndex][140], now.format(formatter)
                                        });
                                        break;
                                    case 3:
                                        // Laporan barang rusak
                                        if (!barangRusakList.isEmpty()) {
                                            System.out.println(GREEN + messages[langIndex][22] + RESET);
                                            tampilkanLaporanBarangRusak(barangRusakList);
                                        } else {
                                            System.out.println(GREEN + messages[langIndex][117]+"\n" + RESET);
                                        }

                                        historyPenggunaan.add(new String[] {
                                        "Staff",  messages[langIndex][141], now.format(formatter)
                                        });
                                        break;
                                    case 4:
                                        //Laporan Stock Barang sekarang
                                        TampilkanStockSekarang();
                                        System.out.println(BLUE + "["+ messages[langIndex][142]+" " + formattedDateTime  + "]"+ RESET);
                                        historyPenggunaan.add(new String[] {
                                        "Staff", messages[langIndex][143], now.format(formatter)
                                        });

                                        break;
                                    case 5:
                                        LaporanBarangPalingBanyakKeluar();

                                        historyPenggunaan.add(new String[] {
                                        "Staff", messages[langIndex][144], now.format(formatter)
                                        });
                                        break;
                                    case 6:
                                        // Laporan barang sedikit atau habis
                                        TampilkanBarangHabis();
                                        historyPenggunaan.add(new String[] {
                                        "Staff", messages[langIndex][145], now.format(formatter)
                                        });
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
                                
                        System.out.println(BLUE + "["+ messages[langIndex][146]+" " + formattedDateTime  + "]"+ RESET);

                        historyPenggunaan.add(new String[] {
                            "Admin", messages[langIndex][86], now.format(formatter)
                        });
                            System.out.println();
                            break;

                    case 5:
                        boolean konfirmasiUser = true;
                        do {
                            System.out.println(YELLOW+"=========================================="+RESET);
                            System.out.println(YELLOW+"|   "+messages[langIndex][96]+"   |"+RESET);
                            System.out.println(YELLOW+"=========================================="+RESET);
                            System.out.println(messages[langIndex][99]+" "+GREEN+messages[langIndex][49]+RESET);
                            System.out.println();
                            System.out.println("|1| "+messages[langIndex][52]);
                            System.out.println("|2| "+messages[langIndex][50]);
                            System.out.println("|3| "+messages[langIndex][55]);
                            System.out.println("|4| "+messages[langIndex][59]);
                            System.out.println("|5| "+messages[langIndex][61]);
                            System.out.println("|6| "+messages[langIndex][57]);
                            System.out.println(RED +"|0| "+messages[langIndex][45] + RESET);
                            System.out.println();
                            System.out.print(messages[langIndex][91]+" ");
                            int laporanUser = sc.nextInt();
                            
                            switch (laporanUser) {
                                case 1:
                                    //Laporan barang masuk
                                    TampilkanBarangMasuk(barangMasukList);

                                    historyPenggunaan.add(new String[] {
                                    "Admin", messages[langIndex][139], now.format(formatter)
                                    });
                                    break;
                                case 2:
                                    //Laporan barang keluar
                                    TampilkanBarangKeluar(barangKeluarList);

                                    historyPenggunaan.add(new String[] {
                                    "Admin", messages[langIndex][140], now.format(formatter)
                                    });
                                    break;
                                case 3:
                                    // Laporan barang rusak
                                    if (!barangRusakList.isEmpty()) {
                                        System.out.println(RED + "NOTE : "+messages[langIndex][21] + RESET);
                                        tampilkanLaporanBarangRusak(barangRusakList);
                                    } else {
                                        System.out.println(GREEN + messages[langIndex][10] + RESET);
                                        System.out.println();
                                    }

                                    historyPenggunaan.add(new String[] {
                                    "Admin", messages[langIndex][141], now.format(formatter)
                                    });
                                    break;
                                case 4:
                                    //Laporan Stock Barang sekarang
                                    TampilkanStockSekarang();
                                    System.out.println(BLUE + "["+messages[langIndex][147]+" " + formattedDateTime  + "]"+ RESET);

                                    historyPenggunaan.add(new String[] {
                                    "Admin", messages[langIndex][143], now.format(formatter)
                                    });

                                    break;
                                case 5:
                                    //Laporan Barang Paling Banyak Keluar
                                    LaporanBarangPalingBanyakKeluar();

                                    historyPenggunaan.add(new String[] {
                                    "Admin", messages[langIndex][148], now.format(formatter)
                                    });
                                    break;
                                case 6:
                                    // Laporan barang sedikit atau habis
                                    TampilkanBarangHabis();

                                    historyPenggunaan.add(new String[] {
                                    "Admin", messages[langIndex][145], now.format(formatter)
                                    });
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
                        System.out.println(GREEN+messages[langIndex][9]+" " +currentRole + ": " + currentUser+RESET);
                        System.out.println(messages[langIndex][100]);
                        // Kembali ke proses login
                        boolean switchSuccess = false;
                        while (true) {
                            System.out.print(messages[langIndex][84]+" ");
                            String switchUsername = sc.next();
                            System.out.print(messages[langIndex][77]+" ");
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
                                System.out.println(GREEN+messages[langIndex][7]+RESET);
                                isStaff = !isStaff;
                                isAdmin = !isAdmin;
                                break;
                            } else {
                                System.out.println(RED+messages[langIndex][62]+RESET);
                            }
                        }
                        break;
                    /////////////////////////////////////////////////////////////////////////////////////////////////        
                    case 0:
                    System.out.println(YELLOW+messages[langIndex][0] +RESET);
                    System.out.println();
                    System.out.println(
                                "Terimakasih telah menggunakan Sistem Inventaris!\n"+
                                "Program ini dibuat oleh:\n"+
                                "- Adnan Arju Maulana Pasha\n"+
                                "- Afifah Khoirunnisa\n"+
                                "- Mohammad Adri Favian\n"
                    );
                        exit = true;
                        break;

                    default:
                        System.out.println(RED+messages[langIndex][92]+RESET);
                        break;
                    }
                break;
            } while (isLoop);
        } while (!exit);    
        //selamat anda berada di akhir program :D
        break;// break loop memilih bahasa
    }
    }





////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////    

    //Fungsi Data Bahasa
    static String[][] messages = {
        // Bahasa Indonesia
        {
            "Anda telah Logout",//0
            "Apakah Anda ingin mengganti tanggal kadaluarsa? (ya/tidak): ",//1
            "Apakah Anda ingin me-reset tanggal kadaluarsa?(ya/tidak): ",//2
            "Barang berhasil dicatat sebagai barang rusak.",//3
            "Barang dengan kode atau nama ",//4
            "Barang ditemukan!",//5
            "Beralih Akun",//6
            "Berhasil Beralih Akun",//7
            "Berhasil login!",//8
            "Berhasil Logout dari ",//9
            "Belum ada data laporan barang rusak dari Staff",//10
            "Belum ada data history penggunaan sistem ini.",//11
            "CEK KEMBALI DATA BARANG NANTI ATAU BESOK",//12
            "DAFTAR IKAN LAUT",//13
            "DAFTAR HIDANGAN LAUT",//14
            "DAFTAR BUMBU MASAKAN",//15
            "DAFTAR BAHAN MINUMAN",//16
            "Data barang baru telah disimpan!",//17
            "Data berhasil diupdate!",//18
            "Data terakhir di update oleh Admin pada tanggal ",//19
            "Data terakhir di update oleh Staff pada tanggal ",//20
            "DEAR ADMIN, SEGERA KELUARKAN BARANG BERIKUT INI KARENA MENGALAMI KERUSAKAN.",//21
            "DEAR STAFF, BERIKUT INI ADALAH DATA BARANG YANG SUDAH ANDA CATAT SEBAGAI BARANG RUSAK.",//22
            "History penggunaan sistem",//23
            "HISTORY PENGGUNAAN SISTEM",//24
            "Input Data Barang Masuk",//25
            "Input Data Barang masuk",//26
            "Input Data Barang Masuk dan Keluar",//27
            "Input Data Barang ke Master",//28
            "Input data barang ke master",//29
            "Input Data Barang Keluar",//30
            "Input Data Barang Rusak",//31
            "Input data barang rusak",//32
            "Item tidak ditemukan.",//33
            "Jumlah",//34
            "Jumlah Barang",//35
            "Jumlah barang berhasil dikurangi.",//36
            "Jumlah barang berhasil ditambahkan.",//37
            "Jumlah barang yang Anda ingin dikeluarkan lebih besar dari stok. Data tetap.",//38
            "Jumlah Kerusakan",//39
            "Jumlah Saat ini",//40
            "Jumlah Sebelum",//41
            "Kadaluarsa",//42
            "kategori tidak valid",//43
            "Kegiatan",//44
            "Keluar",//45
            "keterangan Kerusakan",//46
            "Kode",//47
            "KODE",//48
            "Laporan Data Barang",//49
            "Laporan Data Barang Keluar",//50
            "LAPORAN DATA BARANG KELUAR",//51
            "Laporan Data Barang Masuk",//52
            "LAPORAN DATA BARANG MASUK",//53
            "LAPORAN DATA BARANG PALING BANYAK KELUAR",//54
            "Laporan Data Barang Rusak",//55
            "LAPORAN DATA BARANG RUSAK",//56
            "Laporan Data Barang Sedikit atau Habis",//57
            "LAPORAN DATA BARANG SEDIKIT ATAU HABIS",//58
            "Laporan Stock Barang Sekarang",//59
            "LAPORAN STOCK BARANG SEKARANG",//60
            "Laporan Stock Paling Banyak Keluar",//61
            "Login gagal. Silahkan coba lagi !",//62
            "Masukkan berapa jenis barang rusak yang ingin Anda input: ",//63
            "Masukkan berapa jenis barang yang ingin Anda masukkan: ",//64
            "Masukkan berapa jenis barang yang ingin Anda keluarkan: ",//65
            "Masukkan jumlah barang yang ingin ditambahkan: ",//66
            "Masukkan jumlah barang yang ingin dikeluarkan: ",//67
            "Masukkan jumlah baru: ",//68
            "Masukkan Jumlah Kerusakan: ",//69
            "Masukkan keterangan Kerusakan: ",//70
            "Masukkan kode baru: ",//71
            "Masukkan kode atau nama barang: ",//72
            "Masukkan kode atau nama barang yang rusak: ",//73
            "Masukkan kode atau nama barang yang ingin dicari: ",//74
            "Masukkan nama atau kode item yang akan diupdate: ",//75
            "Masukkan nama baru: ",//76
            "Masukkan Password: ",//77
            "Masukkan Pilihan (1/2/3/4): ",//78
            "Masukkan satuan baru (kg/buah/liter/galon/box/lainnya): ",//79
            "Masukkan Tanggal Kadaluarsa Baru: ",//80
            "Masukkan Tanggal Kadaluarsa (dd-MM-yyyy): ",//81
            "Masukkan Tanggal Kadaluarsa Baru (dd-MM-yyyy): ",//82
            "Masukkan Tanggal Kerusakan (dd-MM-yyyy): ",//83
            "Masukkan Username: ",//84
            "Membuka Pencarian Barang",//85
            "Mencari data barang",//86
            "Mohon input data anda dengan benar !",//87
            "Nama Barang",//88
            "Pencarian Data Barang",//89
            "Pilih jenis udpate:",//90
            "Pilih Menu: ",//91
            "Pilihan tidak valid !",//92
            "Program ini dibuat oleh:",//93
            "Satuan",//94
            "Satuan Barang",//95
            "SELAMAT DATANG DI SISTEM INVENTARIS",//96
            "SEGERA RE-STOCK BARANG-BARANG !",//97
            "Sekarang Anda Berada di Menu: ",//98
            "Sekarang Anda Berada di Submenu: ",//99
            "Silahkan login Kembali !",//100
            "Silahkan input barang terlebih dahulu !",//101
            "Tanggal Barang di-Input",//102
            "Tanggal Rusak",//103
            "Tanggal Kadaluarsa",//104
            "Tanggal kadaluarsa berhasil di ganti.",//105
            "Tanggal kadaluarsa berhasil di reset.",//106
            "Tanggal Kadaluarsa Tidak Di Update.",//107
            "Tanggal Penggunaan",//108
            "TERDAPAT STOK BARANG SEDIKIT DAN/ATAU HABIS",//109
            "Terimakasih ",//110
            "tidak",//111
            "tidak ada barang yang di-input.",//112
            "tidak ada barang yang dikeluarkan.",//113
            "tidak ada barang yang rusak.",//114
            "TIDAK ADA STOK BARANG SEDIKIT DAN/ATAU HABIS",//115
            "tidak ditemukan.",//116
            "TIDAK ADA LAPORAN BARANG RUSAK.",//117
            "tidak ditemukan.",//118
            "Update Data",//119
            "Update data barang",//120
            "Update Data Barang Ke Master",//121
            "ya",//122
            "Busuk",//123
            "Kemasan Rusak",//124
            "Lainnya",//125
            "Masukkan pilihan: ",//126
            "Tolong masukkan masalah kerusakan !",//127
            "Kadaluarsa dalam",//128
            "Sudah Kadaluarsa",//129
            "hari",//130
            "Kadaluarsa Hari Ini",//131
            "Perubahan Tanggal Kadaluarsa:",//132
            "Reset Tanggal",//133
            "Tambah Tanggal",//134
            "Biarkan Tanggal",//135
            "Tambahkan Tanggal Kadaluarsa (dd-MM-yyyy):",//136
            "DEAR ADMIN, BARANG-BARANG BERIKUT INI HARUS SEGERA DIKELUARKAN DARI DAFTAR.",//137
            "Jumlah barang berhasil dikurangi. JUMLAH STOK KOSONG, SEGERA RE-STOK!!",//138
            "Melihat Laporan Barang Masuk",//139
            "Melihat Laporan Barang Keluar",//140
            "Melihat Laporan Barang Rusak",//141
            "Data terakhir dlihat oleh Staff pada tanggal",//142
            "Melihat Laporan Stok Saat Ini",//143
            "Melihat Laporan Barang Paling Banyak Keluar",//144
            "Melihat Laporan Barang Habis",//145
            "Pencarian data terakhir dilakukan oleh Admin pada tanggal",//146
            "Data terakhir dilihat oleh Admin pada tanggal",//147
            "Melihat Laporan Barang Paling Banyak Keluar",//148
            "(kg/buah/liter/galon/box/bungkus/lainnya)",//149
            "MOHON MAAF, KODE SUDAH TERSEDIA, MOHON MASUKKAN ULANG KODE.",//150
            "Mohon maaf, kode yang Anda Masukkan tidak valid.",//151
            "FORMAT KODE",//152
            "MOHON MAAF, NAMA SUDAH TERSEDIA, MOHON MASUKKAN ULANG NAMA.",//153
        },
        // English
        {
            "You have logged out.",//0
            "Do you want to change the expiration date? (yes/no):",//1
            "Do you want to reset the expiration date? (yes/no):",//2
            "Item successfully recorded as damaged item.",//3
            "Item with code or name",//4
            "Item found!",//5
            "Switch Account",//6
            "Successfully switched account.",//7
            "Successfully logged in!",//8
            "Successfully logged out from",//9
            "No damaged items report data from Staff yet.",//10
            "No history data of system usage.",//11
            "CHECK AGAIN ON THE ITEM DATA LATER OR TOMORROW",//12
            "LIST OF SEAFOOD",//13
            "LIST OF SEAFOOD DISHES",//14
            "LIST OF KITCHEN SPICES",//15
            "LIST OF BEVERAGE INGREDIENTS",//16
            "New item data has been saved!",//17
            "Data successfully updated!",//18
            "Last data update by Admin on",//19
            "Last data update by Staff on",//20
            "DEAR ADMIN, PLEASE REMOVE THE FOLLOWING ITEMS AS THEY ARE DAMAGED.",//21
            "DEAR STAFF, HERE IS THE DATA OF ITEMS YOU HAVE MARKED AS DAMAGED.",//22
            "System usage history",//23
            "SYSTEM USAGE HISTORY",//24
            "Input Incoming Items Data",//25
            "Input Incoming items Data",//26
            "Input Incoming and Outgoing Items Data",//27
            "Input Items to Master",//28
            "Input items to master",//29
            "Input Outgoing Items Data",//30
            "Input Damaged Items Data",//31
            "Input damaged items data",//32
            "Item not found.",//33
            "Quantity",//34
            "Quantity of Items",//35
            "Quantity of items successfully reduced.",//36
            "Quantity of items successfully added.",//37
            "The quantity of items you want to take out is greater than the stock. Data remains.",//38
            "Quantity of Damage",//39
            "Current Quantity",//40
            "Quantity Before",//41
            "Expired",//42
            "invalid category",//43
            "Activity",//44
            "Exit",//45
            "Damage Description",//46
            "Code",//47
            "CODE",//48
            "Report of Items Data",//49
            "Report of Outgoing Items Data",//50
            "REPORT OF OUTGOING ITEMS DATA",//51
            "Report of Incoming Items Data",//52
            "REPORT OF INCOMING ITEMS DATA",//53
            "REPORT OF MOST OUTGOING ITEMS",//54
            "Report of Damaged Items Data",//55
            "REPORT OF DAMAGED ITEMS DATA",//56
            "Report of Few or Empty Items",//57
            "REPORT OF FEW OR EMPTY ITEMS",//58
            "Current Stock Report",//59
            "CURRENT STOCK REPORT",//60
            "Report of Most Outgoing Stock",//61
            "Login failed. Please try again !",//62
            "Enter how many types of damaged items you want to input:",//63
            "Enter how many types of items you want to input:",//64
            "Enter how many types of items you want to take out:",//65
            "Enter the quantity of items you want to add:",//66
            "Enter the quantity of items you want to take out:",//67
            "Enter the new quantity:",//68
            "Enter Quantity of Damage:",//69
            "Enter Damage Description:",//70
            "Enter new code:",//71
            "Enter code or name of the item:",//72
            "Enter code or name of the damaged item:",//73
            "Enter code or name of the item to search:",//74
            "Enter the name or code of the item to be updated:",//75
            "Enter new name:",//76
            "Enter Password:",//77
            "Enter Choice (1/2/3/4):",//78
            "Enter new unit (kg/piece/litre/galon/box/other):",//79
            "Enter New Expiration Date:",//80
            "Enter Expiration Date (dd-MM-yyyy):",//81
            "Enter New Expiration Date (dd-MM-yyyy):",//82
            "Enter Date of Damage (dd-MM-yyyy):",//83
            "Enter Username:",//84
            "Opening Item Search",//85
            "Searching for item data",//86
            "Please input your data correctly !",//87
            "Item Name",//88
            "Search for Items Data",//89
            "Choose the type of update:",//90
            "Choose Menu:",//91
            "Invalid choice !",//92
            "This program is created by:",//93
            "Unit",//94
            "Item Unit",//95
            "WELCOME TO THE INVENTORY SYSTEM",//96
            "RE-STOCK YOUR ITEMS !",//97
            "You are now in the Menu:",//98
            "You are now in the Submenu:",//99
            "Please login again !",//100
            "Please input items first !",//101
            "Date Item Input",//102
            "Date Damaged",//103
            "Expiration Date",//104
            "Expiration date successfully changed.",//105
            "Expiration date successfully reset.",//106
            "Expiration Date Not Updated",//107
            "Date of Use",//108
            "ITEMS LOW IN STOCK OR OUT OF STOCK",//109
            "Thank you",//110
            "no",//111
            "no items input.",//112
            "no items taken out.",//113
            "no damaged items.",//114
            "NO ITEMS LOW IN STOCK OR OUT OF STOCK",//115
            "not found.",//116
            "NO DAMAGED ITEMS REPORT.",//117
            "not found.",//118
            "Update Data",//119
            "Update items data",//120
            "Update Items Data to Master",//121
            "yes"//122
        }
    };


    // Fungsi untuk menampilkan judul kategori
    public static String JenisJudul(int namaJudul) {
        switch (namaJudul) {
            case 0:
                return messages[langIndex][13];
            case 1:
                return messages[langIndex][14];
            case 2:
                return messages[langIndex][15];
            case 3:
                return messages[langIndex][16];
            default:
                return "";
        }
    }

    //Fungsi Mencari Data Barang
    static void cariDataBarang(){
        System.out.print(ORANGE+messages[langIndex][74]+" "+RESET);
        String cariBarang = sc.nextLine();
        boolean ditemukan = false;

        LocalDate selisihTanggal;
        for (int kategori = 0; kategori < 4; kategori++) {
            String[] kodeArray = GudangRestoran[kategori * 5];
            String[] namaArray = GudangRestoran[kategori * 5 + 1];
            String[] JmlArray = GudangRestoran[kategori * 5 + 2];
            String[] SatuanArray = GudangRestoran[kategori * 5 + 3];
            String[] KadaluarsaArray = GudangRestoran[kategori * 5 + 4];

            String kategoriJudul = JenisJudul(kategori);
            for (int i = 0; i < kodeArray.length; i++) {
                if (kodeArray[i].equalsIgnoreCase(cariBarang) || namaArray[i].equalsIgnoreCase(cariBarang)) {
                    System.out.println(CYAN + "                                            =================================               " + RESET);
                    System.out.println(CYAN + "                                            |      " + kategoriJudul +"     |" + RESET);
                    System.out.println(CYAN + "                                            =================================               " + RESET);
                    System.out.println("=========================================================================================================================");
                    System.out.println("| "+messages[langIndex][48]+"   |       "+messages[langIndex][88]+"       | "+messages[langIndex][35]+" |     "+messages[langIndex][95]+"     |     "+messages[langIndex][94]+"    |     "+messages[langIndex][128]+"   |");
                    System.out.println("=========================================================================================================================");
                    
                    if (KadaluarsaArray[i] != " ") {
                        selisihTanggal = LocalDate.parse(KadaluarsaArray[i], format);
                        long selisihHari = hitungSelisihHari(selisihTanggal, hariIni);
                        if (selisihTanggal.isAfter(hariIni)) {
                            System.out.printf("| %-6s | %-23s |       %-8s| %-21s |     %-10s    |        %-10s      |\n", kodeArray[i], namaArray[i], JmlArray[i], SatuanArray[i], KadaluarsaArray[i], selisihHari + " "+messages[langIndex][130]);
                        } else if (selisihTanggal.isBefore(hariIni)) {
                            // Kadaluarsa kemarin, tampilkan pesan "sudah kadaluarsa" dengan warna merah
                            System.out.printf("| %-6s | %-23s |       %-8s| %-21s |     %-10s    |%-23s |\n",
                                    kodeArray[i], namaArray[i], JmlArray[i], SatuanArray[i], KadaluarsaArray[i], RED + messages[langIndex][129]+" " + selisihHari + " "+messages[langIndex][130] + RESET);
                        } else if (selisihTanggal == hariIni){
                            // Tanggal kadaluarsa hari ini
                            System.out.printf("| %-6s | %-23s |       %-8s| %-21s |     %-10s    |  %-23s  |\n", kodeArray[i], namaArray[i], JmlArray[i], SatuanArray[i], KadaluarsaArray[i], GREEN + " "+messages[langIndex][131] + RESET);
                        }
                } else {
                    System.out.printf("| %-6s | %-23s |       %-8s| %-21s |     %-10s    |  %-20s  |\n", kodeArray[i], namaArray[i], JmlArray[i], SatuanArray[i], KadaluarsaArray[i]," ");
                }

                    System.out.println("=========================================================================================================================");
                        System.out.println();
                        ditemukan = true;
                    }
                }
            }
        if (!ditemukan) {
            System.out.println(RED + "Note: "+messages[langIndex][4]+" " + cariBarang + " "+messages[langIndex][118]+".\n" + RESET);
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
                    System.out.println("\n===== "+messages[langIndex][119]+" =====");
                    System.out.println(messages[langIndex][47]+"\t\t: " + kodeArray[i]);
                    System.out.println(messages[langIndex][88]+"\t: " + namaArray[i]);
                    System.out.println(messages[langIndex][34]+"\t\t: " + JmlArray[i]);
                    System.out.println(messages[langIndex][94]+"\t\t: " + SatuanArray[i]);
                    System.out.println(messages[langIndex][42]+"\t: "+ KadaluarsaArray[i]);
                    sc.nextLine();
    
                    // Meminta pengguna untuk memasukkan informasi baru
                    switch (choice) {
                        case 1:
                            System.out.print(ORANGE+messages[langIndex][71]+" "+RESET);
                            kodeArray[i] = sc.nextLine();
                            break;
                        case 2:
                            System.out.print(ORANGE+messages[langIndex][76]+" "+RESET);
                            namaArray[i] = sc.nextLine();
                            break;
                        case 3:
                            System.out.print(ORANGE+messages[langIndex][79]+" "+RESET);
                            System.out.print(ORANGE+messages[langIndex][149]+" "+RESET);
                            SatuanArray[i] = sc.nextLine();
                            break;
                        case 4:
                            System.out.print(ORANGE+messages[langIndex][80]+" "+RESET);
                            KadaluarsaArray[i] = sc.nextLine();
                            break;
                        default:
                            System.out.println(messages[langIndex][92]);
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
            System.out.println(RED+messages[langIndex][43]+RESET);
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
            System.out.println("                                                    |       "+messages[langIndex][56]+"     |"      );
            System.out.println("                                                    ======================================="+RESET);
            System.out.println("============================================================================================================================================");
            System.out.println("| "+messages[langIndex][48]+"   |       "+messages[langIndex][88]+"       | "+messages[langIndex][34]+" |     "+messages[langIndex][94]+"     |     "+messages[langIndex][42]+"         | "+messages[langIndex][103]+" | "+messages[langIndex][46]+" | "+messages[langIndex][39]+" |");
            System.out.println("============================================================================================================================================");

                for (String[] detailBarang : barangRusakList) {
                    System.out.printf("| %-6s | %-23s |    %-4.9s| %-14s |     %-13s |  %-11s  | %-21s|        %-10s|\n",
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
        System.out.println(ORANGE +"                        =====================================================================" + RESET);
        System.out.println(ORANGE +"                        |                     "+messages[langIndex][60]+"                 |"  + RESET);
        System.out.println(ORANGE +"                        =====================================================================" + RESET);
        System.out.println();
        
        LocalDate selisihTanggal;
        for (int kategori = 0; kategori < 4; kategori++) {
            String[] kodeArray = GudangRestoran[kategori * 5];
            String[] namaArray = GudangRestoran[kategori * 5 + 1];
            String[] JmlArray = GudangRestoran[kategori * 5 + 2];
            String[] SatuanArray = GudangRestoran[kategori * 5 + 3];
            String[] KadaluarsaArray = GudangRestoran[kategori * 5 + 4];

            String kategoriJudul = JenisJudul(kategori);
            System.out.println(CYAN + "                                            =================================               " + RESET);
            System.out.println(CYAN + "                                            |      " + kategoriJudul +"     |" + RESET);
            System.out.println(CYAN + "                                            =================================               " + RESET);
            System.out.println("=========================================================================================================================");
            System.out.println("| "+messages[langIndex][48]+"   |       "+messages[langIndex][88]+"       | "+messages[langIndex][35]+" |     "+messages[langIndex][95]+"     |     "+messages[langIndex][42]+"    |     "+messages[langIndex][128]+"   |");
            System.out.println("=========================================================================================================================");
            for (int i =0; i<kodeArray.length; i++) {
                if (KadaluarsaArray[i] != " ") {
                    selisihTanggal = LocalDate.parse(KadaluarsaArray[i], format);
                    long selisihHari = hitungSelisihHari(selisihTanggal, hariIni);
                    if (selisihTanggal.isAfter(hariIni)) {
                        System.out.printf("| %-6s | %-23s |       %-8s| %-21s |     %-10s    |        %-10s      |\n", kodeArray[i], namaArray[i], JmlArray[i], SatuanArray[i], KadaluarsaArray[i], selisihHari + " "+messages[langIndex][130]);
                    } else if (selisihTanggal.isBefore(hariIni)) {
                        // Kadaluarsa kemarin, tampilkan pesan "sudah kadaluarsa" dengan warna merah
                        System.out.printf("| %-6s | %-23s |       %-8s| %-21s |     %-10s    |%-23s |\n",
                                kodeArray[i], namaArray[i], JmlArray[i], SatuanArray[i], KadaluarsaArray[i], RED + messages[langIndex][129]+" " + selisihHari + " "+messages[langIndex][130] + RESET);
                    } else if (selisihTanggal == hariIni){
                        // Tanggal kadaluarsa hari ini
                        System.out.printf("| %-6s | %-23s |       %-8s| %-21s |     %-10s    |  %-23s  |\n", kodeArray[i], namaArray[i], JmlArray[i], SatuanArray[i], KadaluarsaArray[i], GREEN + " "+messages[langIndex][131] + RESET);
                    }
                } else {
                    System.out.printf("| %-6s | %-23s |       %-8s| %-21s |     %-10s    |  %-20s  |\n", kodeArray[i], namaArray[i], JmlArray[i], SatuanArray[i], KadaluarsaArray[i]," ");
                }
                

            }
                System.out.println("=========================================================================================================================");
                System.out.println();
        }
    }

    //method untuk menghitung selisih hari
    public static long hitungSelisihHari (LocalDate hariIni, LocalDate tanggalAkhir) {
        return Math.abs(tanggalAkhir.toEpochDay() - hariIni.toEpochDay());
    }
    
    //Fungsi untuk menampilkan laporan barang yang sedikit atau habis
    public static void TampilkanBarangHabis() {
    boolean konfirmasiBarangSedikit = false;
    
    if (!konfirmasiBarangSedikit) {
        System.out.println(RED + "NOTE :  "+messages[langIndex][109] + RESET);
        System.out.println(RED + messages[langIndex][97] + RESET);
        System.out.println();
        System.out.println(BLUE + "                 ==========================================            " + RESET);
        System.out.println(BLUE + "                 | "+messages[langIndex][58]+" |            " + RESET);
        System.out.println(BLUE + "                 ==========================================            " + RESET);
        System.out.println();
        System.out.println("=========================================================================================================================");
        System.out.println("| "+messages[langIndex][48]+"   |       "+messages[langIndex][88]+"       | "+messages[langIndex][35]+" |     "+messages[langIndex][95]+"     |     "+messages[langIndex][42]+"    |     "+messages[langIndex][128]+"   |");
        System.out.println("=========================================================================================================================");
                                    
        for (int kategori = 0; kategori < 4; kategori++) {
            String[] kodeArray = GudangRestoran[kategori * 5];
            String[] namaArray = GudangRestoran[kategori * 5 + 1];
            String[] JmlArray = GudangRestoran[kategori * 5 + 2];
            String[] SatuanArray = GudangRestoran[kategori * 5 + 3];
            String[] KadaluarsaArray = GudangRestoran[kategori * 5 + 4 ];

            LocalDate selisihTanggal;
            for (int i =0; i<kodeArray.length; i++) {
                int jumlahStok = Integer.parseInt(JmlArray[i]);
                    if (jumlahStok < 6) {
                        if (KadaluarsaArray[i] != " ") {
                            selisihTanggal = LocalDate.parse(KadaluarsaArray[i], format);
                            long selisihHari = hitungSelisihHari(selisihTanggal, hariIni);
                                if (selisihTanggal.isAfter(hariIni)) {
                                    System.out.printf("| %-6s | %-23s |       %-8s| %-21s |     %-10s    |        %-10s      |\n", kodeArray[i], namaArray[i], JmlArray[i], SatuanArray[i], KadaluarsaArray[i], selisihHari + " "+messages[langIndex][130] );
                                } else if (selisihTanggal.isBefore(hariIni)) {
                                    // Kadaluarsa kemarin, tampilkan pesan "sudah kadaluarsa" dengan warna merah
                                    System.out.printf("| %-6s | %-23s |       %-8s| %-21s |     %-10s    |%-23s |\n",
                                            kodeArray[i], namaArray[i], JmlArray[i], SatuanArray[i], KadaluarsaArray[i], RED + messages[langIndex][129]+" " + selisihHari + " "+messages[langIndex][130] + RESET);
                                } else if (selisihTanggal == hariIni){
                                    // Tanggal kadaluarsa hari ini
                                    System.out.printf("| %-6s | %-23s |       %-8s| %-21s |     %-10s    |  %-23s  |\n", kodeArray[i], namaArray[i], JmlArray[i], SatuanArray[i], KadaluarsaArray[i], GREEN + " "+messages[langIndex][131] + RESET);
                                }
                        } else {
                            System.out.printf("| %-6s | %-23s |       %-8s| %-21s |     %-10s    |  %-20s  |\n", kodeArray[i], namaArray[i], JmlArray[i], SatuanArray[i], KadaluarsaArray[i]," ");
                        }
                            konfirmasiBarangSedikit = true;
                    } 
                }
            }
            System.out.println("=========================================================================================================================");
            System.out.println();
        } else {
            System.out.println(GREEN + "               "+messages[langIndex][115]+"            " + RESET);
            System.out.println(GREEN + "                 "+messages[langIndex][12]+"           " + RESET);
        }
            
            
    }

    //Fungsi untuk menampilkan laporan barang masuk
    public static void TampilkanBarangMasuk(List<String[]> barangMasukList) {
        if (!barangMasukList.isEmpty()) {
            System.out.println("                           =======================================");
            System.out.println("                                    "+messages[langIndex][53]      );
            System.out.println("                           =======================================");
            System.out.println("========================================================================================================================");
            System.out.println("| "+messages[langIndex][48]+"   |       "+messages[langIndex][88]+"       | "+messages[langIndex][41]+" | "+messages[langIndex][40]+" |  "+messages[langIndex][94]+"  | "+messages[langIndex][42]+" |     "+messages[langIndex][102]+"      |");
            System.out.println("========================================================================================================================");

            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            for (String[] detailBarang : barangMasukList) {
                System.out.printf("| %-6s | %-23s | %-14s | %-15.5s | %-8s | %-10s | %-23s |\n",
                detailBarang[0], detailBarang[1], detailBarang[2], detailBarang[3], detailBarang[4], detailBarang[5], LocalDate.now().format(dateFormatter).toString());
            }
                System.out.println("========================================================================================================================");
                    
        } else {
            System.out.println(RED +  messages[langIndex][112] + RESET);
            System.out.println(RED + "Note : "+messages[langIndex][101] + RESET);
            }
    }

    //Fungsi untuk menampilkan laporan barang keluar
    public static void TampilkanBarangKeluar(List<String[]> barangKeluarList) {
        if (!barangKeluarList.isEmpty()) {
            
            System.out.println("                           =======================================");
            System.out.println("                                   "+messages[langIndex][54]    );
            System.out.println("                           =======================================");
            System.out.println();
            System.out.println("========================================================================================================================");
            System.out.println("| "+messages[langIndex][48]+"   |       "+messages[langIndex][88]+"       | "+messages[langIndex][41]+" | "+messages[langIndex][40]+" |  "+messages[langIndex][94]+"  | "+messages[langIndex][42]+" |     "+messages[langIndex][102]+"      |");
            System.out.println("========================================================================================================================");

            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            for (String[] detailBarang : barangKeluarList) {
                System.out.printf("| %-6s | %-23s | %-14s | %-15.5s | %-8s | %-10s | %-23s |\n",
                detailBarang[0], detailBarang[1], detailBarang[2], detailBarang[3], detailBarang[4], detailBarang[5], LocalDate.now().format(dateFormatter).toString());
            }
                System.out.println("========================================================================================================================");
                    
        } else {
                System.out.println(RED + messages[langIndex][112] + RESET);
                System.out.println(RED + "Note : "+messages[langIndex][101] + RESET);
            }
    }

    //Fungsi Laporan data yang paling banyak keluar
    public static void LaporanBarangPalingBanyakKeluar() {
        if (!barangKeluarList.isEmpty()) {
            System.out.println("                           ============================================");
            System.out.println("                           | "+messages[langIndex][54]+" |"    );
            System.out.println("                           ============================================");
            System.out.println("========================================================================================================================");
            System.out.println("| "+messages[langIndex][48]+"   |       "+messages[langIndex][88]+"       | "+messages[langIndex][41]+" | "+messages[langIndex][40]+" |  "+messages[langIndex][94]+"  | "+messages[langIndex][42]+" |     "+messages[langIndex][102]+"      |");
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
            System.out.println(RED + messages[langIndex][113]+"\n" + RESET);
        }
        
    }

    //Fungsi untuk menampilkan history Penggunaan 
    public static void historyPenggunaanSistem (List<String[]> historyPenggunaan) {
        if (!historyPenggunaan.isEmpty()) {
            System.out.println(CYAN +"                           =======================================" + RESET);
            System.out.println(CYAN +"                           |       "+messages[langIndex][24]+"     |" + RESET);
            System.out.println(CYAN +"                           =======================================" + RESET);
            System.out.println("===============================================================================================");
            System.out.println("| Role  |                       "+messages[langIndex][44]+"                               | "+messages[langIndex][108]+"   |");
            System.out.println("===============================================================================================");
            
            for (String[] history : historyPenggunaan) {
                System.out.printf("| %-5s | %-60s | %-20s |\n",
                history[0], history[1], history[2]
                );
            }
            System.out.println("===============================================================================================");

        } else {
            System.out.println(GREEN + "Note : "+messages[langIndex][11]+"\n" + RESET);
        }
    }

    // Fungsi untuk mendapatkan waktu aktual
    static String getFormattedDateTime() {
        LocalDateTime now = LocalDateTime.now();
        return now.format(formatter);
    }

    //Fungsi untuk menginput data barang ke-master
    static void inputDataMaster() {
        System.out.print(messages[langIndex][64]+" ");
        int barangbaru = sc.nextInt();

        for (int j =0; j <barangbaru; j++) {
            boolean ditemukan = true;

            while (ditemukan) {
                System.out.print(ORANGE+messages[langIndex][71]+" "+RESET);
                String newKode = sc.next();
                ditemukan = false;

                LocalDate selisihTanggal;
                for (int kategori = 0; kategori < 4; kategori++) {
                    String[] kodeArray = GudangRestoran[kategori * 5];
                    String[] namaArray = GudangRestoran[kategori * 5 + 1];
                    String[] JmlArray = GudangRestoran[kategori * 5 + 2];
                    String[] SatuanArray = GudangRestoran[kategori * 5 + 3];
                    String[] KadaluarsaArray = GudangRestoran[kategori * 5 + 4];

                    for (int i = 0; i < kodeArray.length; i++) {
                        if (kodeArray[i].equalsIgnoreCase(newKode)) {

                            System.out.println("=========================================================================================================================");
                            System.out.println("| "+messages[langIndex][48]+"   |       "+messages[langIndex][88]+"       | "+messages[langIndex][35]+" |     "+messages[langIndex][95]+"     |     "+messages[langIndex][94]+"    |     "+messages[langIndex][128]+"   |");
                            System.out.println("=========================================================================================================================");
                                            
                            if (KadaluarsaArray[i] != " ") {
                                selisihTanggal = LocalDate.parse(KadaluarsaArray[i], format);
                                long selisihHari = hitungSelisihHari(selisihTanggal, hariIni);

                                    if (selisihTanggal.isAfter(hariIni)) {
                                        System.out.printf("| %-6s | %-23s |       %-8s| %-21s |     %-10s    |        %-10s      |\n", kodeArray[i], namaArray[i], JmlArray[i], SatuanArray[i], KadaluarsaArray[i], selisihHari + " "+messages[langIndex][130]);
                                    } else if (selisihTanggal.isBefore(hariIni)) {
                                        // Kadaluarsa kemarin, tampilkan pesan "sudah kadaluarsa" dengan warna merah
                                        System.out.printf("| %-6s | %-23s |       %-8s| %-21s |     %-10s    |%-23s |\n",
                                                kodeArray[i], namaArray[i], JmlArray[i], SatuanArray[i], KadaluarsaArray[i], RED + messages[langIndex][129]+" " + selisihHari + " "+messages[langIndex][130] + RESET);
                                    } else if (selisihTanggal == hariIni){
                                        // Tanggal kadaluarsa hari ini
                                        System.out.printf("| %-6s | %-23s |       %-8s| %-21s |     %-10s    |  %-23s  |\n", kodeArray[i], namaArray[i], JmlArray[i], SatuanArray[i], KadaluarsaArray[i], GREEN + " "+messages[langIndex][131] + RESET);
                                    }

                            } else {
                                System.out.printf("| %-6s | %-23s |       %-8s| %-21s |     %-10s    |  %-20s  |\n", kodeArray[i], namaArray[i], JmlArray[i], SatuanArray[i], KadaluarsaArray[i]," ");
                            }
                                System.out.println("=========================================================================================================================");
                                System.out.println();
                                System.out.println(RED + "NOTE: "+messages[langIndex][150] + RESET);
                                ditemukan = true;
                        }

                    }
                }

                if (!ditemukan) {
                    if (!newKode.matches("^[IHBM]-\\d{3}$")) {
                        System.out.println(messages[langIndex][151]);
                        System.out.println();
                        System.out.println("============== "+messages[langIndex][152]+" ==============");
                        System.out.println("Ikan Laut(marine fish)      = I-(3 Angka)");
                        System.out.println("Hewan Laut(marine animals)     = H-(3 Angka)");
                        System.out.println("Bumbu Dapur(herbs)    = B-(3 Angka)");
                        System.out.println("Minuman(beverages)        = M-(3 Angka)");
                        System.out.println();
                        ditemukan = true;
                    }
                }
                
                if (!ditemukan) {
                    boolean ditemukan2 = true;

                    while (ditemukan2) {
                        System.out.print(ORANGE+messages[langIndex][76]+" "+RESET);
                        String newNama = sc.next();
                        ditemukan2 = false;

                        for (int kategori = 0; kategori < 4; kategori++) {
                            String[] kodeArray = GudangRestoran[kategori * 5];
                            String[] namaArray = GudangRestoran[kategori * 5 + 1];
                            String[] JmlArray = GudangRestoran[kategori * 5 + 2];
                            String[] SatuanArray = GudangRestoran[kategori * 5 + 3];
                            String[] KadaluarsaArray = GudangRestoran[kategori * 5 + 4];

                            for (int i = 0; i < kodeArray.length; i++) {
                                if (namaArray[i].equalsIgnoreCase(newNama)) {
                                    System.out.println("=========================================================================================================================");
                                    System.out.println("| "+messages[langIndex][48]+"   |       "+messages[langIndex][88]+"       | "+messages[langIndex][35]+" |     "+messages[langIndex][95]+"     |     "+messages[langIndex][94]+"    |     "+messages[langIndex][128]+"   |");
                                    System.out.println("=========================================================================================================================");
                                                            
                                    if (KadaluarsaArray[i] != " ") {
                                    selisihTanggal = LocalDate.parse(KadaluarsaArray[i], format);
                                    long selisihHari = hitungSelisihHari(selisihTanggal, hariIni);

                                        if (selisihTanggal.isAfter(hariIni)) {
                                            System.out.printf("| %-6s | %-23s |       %-8s| %-21s |     %-10s    |        %-10s      |\n", kodeArray[i], namaArray[i], JmlArray[i], SatuanArray[i], KadaluarsaArray[i], selisihHari + " "+messages[langIndex][130]);
                                        } else if (selisihTanggal.isBefore(hariIni)) {
                                            // Kadaluarsa kemarin, tampilkan pesan "sudah kadaluarsa" dengan warna merah
                                            System.out.printf("| %-6s | %-23s |       %-8s| %-21s |     %-10s    |%-23s |\n",
                                                    kodeArray[i], namaArray[i], JmlArray[i], SatuanArray[i], KadaluarsaArray[i], RED + messages[langIndex][129]+" " + selisihHari + " "+messages[langIndex][130] + RESET);
                                        } else if (selisihTanggal == hariIni){
                                            // Tanggal kadaluarsa hari ini
                                            System.out.printf("| %-6s | %-23s |       %-8s| %-21s |     %-10s    |  %-23s  |\n", kodeArray[i], namaArray[i], JmlArray[i], SatuanArray[i], KadaluarsaArray[i], GREEN + " "+messages[langIndex][131] + RESET);
                                        }
                                } else {
                                    System.out.printf("| %-6s | %-23s |       %-8s| %-21s |     %-10s    |  %-20s  |\n", kodeArray[i], namaArray[i], JmlArray[i], SatuanArray[i], KadaluarsaArray[i]," ");
                                }
                                            System.out.println("=========================================================================================================================");
                                            System.out.println();
                                            ditemukan2 = true;
                                    }
                                }
                            }

                            if (ditemukan2) {
                                System.out.println(RED + "NOTE: "+messages[langIndex][153] + RESET);
                            }


                            if (!ditemukan2) {
                                sc.nextLine();
                                System.out.print(ORANGE+messages[langIndex][68]+" "+RESET);
                                String newJumlah = sc.next();
                                System.out.print(ORANGE+messages[langIndex][79]+" "+RESET);
                                String newSatuan = sc.next();
                                System.out.print(ORANGE+messages[langIndex][81]+" "+RESET);
                                String tanggalKadaluarsa = sc.next();

                                DateTimeFormatter formatTanggal = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                                LocalDate tanggal = LocalDate.from(formatTanggal.parse(tanggalKadaluarsa));

                                System.out.println();
                                System.out.println(GREEN + messages[langIndex][17] + RESET);
                                System.out.println(GREEN + "================================" + RESET);
                                System.out.println();

                                inputData(newKode, newNama, newJumlah, newSatuan, tanggal);
                            }
                        }
                    }
        }

    }
}

static void clear() {
    System.out.print("\033[H\033[2J");
}

}
