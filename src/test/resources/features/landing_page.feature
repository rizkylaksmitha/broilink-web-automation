Feature: Halaman Utama BroiLink

  Scenario: TC-LP-01 Membuka halaman utama BroiLink
    Given Browser dalam kondisi aktif
    When User membuka URL BroiLink "http://localhost:5173"
    Then Landing Page berhasil ditampilkan

  Scenario: TC-LP-02 Navigasi dari Landing Page ke Login
    Given Landing Page terbuka
    When User mengklik tombol Gabung
    Then Sistem mengarahkan ke halaman Login