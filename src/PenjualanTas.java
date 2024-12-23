import java.sql.*;

// 8. Menggunakan JDBC dan terdapat fungsi CRUD
public class PenjualanTas {

    // 8. Fungsi Read (CRUD)
    void view() throws SQLException {
        String sql = "SELECT * FROM penjualan"; // Query untuk membaca data dari tabel
        Statement statement = ProgramPenjualanTas.conn.createStatement();
        ResultSet result = statement.executeQuery(sql);

        // 4. Perulangan untuk membaca semua data dari tabel
        while (result.next()) {
            System.out.println("\nID Tas           : " + result.getInt("id_tas"));
            System.out.println("Nama Tas         : " + result.getString("nama_tas")); // 5. Manipulasi String
            System.out.println("Harga Tas        : Rp " + result.getDouble("harga"));
            System.out.println("Stok             : " + result.getInt("stok"));
            System.out.println("-----------------------------");
        }
    }

    // 8. Fungsi Create (CRUD)
    void save() throws SQLException {
        // Input data untuk tas baru
        System.out.print("Nama Tas: ");
        String namaTas = ProgramPenjualanTas.input.nextLine(); // 5. Manipulasi String
        System.out.print("Harga Tas: ");
        double harga = ProgramPenjualanTas.input.nextDouble(); // 4. Perhitungan matematika
        System.out.print("Stok: ");
        int stok = ProgramPenjualanTas.input.nextInt(); // 4. Perhitungan matematika
        ProgramPenjualanTas.input.nextLine(); // Konsumsi newline

        String sql = "INSERT INTO penjualan (nama_tas, harga, stok) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = ProgramPenjualanTas.conn.prepareStatement(sql)) {
            preparedStatement.setString(1, namaTas);
            preparedStatement.setDouble(2, harga);
            preparedStatement.setInt(3, stok);

            // Eksekusi query dan periksa apakah data berhasil ditambahkan
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Data penjualan berhasil ditambahkan.");
            } else {
                System.out.println("Gagal menambahkan data.");
            }
        }
    }

    // 8. Fungsi Update (CRUD)
    void update() throws SQLException {
        // Input data untuk memperbarui tas
        System.out.print("Masukkan ID Tas yang ingin diperbarui: ");
        int idTas = ProgramPenjualanTas.input.nextInt();
        ProgramPenjualanTas.input.nextLine(); // Konsumsi newline
        System.out.print("Nama Tas Baru: ");
        String namaTas = ProgramPenjualanTas.input.nextLine(); // 5. Manipulasi String
        System.out.print("Harga Tas Baru: ");
        double harga = ProgramPenjualanTas.input.nextDouble(); // 4. Perhitungan matematika
        System.out.print("Stok Baru: ");
        int stok = ProgramPenjualanTas.input.nextInt(); // 4. Perhitungan matematika
        ProgramPenjualanTas.input.nextLine(); // Konsumsi newline

        String sql = "UPDATE penjualan SET nama_tas = ?, harga = ?, stok = ? WHERE id_tas = ?";
        PreparedStatement preparedStatement = ProgramPenjualanTas.conn.prepareStatement(sql);
        preparedStatement.setString(1, namaTas); // 5. Manipulasi String
        preparedStatement.setDouble(2, harga);
        preparedStatement.setInt(3, stok);
        preparedStatement.setInt(4, idTas);

        // Eksekusi query dan periksa apakah data berhasil diperbarui
        int rowsAffected = preparedStatement.executeUpdate();
        if (rowsAffected > 0) {
            System.out.println("Data penjualan berhasil diperbarui.");
        } else {
            System.out.println("Gagal memperbarui data.");
        }
    }

    // 8. Fungsi Delete (CRUD)
    void delete() throws SQLException {
        System.out.print("Masukkan ID Tas yang ingin dihapus: ");
        int idTas = ProgramPenjualanTas.input.nextInt(); // Input ID tas untuk dihapus
        ProgramPenjualanTas.input.nextLine(); // Konsumsi newline

        String sql = "DELETE FROM penjualan WHERE id_tas = ?";
        PreparedStatement preparedStatement = ProgramPenjualanTas.conn.prepareStatement(sql);
        preparedStatement.setInt(1, idTas);

        // Eksekusi query dan periksa apakah data berhasil dihapus
        int rowsAffected = preparedStatement.executeUpdate();
        if (rowsAffected > 0) {
            System.out.println("Data penjualan berhasil dihapus.");
        } else {
            System.out.println("Gagal menghapus data.");
        }
    }
}
