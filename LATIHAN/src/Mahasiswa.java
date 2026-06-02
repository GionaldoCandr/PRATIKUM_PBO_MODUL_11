public class Mahasiswa {
    int sks ;
    int sisaSks;
    String namaMatkul;
    int bebanSks;




    public void setSksMaksimal(int sks) {
        if (sks > 24 || sks < 3) {
            throw new IllegalArgumentException("Kesalahan sistem: Batas SKS tidak valid (harus antara 2 dan 24).");
        }
        this.sks = sks;
        System.out.println("SKS maksimal berhasil diatur: " + sks);
    }


   public void ambilMataKuliah(String namaMatkul, int bebanSks) {
        System.out.println("mengambil mata kuliah: " + namaMatkul + " (" + bebanSks + " SKS)");
        
        if (bebanSks > this.sisaSks) {
            throw new SksTidakCukupException("SKS tidak mencukupi untuk mengambil " + namaMatkul + 
                                             ". (Sisa SKS Anda: " + this.sisaSks + ", Beban SKS: " + bebanSks + ")");
        }
        
        this.sisaSks -= bebanSks;
        System.out.println(" -> Berhasil Anda mengambil " + namaMatkul + ". Sisa SKS sekarang: " + this.sisaSks + "\n");
    }


    
}
