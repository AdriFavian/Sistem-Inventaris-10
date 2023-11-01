import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SistemInventaris {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        //Array user dan gudang penyimpanan
        String[][] userArr = new String[10][3]; // Array dua dimensi: username, password, role
        String[][] gudangArr = new String[100][3]; // Array dua dimensi: nama, jumlah, tanggal
        boolean IsLogin = false;
        boolean IsAdmin = false;
        boolean IsStaff = false;
        int userCount = 0;
        int gudangCount = 0;
        String currentUser = "";

        //Warna Teks
        String RESET = "\u001B[0m";
        String RED = "\u001B[31m";
        String GREEN = "\u001B[32m";
        String YELLOW = "\u001B[33m";
        
        while (!IsLogin) {
            System.out.println("===================================\n");
            System.out.println(YELLOW+"SELAMAT DATANG DI SISTEM INVENTARIS"+RESET);
            System.out.println("\n===================================");
            System.out.println("1. Buat akun");
            System.out.println("2. Login");
            System.out.println("3. Tampilkan pengguna");
            System.out.println("4. Keluar");
            System.out.print("\nPilih Menu: ");
            int choice = sc.nextInt();
            
            if (choice == 1) {
                // Menu Buat Akun
                System.out.println("===================================");
                System.out.println("             BUAT AKUN              ");
                System.out.println("===================================");
                System.out.print("Username: ");
                String username = sc.next();
                System.out.print("Password: ");
                String password = sc.next();
                System.out.print("Role (staff/admin): ");
                String role = sc.next();
                
                for (int l=0; l< userArr.length; l++) {
                    if (userArr[l][0] == null) {
                        userArr[l][0] = username;
                        userArr[l][1] = password;
                        userArr[l][2] = role;
                        userCount++;
                        break; 
                    }
                }

                System.out.println("===================================");
                System.out.println(GREEN+"       AKUN BERHASIL DIBUAT !      "+RESET);
                System.out.println("===================================");

            } else if (choice == 2) {
                // Menu Login
                System.out.println("===================================");
                System.out.println("            MASUK AKUN             ");
                System.out.println("===================================");
                System.out.print("Username: ");
                String username = sc.next();
                System.out.print("Password: ");
                String password = sc.next();
                
                for (int i = 0; i < userArr.length; i++) {
                    if (userArr[i][0].equals(username) && userArr[i][1].equals(password)) {
                        IsLogin = true;
                        currentUser = username;
                        
                        if (userArr[i][2].equals("admin")) {
                            IsAdmin = true;
                        } else if (userArr[i][2].equals("staff")) {
                            IsStaff = true;
                        }
                        
                        break;
                    }
                }
                
                while (IsAdmin) {
                    System.out.println("\n===================================");
                    System.out.println("          Selamat Datang           ");
                    System.out.println("             Admin " + currentUser    );
                    System.out.println("===================================");

                    System.out.println("1. Input Barang Masuk");
                    System.out.println("2. Input Barang Keluar");
                    System.out.print("\nPilih Menu: ");
                    int choicee = sc.nextInt();

                    switch (choicee) {
                        case 1:
                            System.out.println("===================================");
                            System.out.println("         Input Barang Masuk        ");
                            System.out.println("===================================");

                            System.out.print("Berapa jenis barang yang ingin diinput: ");
                            int jumlahInput = sc.nextInt();

                            for (int k=0; k<jumlahInput; k++) {
                                System.out.println("Data barang ke - "+(k+1));
                                System.out.print("Nama barang: ");
                                String namaBarang = sc.next();
                                System.out.print("Jumlah barang: ");
                                int jumlahBarang = sc.nextInt();
                                System.out.print("Masukkan tanggal barang masuk (dd/MM/yyyy): ");
                                String input = sc.next();
                                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                                LocalDate tanggalMasuk = LocalDate.parse(input, formatter);
                                
                                String informasiBarangMasuk = "Nama: " + namaBarang + " Jumlah: " + jumlahBarang + " Tanggal Masuk: " + tanggalMasuk;
                                for (int i = 0; i < gudangArr.length; i++) {
                                    if (gudangArr[i][0] == null) {
                                        gudangArr[i][0] = informasiBarangMasuk;
                                        gudangCount++;
                                        break; 
                                    }
                                }
                                System.out.println("\nData barang masuk telah disimpan.\n");
                            }
                        break;
                    } 
                    break;
                }

                while (IsStaff) {
                    System.out.println("\n===================================");
                    System.out.println("          Selamat Datang           ");
                    System.out.println("             Staff " + currentUser    );
                    System.out.println("===================================");
                    break;
                }

                
            } else if (choice == 3) {
                // Menu Tampilkan akun
                System.out.println("===================================");
                System.out.println("            DAFTAR AKUN            ");
                System.out.println("===================================");
                for (int i = 0; i < userCount; i++) {
                    System.out.println("Username: " + userArr[i][0] + 
                                        " Password: " + userArr[i][1] +
                                        " Role: " + userArr[i][2] + "\n");
                }

            } else if (choice == 4) {
                break;
            } else {
                System.out.println("===================================");
                System.out.println("      Pilihan tidak valid!         ");
                System.out.println("===================================");
            }
        } sc.close();
    }
}
