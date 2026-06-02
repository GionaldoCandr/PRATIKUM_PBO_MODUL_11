class SistemAkademik {
    
    public void cetakDokumenKrs(String namaFile) throws FileNotFoundException {
        System.out.println("Memproses pencetakan file: " + namaFile + "...");
        
        // Logika Pengecekan Nama File
        if (!namaFile.equals("krs_valid.txt")) {
            throw new FileNotFoundException("Gagal: File '" + namaFile + "' tidak ditemukan di direktori!");
        }
        
        System.out.println(" -> Pencetakan Berhasil! Dokumen KRS (" + namaFile + ") sedang dicetak.");
    }
}
