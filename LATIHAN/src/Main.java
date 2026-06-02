import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // soal 1
        int[] kuotaMatkul = new int[3]; 

        for (int i = 0; i <= 3; i++) {
            try {
                System.out.print("Masukkan kuota matkul ke-" + (i + 1) + ": ");
                kuotaMatkul[i] = input.nextInt();
                System.out.println("  -> Kuota ditambahkan: " + kuotaMatkul[i]);
            } catch (InputMismatchException e) {
                System.out.println("  -> [Input mismatch]: Tidak boleh huruf atau karakter, harus angka!");
                input.nextLine(); // Membersihkan buffer Scanner agar tidak loop tak berujung
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("  -> [Illegal argument]: Array Penuh! " + e.getMessage());
            }
        }

        // soal 2
        Mahasiswa mhs1 = new Mahasiswa();
        
        try {
            mhs1.setSksMaksimal(20); 
            mhs1.setSksMaksimal(25);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } 


        // soal 3
            Mahasiswa mhsAnton = new Mahasiswa();

        try {
            mhsAnton.setSksMaksimal(20);
            System.out.println();

            mhsAnton.ambilMataKuliah("Pemrograman Berorientasi Objek", 4);
            mhsAnton.ambilMataKuliah("Basis Data", 3);
            mhsAnton.ambilMataKuliah("Kalkulus Dasar", 4);
            mhsAnton.ambilMataKuliah("Tugas Akhir / Skripsi", 10);
            System.out.println("Pesan ini tidak akan tercetak.");

        } 
        catch (SksTidakCukupException e) {
            System.out.println("\n[CUSTOM EXCEPTION] " + e.getMessage());
        } 
        catch (IllegalArgumentException e) {
            System.out.println("\n[ERROR ARGUMEN] " + e.getMessage());
        } 

        // soal 4
        SistemAkademik sistem = new SistemAkademik();

        try {
            sistem.gabungKelas("PBO-KelasA", 0);
            
            System.out.println("Pesan ini tidak akan tercetak.");
            
        } catch (KelasPenuhException e) {
            System.out.println("[ERROR CHECKED EXCEPTION] " + e.getMessage());
        }
        // soal 5
        
    }    
}