@monitoring
Feature: Modul Monitoring Sensor & Dashboard Owner BroiLink

  Background:
    Given User melakukan login sebagai Owner dengan username "budi.santoso" dan password "password"

  Scenario: TC-MON-01 Memastikan Card Suhu tampil dan menampilkan data BVA batas optimal
    Given User berada di Halaman Simulasi "http://localhost:5173/simulation"
    When User memilih Kandang "Kandang 1" di halaman simulasi
    And User mengatur slider Suhu ke "30" derajat Celcius
    And User mengklik tombol Kirim Data Sekarang
    Then Sistem menampilkan notifikasi sukses pengiriman data
    And User membuka Halaman Monitoring "http://localhost:5173/owner/monitoring"
    Then Card Suhu Aktual menampilkan nilai "30°C"
    And Card Status Kandang menampilkan status "Normal" atau "Aman"

  Scenario: TC-MON-02 Memastikan sistem merespon data EP Invalid (Suhu > 35 derajat Celcius)
    Given User berada di Halaman Simulasi "http://localhost:5173/simulation"
    When User memilih Kandang "Kandang 1" di halaman simulasi
    And User mengatur slider Suhu ke "36" derajat Celcius
    And User mengklik tombol Kirim Data Sekarang
    Then Sistem menampilkan notifikasi sukses pengiriman data
    And User membuka Halaman Monitoring "http://localhost:5173/owner/monitoring"
    Then Card Suhu Aktual menampilkan nilai "36°C"
    And Card Status Kandang menampilkan status "Bahaya" atau "Waspada"

  Scenario: TC-MON-03 Memastikan nilai sensor Card Amonia sesuai dengan input simulasi
    Given User berada di Halaman Simulasi "http://localhost:5173/simulation"
    When User memilih Kandang "Kandang 1" di halaman simulasi
    And User mengatur slider Amonia ke "15" ppm
    And User mengklik tombol Kirim Data Sekarang
    Then Sistem menampilkan notifikasi sukses pengiriman data
    And User membuka Halaman Monitoring "http://localhost:5173/owner/monitoring"
    Then Card Kadar Amonia menampilkan nilai "15.00ppm" atau "15ppm"

  Scenario: TC-MON-04 Memastikan grafik terupdate secara real-time
    Given User membuka Halaman Monitoring "http://localhost:5173/owner/monitoring"
    Then Grafik monitoring sensor berhasil ditampilkan

  Scenario: TC-MON-05 Memastikan grafik menampilkan data sesuai rentang waktu filter (Historis)
    Given User membuka Halaman Monitoring "http://localhost:5173/owner/monitoring"
    When User memilih filter jangka waktu "1 Minggu Terakhir"
    Then Grafik monitoring sensor berhasil ditampilkan

  Scenario: TC-MON-06 Memastikan Card Kelembapan tampil dan menampilkan data BVA batas optimal
    Given User berada di Halaman Simulasi "http://localhost:5173/simulation"
    When User memilih Kandang "Kandang 1" di halaman simulasi
    And User mengatur slider Kelembapan ke "60" persen
    And User mengklik tombol Kirim Data Sekarang
    Then Sistem menampilkan notifikasi sukses pengiriman data
    And User membuka Halaman Monitoring "http://localhost:5173/owner/monitoring"
    Then Card Kelembapan Aktual menampilkan nilai "60%"

  Scenario: TC-MON-07 Memastikan Dashboard Owner menampilkan kondisi kandang dan aktivitas peternakan terbaru
    Given User membuka Halaman Dashboard Owner "http://localhost:5173/owner/dashboard"
    Then Dashboard Owner menampilkan nama kandang "Kandang Sleman Utara" atau "Kandang 1"
    And Dashboard Owner menampilkan status kandang "Waspada" atau "Normal" atau "Aman"
    And Dashboard Owner menampilkan suhu kandang terkini
    And Dashboard Owner menampilkan section Aktivitas Peternakan
