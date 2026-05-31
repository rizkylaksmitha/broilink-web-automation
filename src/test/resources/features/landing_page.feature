Feature: Landing Page BroiLink

  Scenario: Membuka halaman utama BroiLink
    When pengguna mengakses halaman utama BroiLink
    Then halaman landing berhasil tampil

  Scenario: Tombol login tersedia di landing page
    When pengguna mengakses halaman utama BroiLink
    Then tombol atau link menuju halaman login tersedia

  Scenario: Navigasi dari landing page ke login page
    When pengguna mengakses halaman utama BroiLink
    And pengguna mengklik tombol login
    Then pengguna diarahkan ke halaman login
    And URL halaman mengandung "/login"