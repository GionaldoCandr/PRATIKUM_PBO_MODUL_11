import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        try {
            System.out.print("(pembilang): ");
            int pembilang = scanner.nextInt();
            
            System.out.print("(penyebut): ");
            int penyebut = scanner.nextInt();
            
            int hasil = pembilang / penyebut;
            System.out.println("Hasil: " + hasil);
            
        } catch (ArithmeticException e) {
            System.err.println("Peringatan: Tidak dapat membagi dengan angka nol!");
        } catch (InputMismatchException e) {
            System.err.println("Peringatan: Input harus berupa angka/karakter numerik!");
        } finally {
            scanner.close();
            System.out.println("Proses kalkulasi selesai dan resource memori telah dibersihkan.");
        }
    }
}
