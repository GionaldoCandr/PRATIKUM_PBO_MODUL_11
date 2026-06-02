class SistemAkademik {
    
    public void cetakDokumenKrs(String namaFile) throws FileNotFoundException {
        System.out.println("Memproses pencetakan file: " + namaFile + "...");
        
        // Logika Pengecekan Nama File
        if (!namaFile.equals("krs_valid.txt")) {
            throw new FileNotFoundException("Gagal: File '" + namaFile + "' tidak ditemukan di direktori!");
        }
        
        System.out.println(" -> Pencetakan Berhasil! Dokumen KRS (" + namaFile + ") sedang dicetak.");
    }

    public void gabungKelas(String namaKelas, int jumlahPeserta) throws KelasPenuhException {
        System.out.println("Mencoba bergabung ke kelas: " + namaKelas + " dengan " + jumlahPeserta + " peserta...");
        
        if (jumlahPeserta >= 30) {
            throw new KelasPenuhException("Gagal: Kelas '" + namaKelas + "' sudah penuh dengan " + jumlahPeserta + " peserta!");
        }
        
        System.out.println(" -> Bergabung Berhasil! Anda telah bergabung ke kelas '" + namaKelas + "'.");
}

}
