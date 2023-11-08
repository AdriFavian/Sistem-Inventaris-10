import java.util.Scanner;

public class PencarianDaftarBarang {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[][] GudangRestoran    = {
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

            for (int  i = 0; i < kodeArray.length; i++) {
                System.out.println(kodeArray[i] + " - " + namaArray[i] + " = " + JmlArray[i]);
            }

            System.out.println();
            indexNow += 3;
        }

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
        sc.close();

        }


    }



