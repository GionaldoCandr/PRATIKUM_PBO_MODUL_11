import java.util.*;


// a. Unchecked Exception (Mewarisi RuntimeException)
class DataPenumpangTidakValidException extends RuntimeException {
    public DataPenumpangTidakValidException(String message) {
        super(message);
    }
}

// b. Checked Exception (Mewarisi Exception)
class RuteTidakDitemukanException extends Exception {
    public RuteTidakDitemukanException(String message) {
        super(message);
    }
}

// c. Checked Exception Khusus (Mewarisi Exception dan Menyimpan State/Info)
class TiketHabisException extends Exception {
    private String namaKereta;
    private int sisaKursi;

    public TiketHabisException(String message, String namaKereta, int sisaKursi) {
        super(message);
        this.namaKereta = namaKereta;
        this.sisaKursi = sisaKursi;
    }

    public String getNamaKereta() { return namaKereta; }
    public int getSisaKursi() { return sisaKursi; }
}

// 2. KELAS KERETA API
class Kereta {
    private String kode;
    private String nama;
    private String rute;
    private int sisaKursi;

    public Kereta(String kode, String nama, String rute, int kapasitas) {
        this.kode = kode;
        this.nama = nama;
        this.rute = rute;
        this.sisaKursi = kapasitas;
    }

    public String getKode() { return kode; }
    public String getNama() { return nama; }
    public String getRute() { return rute; }
    public int getSisaKursi() { return sisaKursi; }

    public void kurangiKursi(int jumlah) {
        this.sisaKursi -= jumlah;
    }

    public void tampilkanInfo() {
        System.out.printf("| %-5s | %-15s | %-12s | %-10d |\n", kode, nama, rute, sisaKursi);
    }
}

// 3. KELAS SISTEM RESERVASI
class SistemReservasi {
    private List<Kereta> daftarKereta;

    public SistemReservasi() {
        daftarKereta = new ArrayList<>();
        daftarKereta.add(new Kereta("K01", "Argo Bromo", "JKT - SBY", 50));
        daftarKereta.add(new Kereta("K02", "Parahyangan", "JKT - BDG", 15));
    }

    public void lihatJadwal() {
        System.out.println("                 JADWAL KERETA API AKTIF                 ");
        System.out.printf("| %-5s | %-15s | %-12s | %-10s |\n", "KODE", "NAMA KERETA", "RUTE", "SISA KURSI");
        System.out.println("---------------------------------------------------------");
        for (Kereta k : daftarKereta) {
            k.tampilkanInfo();
        }
    }

    public void pesanTiket(String kode, String nik, String namaPenumpang, int jumlahTiket) 
            throws RuteTidakDitemukanException, TiketHabisException {
        
        System.out.println("\n[SISTEM] Memproses reservasi Anda...");

        // 1. Validasi NIK (Unchecked Exception ditaruh lebih awal)
        if (nik.length() != 16 || !nik.matches("\\d+")) {
            throw new DataPenumpangTidakValidException("Penolakan Sistem: NIK harus terdiri dari TEPAT 16 digit angka tanpa huruf/simbol!");
        }

        // 2. Cari Kereta
        Kereta keretaPilihan = null;
        for (Kereta k : daftarKereta) {
            if (k.getKode().equalsIgnoreCase(kode)) {
                keretaPilihan = k;
                break;
            }
        }

        // Validasi Kereta (Checked Exception)
        if (keretaPilihan == null) {
            throw new RuteTidakDitemukanException("Penolakan Sistem: Rute dengan kode '" + kode + "' tidak terdaftar di jadwal kami.");
        }

        // 3. Validasi Kapasitas Kursi (Checked Exception)
        if (jumlahTiket > keretaPilihan.getSisaKursi()) {
            throw new TiketHabisException("Penolakan Sistem: Jumlah pemesanan melebihi kapasitas yang tersedia.", 
                                          keretaPilihan.getNama(), 
                                          keretaPilihan.getSisaKursi());
        }

        // 4. Proses Berhasil (Enhancement: Cetak E-Ticket)
        keretaPilihan.kurangiKursi(jumlahTiket);
        String idTransaksi = "TRX-" + UUID.randomUUID().toString().substring(0, 5).toUpperCase();
        
        System.out.println("          E-TICKET JAVA EXPRESS       ");
        System.out.println("ID Transaksi  : " + idTransaksi);
        System.out.println("Nama Penumpang: " + namaPenumpang);
        System.out.println("NIK           : " + nik);
        System.out.println("Kereta        : " + keretaPilihan.getNama() + " (" + keretaPilihan.getRute() + ")");
        System.out.println("Jumlah Tiket  : " + jumlahTiket);
        System.out.println("Status        : LUNAS & TERKONFIRMASI");
    }
}

// 4. KELAS UTAMA
public class JavaExpressApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SistemReservasi sistem = new SistemReservasi();
        boolean isRunning = true;

        // Blok Try utama untuk menjamin eksekusi finally di akhir program
        try {
            while (isRunning) {
                System.out.println("\n--- MENU UTAMA ---");
                System.out.println("1. Lihat Jadwal Kereta");
                System.out.println("2. Pesan Tiket");
                System.out.println("0. Keluar Aplikasi");
                System.out.print("Pilih menu > ");

                // Menangkap potensi error jika user memasukkan huruf saat memilih menu
                int pilihan = -1;
                try {
                    pilihan = scanner.nextInt();
                    scanner.nextLine(); // Membersihkan sisa newline (Enter)
                } catch (InputMismatchException e) {
                    System.out.println("\n[ERROR INPUT] Anda memasukkan huruf atau simbol. Harap masukkan angka menu (1, 2, atau 0)!");
                    scanner.nextLine(); // WAJIB: Membersihkan buffer agar loop tidak infinite
                    continue; // Mengulang loop while
                }

                switch (pilihan) {
                    case 1:
                        sistem.lihatJadwal();
                        break;
                    case 2:
                        try {
                            System.out.println("\n--- FORM PEMESANAN TIKET ---");
                            System.out.print("Masukkan Kode Kereta : ");
                            String kode = scanner.nextLine();
                            System.out.print("Masukkan NIK (16 digit) : ");
                            String nik = scanner.nextLine();
                            System.out.print("Masukkan Nama Lengkap  : ");
                            String nama = scanner.nextLine();
                            System.out.print("Masukkan Jumlah Tiket  : ");
                            
                            int jumlah = scanner.nextInt();
                            scanner.nextLine(); // Bersihkan newline

                            // Memanggil method yang mendeklarasikan throws
                            sistem.pesanTiket(kode, nik, nama, jumlah);

                        } 
                        // CATCH 1: Error salah ketik angka jumlah tiket
                        catch (InputMismatchException e) {
                            System.out.println("\n[ERROR INPUT] Jumlah tiket harus berupa ANGKA BULAT!");
                            scanner.nextLine(); // Bersihkan buffer
                        } 
                        // CATCH 2: Error Validasi NIK (Unchecked Exception)
                        catch (DataPenumpangTidakValidException e) {
                            System.out.println("\n[VALIDASI GAGAL] " + e.getMessage());
                        } 
                        // CATCH 3: Error Kode Kereta Salah (Checked Exception)
                        catch (RuteTidakDitemukanException e) {
                            System.out.println("\n[RUTE GAGAL] " + e.getMessage());
                        } 
                        // CATCH 4: Error Kursi Tidak Cukup (Checked Exception khusus dengan parameter tambahan)
                        catch (TiketHabisException e) {
                            System.out.println("\n[STOK GAGAL] " + e.getMessage());
                            System.out.println("-> Info   : Kereta " + e.getNamaKereta() + " saat ini hanya tersisa " + e.getSisaKursi() + " kursi.");
                        } 
                        // CATCH 5: Error Sistem Tak Terduga (Fallback)
                        catch (Exception e) {
                            System.out.println("\n[SYSTEM ERROR] Terjadi kesalahan fatal: " + e.getMessage());
                        }
                        break;

                    case 0:
                        isRunning = false;
                        break;

                    default:
                        System.out.println("\n[PERINGATAN] Menu tidak valid. Silakan pilih 1, 2, atau 0.");
                }
            }
        } finally {
            // Blok Finally dijamin PASTI tereksekusi saat user keluar dari menu atau terjadi crash sistem
            if (scanner != null) {
                scanner.close(); // Membersihkan kebocoran memori (memory leak)
            }
            System.out.println(" [SYSTEM LOG] Blok penanganan akhir (finally) dieksekusi.");
            System.out.println(" Sesi JAVA EXPRESS telah ditutup. Memori telah dibersihkan.");
        }
    }
}