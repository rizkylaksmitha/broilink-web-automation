Feature: Modul Login BroiLink

  Background:
    Given Halaman login terbuka

  Scenario: TC-LG-01 Login valid
    When User memasukkan username "admin" dan password "password"
    And User mengklik tombol Masuk
    Then Berhasil login dan masuk ke Dashboard Admin

  Scenario: TC-LG-02 Login dengan username tidak valid
    When User memasukkan username "admin_salah" dan password "password"
    And User mengklik tombol Masuk
    Then Sistem menampilkan pesan error login

  Scenario: TC-LG-03 Login dengan password tidak valid
    When User memasukkan username "admin" dan password "salah123"
    And User mengklik tombol Masuk
    Then Sistem menampilkan pesan error login

  Scenario: TC-LG-04 Login dengan username kosong
    When User memasukkan username "" dan password "password"
    And User mengklik tombol Masuk
    Then Sistem menolak login atau menampilkan pesan validasi

  Scenario: TC-LG-05 Login dengan password kosong
    When User memasukkan username "admin" dan password ""
    And User mengklik tombol Masuk
    Then Sistem menolak login atau menampilkan pesan validasi

  Scenario: TC-LG-06 Login dengan password kurang dari batas minimum (BVA)
    When User memasukkan username "admin" dan password "passwor"
    And User mengklik tombol Masuk
    Then Sistem menolak login atau menampilkan pesan validasi

  Scenario: TC-LG-07 Login dengan password tepat batas minimum (BVA)
    When User memasukkan username "admin" dan password "password"
    And User mengklik tombol Masuk
    Then Sistem menerima input password

  Scenario: TC-LG-08 Login dengan password di atas batas minimum (BVA)
    When User memasukkan username "admin" dan password "passwordd"
    And User mengklik tombol Masuk
    Then Sistem menerima input password