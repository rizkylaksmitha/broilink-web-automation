Feature: Admin User Management
  As an Admin
  I want to manage user accounts (Peternak and Owner)
  So that only authorized users can access the system

  Background:
    Given Admin sudah login dan berada di halaman Manajemen Pengguna

  Scenario: Successful Peternak Account Creation
    When Admin clicks "Tambahkan Akun" button
    And Admin selects the "Tambahkan Peternak" tab
    And Admin fills in all required peternak fields with valid data for "Aulia Rizky"
    And Admin clicks the "Tambah" confirmation button
    Then The modal should close
    And Admin should see a success alert message "Akun Peternak berhasil dibuat"

  Scenario: Negative Partitions - Leave Required Fields Empty
    When Admin clicks "Tambahkan Akun" button
    And Admin leaves required fields empty
    And Admin clicks the "Tambah" confirmation button
    Then Admin should see a validation error message indicating fields are required

  Scenario: Boundary Value Analysis - Empty Optional Email Field for Owner
    When Admin clicks "Tambahkan Akun" button
    And Admin selects the "Tambahkan Owner" tab
    And Admin fills in required fields for owner "Rizky Laksmitha" but leaves "Email" empty
    And Admin clicks the "Tambah" confirmation button
    Then The modal should close
    And Admin should see a success alert message "Akun Owner berhasil dibuat"

  Scenario: Successful Profile Update Without Changing Password
    Given a user account named "Aulia Rizky" exists in the list
    When Admin clicks the edit gear icon for "Aulia Rizky"
    And Admin updates the profile data to "Rizky Updated" but leaves the password field empty
    And Admin clicks the "Simpan" button
    Then The profile should be successfully updated without changing the old password

  Scenario: Successful Account Deletion (Self-Cleaning)
    Given a user account named "Aulia Rizky" exists in the list
    When Admin clicks the red delete trash icon on the user row
    And Admin confirms the deletion on the modal popup
    Then The account should be permanently removed from the user list

  Scenario: Successful Quick Cage Creation via Shortcut
    Given a user account named "Rizky Laksmitha" exists in the list
    When Admin clicks the green "+" shortcut button on "Rizky Laksmitha" row
    And Admin fills the cage form with name "Kandang UGM", location "Sleman", population "5000", and size "120"
    And Admin clicks the "Tambah Kandang" button on cage modal
    Then The modal should close
    And Admin should see a success alert message "Kandang baru berhasil ditambahkan"