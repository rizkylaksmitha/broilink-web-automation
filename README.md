# BroiLink Automation Testing

## Praktikum Pengujian Perangkat Lunak

### Kelompok

| Nama                       | NIM                |
| -------------------------- | ------------------ |
| Mardhika Murni Pramestika  | 24/533524/SV/23914 |
| Rizky Laksmitha            | 24/543122/SV/25155 |
| Muhammad Arief Andriansyah | 24/545740/SV/25748 |
| Yafi Nuqman Elianto        | 24/540133/SV/24782 |

---

# Deskripsi SUT (System Under Test)

BroiLink merupakan aplikasi berbasis web yang digunakan untuk membantu pengelolaan peternakan ayam secara digital. Sistem ini menyediakan berbagai fitur untuk mengelola pengguna, kandang, monitoring sensor IoT, laporan operasional, serta dashboard monitoring yang dapat digunakan oleh admin maupun owner.

Pada proyek ini dilakukan pengujian otomatis (Automation Testing) untuk memastikan setiap fitur utama dapat berjalan sesuai kebutuhan pengguna dan menghasilkan keluaran yang benar.

---

# Tujuan Pengujian

Tujuan dari pengujian ini adalah:

* Memastikan fitur pada aplikasi berjalan sesuai kebutuhan pengguna.
* Mengidentifikasi bug atau kesalahan pada sistem.
* Mengurangi human error dalam proses pengujian.
* Mempermudah regression testing.
* Menghasilkan dokumentasi pengujian yang terstruktur.

---

# Teknologi yang Digunakan

* Java
* Selenium WebDriver
* Cucumber
* JUnit
* Maven
* Page Object Model (POM)
* Behavior Driven Development (BDD)

---

# Metode Pengujian

Metode yang digunakan dalam proyek ini meliputi:

### Automation Testing

Pengujian dilakukan secara otomatis menggunakan Selenium WebDriver.

### Behavior Driven Development (BDD)

BDD diterapkan menggunakan Cucumber dan Gherkin Syntax agar skenario pengujian lebih mudah dipahami.

Contoh:

```gherkin
Scenario: Login berhasil
  Given pengguna membuka halaman login
  When pengguna memasukkan email dan password valid
  Then pengguna berhasil login
```

### Page Object Model (POM)

POM digunakan untuk memisahkan locator element dan action pada setiap halaman sehingga kode lebih terstruktur dan mudah dikelola.

### Equivalence Partitioning (EP)

Digunakan untuk menguji input valid dan invalid.

### Boundary Value Analysis (BVA)

Digunakan untuk menguji batas minimum dan maksimum nilai input.

---

# Test Suite

Pengujian dilakukan pada beberapa modul utama aplikasi BroiLink sebagai berikut:

1. Landing Page
2. Login
3. Dashboard Admin
4. Manajemen Kandang
5. Manajemen Pengguna
6. Konfigurasi IoT
7. Riwayat Laporan
8. Monitoring Sensor
9. Tampilan Card Monitoring
10. Grafik Monitoring
11. Dashboard Peternak
12. Input Hasil Kerja
13. Profil Pengguna

Test suite dibuat menggunakan pendekatan Functional Testing, Equivalence Partitioning (EP), dan Boundary Value Analysis (BVA).

---

# Pembagian Tugas Kelompok

## Mardhika Murni Pramestika

Modul yang dikerjakan:

* Landing Page
* Login
* Dashboard Admin
* Manajemen Kandang

Tanggung jawab:

Pembuatan Test Case
Pembuatan Feature File (BDD)
Pembuatan Step Definition
Implementasi Page Object Model (POM)
Implementasi Selenium WebDriver
Pengujian Equivalence Partitioning (EP)
Pengujian Boundary Value Analysis (BVA)
Bug Reporting
Automation Report

---

## Rizky Laksmitha

Modul yang dikerjakan:

* Manajemen Pengguna
* Konfigurasi IoT
* Riwayat Laporan

Tanggung jawab:

Pembuatan Test Case
Pembuatan Feature File (BDD)
Pembuatan Step Definition
Implementasi Page Object Model (POM)
Implementasi Selenium WebDriver
Pengujian Equivalence Partitioning (EP)
Pengujian Boundary Value Analysis (BVA)
Bug Reporting
Automation Report

---

## Muhammad Arief Andriansyah

Modul yang dikerjakan:

* Monitoring Sensor
* Tampilan Card Monitoring
* Grafik Monitoring

Tanggung jawab:

Pembuatan Test Case
Pembuatan Feature File (BDD)
Pembuatan Step Definition
Implementasi Page Object Model (POM)
Implementasi Selenium WebDriver
Pengujian Equivalence Partitioning (EP)
Pengujian Boundary Value Analysis (BVA)
Bug Reporting
Automation Report

---

## Yafi Nuqman Elianto

Modul yang Dikerjakan:

Dashboard Peternak
Input Hasil Kerja
Profil Pengguna

Tanggung Jawab:

Pembuatan Test Case
Pembuatan Feature File (BDD)
Pembuatan Step Definition
Implementasi Page Object Model (POM)
Implementasi Selenium WebDriver
Pengujian Equivalence Partitioning (EP)
Pengujian Boundary Value Analysis (BVA)
Bug Reporting
Automation Report

---

# Struktur Repository

```plaintext
broilink-automation-testing
│
├── README.md
├── pom.xml
│
├── docs
│   ├── Laporan_Akhir.pdf
│   ├── Pembagian_Tugas.pdf
│   └── Dokumentasi_Pengujian.pdf
│
├── test-case
│   ├── Landing_Page_Test_Case.xlsx
│   ├── Login_Test_Case.xlsx
│   ├── Dashboard_Admin_Test_Case.xlsx
│   ├── Management_Kandang_Test_Case.xlsx
│   ├── Management_Pengguna_Test_Case.xlsx
│   ├── Konfigurasi_IoT_Test_Case.xlsx
│   ├── Riwayat_Laporan_Test_Case.xlsx
│   ├── Monitoring_Sensor_Test_Case.xlsx
│   ├── Dashboard_Peternak_Test_Case.xlsx
│   └── Master_Test_Suite.xlsx
│
├── bug-report
│   ├── Bug_Report.pdf
│   ├── Bug_Login.pdf
│   ├── Bug_Management_Kandang.pdf
│   └── Bug_Monitoring.pdf
│
├── automation-report
│   ├── cucumber-report.html
│   ├── extent-report.html
│   └── screenshots
│       ├── landing-page.png
│       ├── login-success.png
│       ├── login-failed.png
│       └── dashboard-admin.png
│
├── src
│   ├── main
│   │   └── pages
│   │       ├── BasePage.java
│   │       ├── LandingPage.java
│   │       ├── LoginPage.java
│   │       ├── AdminDashboardPage.java
│   │       ├── ManagementKandangPage.java
│   │       ├── ManagementPenggunaPage.java
│   │       ├── KonfigurasiIoTPage.java
│   │       ├── RiwayatLaporanPage.java
│   │       ├── MonitoringSensorPage.java
│   │       ├── DashboardPeternakPage.java
│   │       ├── InputHasilKerjaPage.java
│   │       └── ProfilPage.java
│   │
│   └── test
│       ├── java
│       │   ├── runner
│       │   │   └── TestRunner.java
│       │   │
│       │   ├── stepDef
│       │   │   ├── Hooks.java
│       │   │   ├── LandingPageStepDef.java
│       │   │   ├── LoginStepDef.java
│       │   │   ├── AdminDashboardStepDef.java
│       │   │   ├── ManagementKandangStepDef.java
│       │   │   ├── ManagementPenggunaStepDef.java
│       │   │   ├── KonfigurasiIoTStepDef.java
│       │   │   ├── RiwayatLaporanStepDef.java
│       │   │   ├── MonitoringSensorStepDef.java
│       │   │   ├── DashboardPeternakStepDef.java
│       │   │   ├── InputHasilKerjaStepDef.java
│       │   │   └── ProfilStepDef.java
│       │   │
│       │   └── utils
│       │       └── DriverManager.java
│       │
│       └── resources
│           └── features
│               ├── landing_page.feature
│               ├── login.feature
│               ├── admin_dashboard.feature
│               ├── management_kandang.feature
│               ├── management_pengguna.feature
│               ├── konfigurasi_iot.feature
│               ├── riwayat_laporan.feature
│               ├── monitoring_sensor.feature
│               ├── dashboard_peternak.feature
│               ├── input_hasil_kerja.feature
│               └── profil.feature
│
└── target
    ├── cucumber-report.html
    ├── surefire-reports
    └── screenshots
```

---

# Isi Repository

Repository ini berisi:

### 1. Source Code Automation Testing

Berupa implementasi Selenium WebDriver, Cucumber, dan Page Object Model (POM).

### 2. Test Case

Berisi seluruh test suite yang digunakan selama proses pengujian.

### 3. Bug Report

Berisi daftar bug yang ditemukan selama proses pengujian beserta tingkat severity dan deskripsinya.

### 4. Automation Report

Berisi hasil eksekusi automation testing yang dijalankan menggunakan Selenium dan Cucumber.

---

# Cara Menjalankan Project

### Clone Repository

```bash
git clone <repository-url>
```

### Install Dependency

```bash
mvn clean install
```

### Menjalankan Automation Testing

```bash
mvn test
```

atau melalui:

```plaintext
TestRunner.java
```

---

# Artefak yang Dipresentasikan Saat Responsi

Sesuai ketentuan praktikum, artefak yang dipresentasikan meliputi:

1. Test Case
2. Source Code Automation Testing
3. Bug Report

Selain itu setiap anggota kelompok wajib dapat menjalankan kode pengujian yang menjadi tanggung jawabnya masing-masing.

---

# Mata Kuliah

Praktikum Pengujian Perangkat Lunak

Program Studi Sarjana Terapan Teknologi Rekayasa Perangkat Lunak

Sekolah Vokasi Universitas Gadjah Mada

---

# Lisensi

Repository ini dibuat untuk keperluan akademik dan pembelajaran pada mata kuliah Praktikum Pengujian Perangkat Lunak Universitas Gadjah Mada.
