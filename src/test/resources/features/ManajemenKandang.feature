Feature: Manajemen Kandang BroiLink

  Background:
    Given Admin sudah login dan berada di halaman Manajemen Kandang

  Scenario: TC-ADM-10 Penugasan Peternak ke kandang
    And Data kandang dan peternak sudah terdaftar
    When Admin memilih dropdown Kandang "Kandang Sleman Utara - Owner: Budi Santoso"
    And Admin memilih Peternak "Ahmad Fauzi"
    And Admin mengklik Simpan Penugasan
    Then Hubungan relasi kerja berhasil disimpan dan sistem menampilkan notifikasi "Peternak berhasil ditugaskan"

  Scenario: TC-ADM-11 Update luas dan jumlah ayam (Positive)
    When Admin menginput angka luas "100" dan jumlah ayam "1000" pada kolom
    And Admin mengklik Simpan
    Then Data sukses disimpan dan sistem menampilkan notifikasi "Luas kandang berhasil diperbarui"

  Scenario: TC-ADM-12a Validasi batas minimum luas kandang bernilai 0 (Negative)
    When Admin menginput angka luas "0" dan jumlah ayam "1000" pada kolom
    And Admin mengklik Simpan
    Then Sistem menolak simpan dan menampilkan pesan eror "Luas kandang minimal 1 m²"

  Scenario: TC-ADM-12b Validasi batas minimum jumlah ayam bernilai minus (Negative)
    When Admin menginput angka luas "100" dan jumlah ayam "-10" pada kolom
    And Admin mengklik Simpan
    Then Sistem menolak simpan dan menampilkan pesan eror "Jumlah ayam minimal 1 ekor"

  Scenario: TC-ADM-12c Validasi kombinasi batas minimum tidak valid (Negative)
    When Admin menginput angka luas "0" dan jumlah ayam "-10" pada kolom
    And Admin mengklik Simpan
    Then Sistem menolak simpan dan menampilkan pesan eror "Luas kandang minimal 1 m²" atau "Jumlah ayam minimal 1 ekor"