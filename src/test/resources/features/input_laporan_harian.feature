@InputLaporanHarian
Feature: Input Laporan Harian

  Background:
    Given user dalam keadaaan terautentikasi sebagai peternak

  Scenario Outline: Pengisian laporan harian
    When user mengisi formulir laporan harian dengan data berikut:
      | pakan    | <laporan_pakan_harian>    |
      | minum    | <laporan_minum_harian>    |
      | bobot    | <laporan_sampling_bobot>  |
      | kematian | <tingkat_kematian>        |
    And user menekan tombol kirim laporan
    Then muncul pop up notifikasi dengan status "<result>"

    Examples:
      | laporan_pakan_harian | laporan_minum_harian | laporan_sampling_bobot | tingkat_kematian | result  |
      | 2                    | 4                    | 20                     | 3                | success |