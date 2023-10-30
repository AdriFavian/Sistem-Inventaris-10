import java.util.Scanner;

public class SistemInventaris {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[][] userArr = new String[10][3]; // Array dua dimensi: username, password, role
        int userCount = 0;
        boolean IsLogin = false;
        boolean IsAdmin = false;
        String currentUser = "";

        while (true) {
            System.out.println("===================================");
            System.out.println("SELAMAT DATANG DI SISTEM INVENTARIS");
            System.out.println("===================================");
            System.out.println("1. Buat akun");
            System.out.println("2. Login");
            System.out.println("3. Tampilkan pengguna");
            System.out.println("4. Keluar");
            System.out.print("Pilih Menu: ");
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

                userArr[userCount][0] = username;
                userArr[userCount][1] = password;
                userArr[userCount][2] = role;
                userCount++;

                System.out.println("\n");
                System.out.println("       AKUN BERHASIL DIBUAT !      ");
                System.out.println("\n");

            } else if (choice == 2) {
                // Menu Login
                System.out.println("===================================");
                System.out.println("            MASUK AKUN             ");
                System.out.println("===================================");
                System.out.print("Username: ");
                String username = sc.next();
                System.out.print("Password: ");
                String password = sc.next();

                for (int i = 0; i < userCount; i++) {
                    if (userArr[i][0].equals(username) && userArr[i][1].equals(password)) {
                        IsLogin = true;
                        currentUser = username;

                        if (userArr[i][2].equals("admin")) {
                            IsAdmin = true;
                        }

                        break;
                    }
                }

                if (IsLogin) {
                    System.out.println("\n");
                    System.out.println("       BERHASIL LOGIN !      ");
                    System.out.println("\n");

                    if (IsAdmin) {
                        // Di sini Anda dapat menambahkan logika untuk menu admin
                        System.out.println("Selamat datang, admin " + currentUser);
                    } else {
                        // Di sini Anda dapat menambahkan logika untuk menu staf
                        System.out.println("Selamat datang, staf " + currentUser);
                    }
                } else {
                    System.out.println("\n");
                    System.out.println("        GAGAL LOGIN !        ");
                    System.out.println("\n");
                }
            } else if (choice == 3) {
                // Menu Tampilkan akun
                System.out.println("===================================");
                System.out.println("            DAFTAR AKUN            ");
                System.out.println("===================================");
                for (int i = 0; i < userCount; i++) {
                    System.out.println("Username: " + userArr[i][0] + " Role: " + userArr[i][2]);
                }
                System.out.println("\n");
            } else if (choice == 4) {
                break;
            } else {
                System.out.println("Pilihan tidak valid!");
            }
        }
    }
}
