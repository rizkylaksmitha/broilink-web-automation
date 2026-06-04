Feature: Login BroiLink

  Background:
    Given pengguna membuka browser
    And pengguna membuka halaman login

  Scenario: Login dengan username dan password valid
    When pengguna memasukkan email "admin" dan password "admin123"
    And pengguna mengklik tombol masuk
    Then pengguna berhasil login

  Scenario: Login dengan username tidak valid
    When pengguna memasukkan email "salahuser" dan password "admin123"
    And pengguna mengklik tombol masuk
    Then pesan error login ditampilkan
    And pengguna tetap di halaman login

  Scenario: Login dengan password tidak valid
    When pengguna memasukkan email "admin" dan password "salahpassword"
    And pengguna mengklik tombol masuk
    Then pesan error login ditampilkan
    And pengguna tetap di halaman login

  Scenario: Login dengan username kosong
    When pengguna memasukkan email "" dan password "admin123"
    And pengguna mengklik tombol masuk
    Then form tidak dapat disubmit atau pesan error ditampilkan

  Scenario: Login dengan password kosong
    When pengguna memasukkan email "admin" dan password ""
    And pengguna mengklik tombol masuk
    Then form tidak dapat disubmit atau pesan error ditampilkan