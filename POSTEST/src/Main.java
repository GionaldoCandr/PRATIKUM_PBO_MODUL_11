public class Main {
    public static void main(String[] args) {
        
        AkunBank akunPengirim = new AkunBank("REK-123", 150000);
        AkunBank akunPenerima = new AkunBank("REK-321", 0);

        try {
            akunPengirim.tarikTunai(150000); 

            akunPengirim.transfer(akunPenerima, 11000); 
            
            System.out.println("Pesan ini tidak akan tercetak jika terjadi exception di atas.");

        } 
        catch (BatasTransferHarian e) {
            System.out.println("[ERROR LIMIT HARIAN] " + e.getMessage());
        } 
        catch (SaldoTidakMencukupi e) {
            System.out.println("[ERROR SALDO] " + e.getMessage());
            System.out.println("  -> Anda kekurangan dana sebesar: Rp" + e.getNominalKekurangan());
        } 
        catch (Exception e) {
            System.out.println("[ERROR SISTEM] Terjadi kesalahan fatal: " + e.getMessage());
        } 
    }
}