class AkunBank {
    private String nomorRekening;
    private double saldo;
    private double totalTransferHariIni;
    
    private final double LIMIT_TRANSFER_HARIAN = 10000000.0; 

    public AkunBank(String nomorRekening, double saldoAwal) {
        this.nomorRekening = nomorRekening;
        this.saldo = Math.max(saldoAwal, 0);
        this.totalTransferHariIni = 0.0;
    }

    public void tarikTunai(double nominal) throws SaldoTidakMencukupi {
        System.out.println("\n[PROSES] Mencoba menarik tunai Rp" + nominal  );
        
        if (nominal > saldo) {
            double kekurangan = nominal - saldo;
            throw new SaldoTidakMencukupi("Penarikan Ditolak! Saldo Anda tidak mencukupi.", kekurangan);
        }
        
        saldo -= nominal;
        System.out.println("  -> Penarikan berhasil. Sisa saldo: Rp" + saldo);
    }

    public void transfer(AkunBank tujuan, double nominal) throws BatasTransferHarian, SaldoTidakMencukupi {
        System.out.println("\n[PROSES] Mencoba transfer Rp" + nominal + " ke rekening " + tujuan.nomorRekening + "...");
        
        if (totalTransferHariIni + nominal > LIMIT_TRANSFER_HARIAN) {
            throw new BatasTransferHarian("Transfer Ditolak! Akumulasi transfer melampaui limit harian Rp10.000.000.");
        }
        
        if (nominal > saldo) {
            double kekurangan = nominal - saldo;
            throw new SaldoTidakMencukupi("Transfer Ditolak! Saldo Anda tidak mencukupi untuk transfer.", kekurangan);
        }
        
        this.saldo -= nominal;
        this.totalTransferHariIni += nominal;
        tujuan.terimaDana(nominal); 
        
        System.out.println("  -> Transfer berhasil! Sisa saldo: Rp" + saldo + " | Total transfer hari ini: Rp" + totalTransferHariIni);
    }

    private void terimaDana(double nominal) {
        this.saldo += nominal;
    }
}