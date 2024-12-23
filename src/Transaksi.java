class Transaksi {
    private Tas tas;
    private int jumlahBeli;
    private double totalBeli;
    private boolean member;

    public Transaksi(Tas tas, int jumlahBeli, boolean member) {
        this.tas = tas;
        this.jumlahBeli = jumlahBeli;
        this.member = member;
        hitungTotalBeli();
    }

    public Tas getTas() {
        return tas;
    }

    public int getJumlahBeli() {
        return jumlahBeli;
    }

    public double getTotalBeli() {
        return totalBeli;
    }

    public boolean isMember() {
        return member;
    }

    private void hitungTotalBeli() {
        double diskon = member ? 0.1 : 0.0;
        totalBeli = (tas.getHarga() * jumlahBeli) * (1 - diskon);
    }
}
