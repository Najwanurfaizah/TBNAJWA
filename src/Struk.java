import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

class Struk {
    private List<Transaksi> transaksiList = new ArrayList<>();
    private Date tanggalTransaksi;

    public Struk() {
        this.tanggalTransaksi = new Date();
    }

    public void tambahTransaksi(Transaksi transaksi) {
        transaksiList.add(transaksi);
    }

    public void cetakStruk() {
        System.out.println("========= Struk Pembelian =========");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        System.out.println("Tanggal Transaksi: " + dateFormat.format(tanggalTransaksi));
        System.out.println("===================================");

        double totalPembelian = 0;
        for (Transaksi transaksi : transaksiList) {
            Tas tas = transaksi.getTas();
            int jumlahBeli = transaksi.getJumlahBeli();
            double totalBeli = transaksi.getTotalBeli();

            System.out.println("Item: " + tas.getMerek());
            System.out.println("Jumlah: " + jumlahBeli);
            System.out.println("Total Harga: Rp" + totalBeli);
            System.out.println("-----------------------------------");

            totalPembelian += totalBeli;
        }

        System.out.println("Total Pembelian: Rp" + totalPembelian);
        System.out.println("Terima kasih atas pembelian Anda!");
        System.out.println("===================================");
    }
}


