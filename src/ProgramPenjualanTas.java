import java.sql.*; // 8. Menggunakan JDBC untuk operasi database
import java.util.HashMap; // 7. Menggunakan Collection Framework
import java.util.Map;
import java.util.Scanner;

// Program untuk penjualan tas
public class ProgramPenjualanTas {
    // 8. Properti untuk koneksi database JDBC
    static Connection conn;
    static String JDBC_URL = "jdbc:mysql://localhost:3306/toko_tas";
    static String USER = "root";
    static String PASSWORD = "";
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        System.out.println("Selamat datang di Program Penjualan Tas");
        conn = DriverManager.getConnection(JDBC_URL, USER, PASSWORD); // 8. Membuka koneksi database
        menu(); // Memanggil fungsi untuk menampilkan menu utama
        conn.close(); // 8. Menutup koneksi setelah program selesai
        System.out.println("Program selesai.");
    }

    // 1. Fungsi untuk menampilkan menu utama
    private static void menu() throws SQLException {
        boolean kembaliKeMenu = true; // 4. Percabangan untuk kontrol alur program
        String pilihan;

        while (kembaliKeMenu) { // 4. Perulangan untuk terus menampilkan menu
            System.out.println("\n=============== M E N U ================");
            System.out.println("    1. Lihat data penjualan");
            System.out.println("    2. Perbarui data penjualan");
            System.out.println("    3. Tambahkan penjualan baru");
            System.out.println("    4. Hapus penjualan");
            System.out.println("    5. Cetak struk pembelian");
            System.out.println("    6. Cetak struk pembelian tas premium");
            System.out.println("    7. Keluar program");
            System.out.print("    Pilihan Anda (1/2/3/4/5/6/7): ");

            pilihan = input.nextLine(); // Input pilihan dari pengguna
            PenjualanTas penjualanTas = new PenjualanTas(); // Membuat objek PenjualanTas

            switch (pilihan) { // 4. Percabangan untuk menangani pilihan pengguna
                case "1":
                    penjualanTas.view(); // Melihat data penjualan
                    break;
                case "2":
                    loginAdmin(); // Fungsi login untuk keamanan
                    penjualanTas.update(); // Memperbarui data
                    break;
                case "3":
                    penjualanTas.save(); // Menambahkan penjualan baru
                    break;
                case "4":
                    penjualanTas.delete(); // Menghapus data penjualan
                    break;
                case "5":
                    cetakStruk(); // Cetak struk reguler
                    break;
                case "6":
                    cetakStrukPremium(); // Cetak struk untuk tas premium
                    break;
                case "7":
                    kembaliKeMenu = false; // Keluar dari program
                    break;
                default:
                    System.out.println("Input harus berupa angka 1/2/3/4/5/6/7!");
                    break;
            }

            // Konfirmasi untuk kembali ke menu utama
            if (kembaliKeMenu) {
                System.out.print("Kembali ke menu utama [y/n]? ");
                String kembali = input.nextLine();
                if (!kembali.equalsIgnoreCase("y")) {
                    kembaliKeMenu = false;
                }
            }
        }
    }

    // 6. Fungsi untuk login admin menggunakan username dan password
    private static void loginAdmin() throws SQLException {
        String inputUsername, inputPassword;
        String sql = "SELECT * FROM login"; // Query untuk mengambil data login dari database
        Map<String, String> login = new HashMap<>(); // 7. Menggunakan HashMap untuk menyimpan data login

        // 8. Menggunakan JDBC untuk mengambil data dari tabel login
        Statement statement = conn.createStatement();
        ResultSet result = statement.executeQuery(sql);
        while (result.next()) { // 4. Perulangan untuk membaca setiap baris hasil query
            String user = result.getString("user");
            String password = result.getString("password");
            login.put(user, password); // Menyimpan username dan password di HashMap
        }
        System.out.println("=== Silakan login terlebih dahulu ===");

        while (true) { // 4. Perulangan untuk meminta input login sampai berhasil
            System.out.print("Username: ");
            inputUsername = input.nextLine();
            System.out.print("Password: ");
            inputPassword = input.nextLine();

            // 4. Percabangan untuk validasi login
            if (login.containsKey(inputUsername) && login.get(inputUsername).equals(inputPassword)) {
                System.out.println("Berhasil login.");
                break; // Keluar dari loop setelah login berhasil
            } else {
                System.out.println("Username atau password salah. Coba lagi.");
            }
        }
    }

    // 1. Fungsi untuk mencetak struk pembelian
    private static void cetakStruk() {
        Struk struk = new Struk(); // Membuat objek Struk
        boolean menambahkanTransaksi = true;

        while (menambahkanTransaksi) { // 4. Perulangan untuk menambahkan transaksi
            System.out.print("Masukkan merek tas: ");
            String merek = input.nextLine();
            System.out.print("Masukkan harga tas: ");
            double harga = input.nextDouble(); // Input harga tas
            System.out.print("Masukkan ukuran tas (cm): ");
            int ukuran = input.nextInt(); // Input ukuran tas
            System.out.print("Jumlah yang dibeli: ");
            int jumlahBeli = input.nextInt(); // Input jumlah pembelian
            System.out.print("Apakah pembeli adalah member (y/n)? ");
            boolean member = input.next().equalsIgnoreCase("y"); // Input status member
            input.nextLine(); // Consume newline

            // Membuat objek Tas dan Transaksi
            Tas tas = new Tas(merek, harga, ukuran);
            Transaksi transaksi = new Transaksi(tas, jumlahBeli, member);
            struk.tambahTransaksi(transaksi); // Menambahkan transaksi ke struk

            System.out.print("Apakah ingin menambahkan transaksi lagi (y/n)? ");
            String tambah = input.nextLine();
            if (!tambah.equalsIgnoreCase("y")) {
                menambahkanTransaksi = false; // Keluar dari loop jika tidak menambahkan lagi
            }
        }

        struk.cetakStruk(); // Cetak semua transaksi yang telah ditambahkan
    }

    // 1. Fungsi untuk mencetak struk tas premium
    private static void cetakStrukPremium() {
        System.out.print("Masukkan merek tas premium: ");
        String merek = input.nextLine();
        System.out.print("Masukkan harga tas premium: ");
        double harga = input.nextDouble();
        System.out.print("Masukkan ukuran tas premium (cm): ");
        int ukuran = input.nextInt();
        System.out.print("Masukkan diskon premium (persen): ");
        double diskon = input.nextDouble(); // 4. Perhitungan diskon pada tas premium
        input.nextLine(); // Consume newline

        // Membuat objek TasPremium
        TasPremium tasPremium = new TasPremium(merek, harga, ukuran, diskon);
        tasPremium.tampilkanInfo(); // Menampilkan informasi tas premium
    }
}
