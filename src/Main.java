import java.util.Random;

class UcakRadarMonteCarlo {

    // Öğrenci numarası (Seed)
    private static final long SEED = 1240505012L;
    // Veri boyutu (n = 10^5)
    private static final int N = 100000;
    // Sistemdeki gerçek arızalı uçak sayısı (Bizim belirlediğimiz bir M değeri)
    private static final int ARIZALI_UCAK_SAYISI = 500;
    // Monte Carlo için maksimum deneme sayısı (k)
    private static final int K_ITERASYON = 200;
    // Deneyin tekrarlanma sayısı
    private static final int DENEY_SAYISI = 100;

    public static void main(String[] args) {
        Random random = new Random(SEED);

        // 1. Veri Setinin Oluşturulması (Hava Sahası)
        // 0: Normal Uçuş, 1: Arızalı Uçuş (Özel Eleman)
        int[] havaSahasi = new int[N];

        // Rastgele yerlere arızalı uçakları yerleştir
        int yerlestirilen = 0;
        while (yerlestirilen < ARIZALI_UCAK_SAYISI) {
            int rastgeleIndex = random.nextInt(N);
            if (havaSahasi[rastgeleIndex] == 0) {
                havaSahasi[rastgeleIndex] = 1;
                yerlestirilen++;
            }
        }

        // 2. Deneyin 100 Kez Çalıştırılması ve Analiz
        int hataSayisi = 0;
        long[] calismaSureleri = new long[DENEY_SAYISI];
        long toplamSure = 0;

        System.out.println("--- Monte Carlo Radar Taraması Başlıyor ---");
        System.out.println("Veri Boyutu: " + N + " uçak");
        System.out.println("Arızalı Uçak Sayısı: " + ARIZALI_UCAK_SAYISI);
        System.out.println("İterasyon Limiti (k): " + K_ITERASYON);
        System.out.println("-------------------------------------------");

        for (int i = 0; i < DENEY_SAYISI; i++) {
            long baslangicZamani = System.nanoTime();

            // Monte Carlo Algoritmasını Çalıştır
            boolean arizaBulundu = monteCarloRadarTarama(havaSahasi, random);

            long bitisZamani = System.nanoTime();
            long gecenSure = bitisZamani - baslangicZamani;

            calismaSureleri[i] = gecenSure;
            toplamSure += gecenSure;

            // Eğer algoritma arızalı uçak bulamadıysa, hata yapmıştır (çünkü sistemde arızalı uçak var)
            if (!arizaBulundu) {
                hataSayisi++;
            }
        }

        // 3. İstatistiksel Hesaplamalar
        double deneyselHataOrani = (double) hataSayisi / DENEY_SAYISI;
        double ortalamaSureNano = (double) toplamSure / DENEY_SAYISI;
        double standartSapma = standartSapmaHesapla(calismaSureleri, ortalamaSureNano);

        System.out.printf("100 Çalıştırma Sonucu Deneysel Hata Oranı: %%%.2f\n", (deneyselHataOrani * 100));
        System.out.printf("Ortalama Çalışma Süresi: %.2f nanosaniye\n", ortalamaSureNano);
        System.out.printf("Çalışma Süresi Standart Sapması: %.2f nanosaniye\n", standartSapma);
    }

    /**
     * Monte Carlo algoritması ile k kez rastgele uçak seçip arıza arar.
     */
    private static boolean monteCarloRadarTarama(int[] havaSahasi, Random random) {
        for (int i = 0; i < K_ITERASYON; i++) {
            int secilenUcakIndex = random.nextInt(N);
            // Eğer özel elemanı (arızalı uçak) bulduysa doğru sonuç üretir
            if (havaSahasi[secilenUcakIndex] == 1) {
                return true;
            }
        }
        // k iterasyon bitti ve bulamadıysa hata yapar (False Negative)
        return false;
    }

    /**
     * Zaman analizi için standart sapma hesaplar.
     */
    private static double standartSapmaHesapla(long[] sureler, double ortalama) {
        double toplamKareFark = 0;
        for (long sure : sureler) {
            toplamKareFark += Math.pow(sure - ortalama, 2);
        }
        return Math.sqrt(toplamKareFark / sureler.length);
    }
}