Ucak Bileti Senaryosu
==========================

This is an executable specification file. This file follows markdown syntax. Every heading in this file denotes a scenario. Every bulleted point denotes a step.
To execute this specification, use `mvn test`



Ucak Bileti Bulma
-----------
* "https://www.enuygun.com/" adresine git
* Şu anki URL "https://www.enuygun.com/" değerini içeriyor mu kontrol et
* Elementine tıkla "ucakBiletiMenuButonu"
* Şu anki URL "https://www.enuygun.com/ucak-bileti/" değerini içeriyor mu kontrol et
* Element "ucakBiletiAramaContainer" var mı kontrol et yoksa hata mesajı ver "Uçak Bileti Sorgulama Alanı Görülmedi!"
* "neredenTextboxi" elementine "İstanbul" yazilir ve enter gonderilir
* "nereyeTextboxi" elementine "Antalya" yazilir ve enter gonderilir
* Elementine tıkla "aktarmasizUcusCheckboxi"
* Elementine tıkla "tekYonBoxi"
* "gidisTarihiBoxi" gidis tarihi "Pazartesi, 1 Mayıs 2023" secilir
* "donusTarihiBoxi" donus tarihi "Çarşamba, 3 Mayıs 2023" secilir
* Elementine tıkla "yolcuSayisiBoxi"
* "2" tane "Yetiskin" sinifi secilir
* "2" tane "65 yaş üstü" sinifi secilir
* "2" tane "Çocuk" sinifi secilir
* "2" tane "Bebek" sinifi secilir
* Ucak sinifi "Business" olarak secilir
* Elementine tıkla "biletAraButonu"
* Element "biletAramaKontrolu" var mı kontrol et yoksa hata mesajı ver "Arama Sayfasina Gidilmedi!"
* Element "biletAramaListeKontrolu" var mı kontrol et yoksa hata mesajı ver "Aradiginiz Sartlarda Bilet Bulunamadi!"






