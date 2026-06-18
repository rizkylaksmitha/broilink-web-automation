Feature: Admin Report and Request History
  As an Admin
  I want to manage request history status
  So that I can approve or reject user account requests

  Scenario: TC-ADM-16 Mengubah status permintaan
    Given Admin sudah login dan berada di halaman Riwayat Laporan BroiLink
    And Data permintaan terbaru tersedia untuk "budi.santoso"
    When Admin memilih baris pengguna "budi.santoso"
    And Admin mengubah status dropdown menjadi "Disetujui"
    Then Dropdown status berhasil diperbarui