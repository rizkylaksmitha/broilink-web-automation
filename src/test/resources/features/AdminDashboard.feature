Feature: Admin Dashboard BroiLink

  Background:
    Given Admin sudah login dan berada di halaman Dashboard

  Scenario: TC-ADM-01 Validasi statistik dashboard
    When Admin memeriksa data angka pada ringkasan statistik
    Then Angka tampil akurat

  Scenario: TC-ADM-02 Validasi permintaan terbaru
    When Admin memeriksa komponen "Permintaan Terbaru"
    Then Menampilkan antrean log secara real-time