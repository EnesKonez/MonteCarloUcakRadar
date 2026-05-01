# ✈️ Hava Trafik Radar Simülasyonu - Monte Carlo Algoritması

Bu proje, Algoritma Analizi dersi kapsamında büyük veri setleri içerisindeki "özel" elemanların rastgelelik kullanılarak tespit edilmesini amaçlayan bir **Monte Carlo** algoritması uygulamasıdır. Standart bir dizi arama problemi, gerçek dünya senaryosuna uyarlanarak bir Hava Trafik Kontrol Radar Sisteminin simülasyonu şeklinde kurgulanmıştır.

## 📝 Proje Senaryosu
Hava trafik kontrol radarında anlık olarak **100.000** adet uçuş verisi (sinyali) bulunmaktadır. Bu uçaklardan **500** tanesi radar sistemine motor arızası vb. acil durumlar için kullanılan **"Squawk 7700"** sinyali göndermektedir. 

Sistemi yormamak ve zaman tasarrufu sağlamak amacıyla, 100.000 uçağın tamamını taramak yerine **Monte Carlo** stratejisi kullanılarak sadece **200** rastgele uçak kontrol edilmektedir. Algoritma bu 200 denemede arızalı uçağı bulursa sistemi uyarır, bulamazsa hata payı çerçevesinde sistemin temiz olduğunu varsayar.

## ⚙️ Parametreler ve Gereksinimler
Proje kısıtları, geliştiricinin öğrenci numarasına (**1240505012**) göre özel olarak belirlenmiştir:
- **Algoritma Tipi:** Monte Carlo *(Öğrenci no son iki hanesi çift)*
- **Veri Hacmi (n):** 10^5 (100.000) *(Öğrenci no son rakamı 2 < 5)*
- **Rastgelelik (Seed):** Sistem rastgeleliği test edilebilirlik açısından `1240505012L` seed değeri ile beslenmiştir.

## 📊 Matematiksel ve Deneysel Analiz
- **Arızalı Uçak (M):** 500
- **İterasyon Limiti (k):** 200
- **Teorik Hata Olasılığı (P_error):** (1 - 500/100000)^200 ≈ **%36.69**

Program, deneysel sonuçları teorik hesaplamalarla karşılaştırmak için aralıksız 100 kez çalıştırılır. Çalışma süreleri kaydedilerek, rastgele algoritmaların zaman karmaşıklığındaki dalgalanmayı ispatlamak adına **Standart Sapma** hesaplanır.

## 🚀 Kurulum ve Çalıştırma

Projeyi yerel ortamınızda çalıştırmak için sisteminizde Java (JDK 8 veya üzeri) kurulu olmalıdır.

1. Projeyi bilgisayarınıza klonlayın veya indirin.
2. Terminal veya komut satırını açarak projenin bulunduğu dizine gidin.
3. Kaynak kodunu derleyin:
   ```bash
   javac UcakRadarMonteCarlo.java
