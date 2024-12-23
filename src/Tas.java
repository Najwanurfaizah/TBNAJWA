// 1. Terdiri dari class, objek, dan constructor
// 2. Implementasi interface
class Tas implements TasInterface {
    private String merek; // Atribut merek tas
    private double harga; // Atribut harga tas
    private int ukuran;   // Atribut ukuran tas

    // Constructor untuk inisialisasi atribut
    public Tas(String merek, double harga, int ukuran) {
        this.merek = merek;
        this.harga = harga;
        this.ukuran = ukuran;
    }

    // Getter untuk atribut merek
    public String getMerek() {
        return merek;
    }

    // Getter untuk atribut harga
    public double getHarga() {
        return harga;
    }

    // Getter untuk atribut ukuran
    public int getUkuran() {
        return ukuran;
    }

    // 2. Implementasi metode dari interface
    @Override
    public void tampilkanInfo() {
        System.out.println("Merek: " + merek); // 5. Manipulasi String
        System.out.println("Harga: Rp" + harga);
        System.out.println("Ukuran: " + ukuran + " cm");
    }
}
