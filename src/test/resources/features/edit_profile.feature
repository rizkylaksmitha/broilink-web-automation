Feature: Edit Profile Role Peternak
  Background:
    Given user dalam keadaan terautentikasi sebagai peternak dan berada di halaman peternak profile

    Scenario: TC-EPP-01 Edit Profile Peternak
      When User mengisi data edit profile
      And User menekan tombol simpan
      Then muncul pop up berhasil

    Scenario: TC-EPP-02 Edit Profile dengan Email Kosong
      When User mengosongkan data email pada edit profile
      And User menekan tombol simpan
      Then muncul pop up error "The email field must be a valid email address."