// 3. Memiliki inheritance (superclass dan subclass)
public class TasPremium extends Tas {
    private double diskonPremium; // Diskon khusus untuk tas premium

    // Constructor yang memanggil constructor superclass
    public TasPremium(String merek, double harga, int ukuran, double diskonPremium) {
        super(merek, harga, ukuran);
        this.diskonPremium = diskonPremium; // Diskon dalam persen (0-100)
    }

    // Getter untuk atribut diskonPremium
    public double getDiskonPremium() {
        return diskonPremium;
    }

    // Setter untuk atribut diskonPremium
    public void setDiskonPremium(double diskonPremium) {
        this.diskonPremium = diskonPremium;
    }

    // 3. Override metode dari superclass
    @Override
    public void tampilkanInfo() {
        super.tampilkanInfo(); // Memanggil metode dari superclass
        System.out.println("Diskon Premium: " + diskonPremium + "%");
        System.out.println("Harga Setelah Diskon: Rp" + hitungHargaDiskon()); // 4. Perhitungan matematika
    }

    // Metode tambahan untuk menghitung harga setelah diskon
    public double hitungHargaDiskon() {
        return getHarga() * (1 - (diskonPremium / 100));
    }
}
