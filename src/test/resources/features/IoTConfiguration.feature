Feature: Admin IoT Configuration
  As an Admin
  I want to configure IoT threshold limits for cages
  So that abnormal farm conditions trigger real-time alerts

  Background:
    Given Admin sudah login dan berada di halaman Konfigurasi IoT

  Scenario: TC-ADM-13 Validasi form wajib diisi
    When Admin selects cage from dropdown
    And Admin leaves one of the sensor fields empty
    And Admin trigger save for IoT configuration
    Then The system should reject saving and show error that all fields are required

  Scenario: TC-ADM-14 Simpan batas sensor dengan nilai normal
    When Admin selects cage from dropdown
    And Admin fills all sensor fields with valid data temperature "28" and humidity "50"
    And Admin trigger save for IoT configuration
    Then The system should successfully save and show toast "Berhasil! Konfigurasi berhasil disimpan"

  Scenario: TC-ADM-15 Validasi input nilai negatif
    When Admin selects cage from dropdown
    And Admin inputs a negative value "-35" into max temperature field
    And Admin trigger save for IoT configuration
    Then The system should reject saving and show error validation that value cannot be negative