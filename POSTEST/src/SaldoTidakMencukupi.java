class SaldoTidakMencukupi extends Exception {
    private double nominalKekurangan;

    public SaldoTidakMencukupi(String pesan, double nominalKekurangan) {
        super(pesan); 
        this.nominalKekurangan = nominalKekurangan;
    }

    public double getNominalKekurangan() {
        return nominalKekurangan;
    }
}