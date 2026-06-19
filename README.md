# BroiLink Automation Testing

## Praktikum Pengujian Perangkat Lunak

### Kelompok

| Nama                        | NIM                |
| --------------------------- | ------------------ |
| Mardhika Murni Pramestika   | 24/533524/SV/23914 |
| Rizky Laksmitha             | 24/543122/SV/25155 |
| Muhammad Arief Andriansyah  | 24/545740/SV/25748 |
| Yafi Nuqman Elianto         | 24/540133/SV/24782 |

---

# Deskripsi SUT (System Under Test)

BroiLink merupakan aplikasi berbasis web yang digunakan untuk membantu pengelolaan peternakan ayam secara digital. Sistem ini menyediakan berbagai fitur untuk mendukung proses operasional peternakan, mulai dari pengelolaan data kandang, monitoring kondisi lingkungan kandang melalui sensor IoT, pengelolaan pengguna, hingga pencatatan aktivitas peternakan.

Sistem BroiLink memiliki tiga jenis pengguna (*role*), yaitu Admin, Owner, dan Peternak. Setiap *role* memiliki hak akses dan fitur yang berbeda sesuai dengan tugas dan tanggung jawabnya.

* **Admin** bertugas mengelola data pengguna, data kandang, konfigurasi sistem, serta melakukan pengawasan terhadap seluruh aktivitas yang terjadi pada sistem.
* **Owner** bertugas melakukan monitoring kondisi peternakan melalui dashboard monitoring, melihat laporan operasional, serta memantau data sensor yang dikirimkan oleh perangkat IoT.
* **Peternak** bertugas melakukan pencatatan aktivitas harian, menginput hasil kerja, serta mengelola informasi yang berkaitan dengan operasional kandang.

Pada proyek ini dilakukan pengujian otomatis (*Automation Testing*) menggunakan Selenium WebDriver, Cucumber, dan Page Object Model (POM) untuk memastikan setiap fitur pada ketiga *role* tersebut dapat berjalan sesuai kebutuhan pengguna, menghasilkan keluaran yang benar, serta meminimalkan kemungkinan terjadinya bug pada sistem.

---

# Tujuan Pengujian

Tujuan dari pengujian ini adalah:

* Memastikan fitur pada aplikasi berjalan sesuai kebutuhan pengguna.
* Mengidentifikasi bug atau kesalahan pada sistem.
* Mengurangi *human error* dalam proses pengujian.
* Mempermudah *regression testing*.
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
BDD diterapkan menggunakan Cucumber dan Gherkin Syntax agar skenario pengujian lebih mudah dipahami oleh seluruh anggota tim maupun pemangku kepentingan.

Contoh skenario pengujian menggunakan Gherkin:
```gherkin
Scenario: Login berhasil
Given pengguna membuka halaman login
When pengguna memasukkan email dan password valid
Then pengguna berhasil login
```

### Page Object Model (POM)
POM digunakan untuk memisahkan *locator element* dan *action* pada setiap halaman sehingga kode lebih terstruktur dan mudah dikelola.

### Equivalence Partitioning (EP) & Boundary Value Analysis (BVA)
Digunakan untuk memvalidasi performa input formulir (baik data valid, invalid, maupun nilai batas ekstrim).

---

# Pembagian Tugas Kelompok

## Mardhika Murni Pramestika
* **Role/Modul yang Dikerjakan:** **Admin** (Landing Page, Login, Dashboard Admin, Manajemen Kandang)
* **Tanggung Jawab:** Pembuatan Test Case, Feature File (BDD), Step Definition, Implementasi POM & Selenium WebDriver, Pengujian EP/BVA, Bug Reporting, & Automation Report.

## Rizky Laksmitha
* **Role/Modul yang Dikerjakan:** **Admin** (User Management, IoT Configuration, Report History)
* **Tanggung Jawab:** Pembuatan Test Case, Feature File (BDD), Step Definition, Implementasi POM & Selenium WebDriver, Pengujian EP/BVA, Bug Reporting, & Automation Report.

## Muhammad Arief Andriansyah
* **Role/Modul yang Dikerjakan:** **Owner** (Monitoring Sensor, Tampilan Card Monitoring, Grafik Monitoring)
* **Tanggung Jawab:** Pembuatan Test Case, Feature File (BDD), Step Definition, Implementasi POM & Selenium WebDriver, Pengujian EP/BVA, Bug Reporting, & Automation Report.

## Yafi Nuqman Elianto
* **Role/Modul yang Dikerjakan:** **Peternak** (Dashboard Peternak, Input Hasil Kerja, Profil Pengguna)
* **Tanggung Jawab:** Pembuatan Test Case, Feature File (BDD), Step Definition, Implementasi POM & Selenium WebDriver, Pengujian EP/BVA, Bug Reporting, & Automation Report.

---

# Dokumentasi & Laporan Pengujian

Berikut dokumen analisis pengujian dan laporan temuan bug pada pengujian ini:

* 📑 [Tautan Google Sheets - Test Case & Bug Report](https://docs.google.com/spreadsheets/d/1YLFGluAXu2HIB362d1b9_PWFZcC2GmHNAfK4Rjs_tPg/edit?usp=sharing)

---

# Struktur Repository

```plaintext
broilink-automation-testing
.
├── .gitignore
├── pom.xml
├── README.md
└── src
    ├── main
    │   └── java
    │       ├── core
    │       │   └── Locator.java
    │       ├── org.example
    │       │   └── Main.java
    │       └── pages
    │           ├── AdminDashboardPage.java
    │           ├── BasePage.java
    │           ├── InputHasilKerjaPage.java
    │           ├── IoTConfigurationPage.java
    │           ├── LandingPage.java
    │           ├── LoginPage.java
    │           ├── ManagementKandangPage.java
    │           ├── MonitoringPage.java
    │           ├── OwnerDashboardPage.java
    │           ├── PeternakProfilePage.java
    │           ├── ReportHistoryPage.java
    │           ├── SimulationPage.java
    │           └── UserManagementPage.java
    └── test
        ├── java
        │   ├── runner
        │   │   └── TestRunner.java
        │   ├── stepDef
        │   │   ├── AdminDashboardStepDef.java
        │   │   ├── EditProfileStepDef.java
        │   │   ├── Hooks.java
        │   │   ├── InputLaporanHarianStepDef.java
        │   │   ├── IoTConfigurationStepDef.java
        │   │   ├── LandingPageStepDef.java
        │   │   ├── LoginStepDef.java
        │   │   ├── ManagementKandangStepDef.java
        │   │   ├── OwnerPeternakStepDef.java
        │   │   ├── ReportHistoryStepDef.java
        │   │   └── UserManagementStepDef.java
        │   └── utils
        │       └── DriverManager.java
        └── resources
            └── features
                ├── AdminDashboard.feature
                ├── edit_profile.feature
                ├── input_laporan_harian.feature
                ├── IoTConfiguration.feature
                ├── landing_page.feature
                ├── login.feature
                ├── ManajemenKandang.feature
                ├── owner_monitoring.feature
                ├── ReportHistory.feature
                └── UserManagement.feature
```

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

# Cara Mengakses Laporan Hasil Uji (Cucumber Report)

Setelah rangkaian pengujian selesai dijalankan secara lokal, Cucumber akan menghasilkan laporan interaktif berbasis HTML. Laporan visualisasi grafik Passed/Failed pengujian dapat diakses melalui langkah berikut:

1. Buka File Explorer pada komputer kalian dan masuk ke direktori proyek utama.
2. Arahkan ke folder target keluaran: target/
3. Cari berkas bernama cucumber-report.html.
4. Klik kanan berkas tersebut -> Open With -> Pilih Web Browser favorit kamu (Chrome/Edge/Firefox).

---

# Mata Kuliah

* Praktikum Pengujian Perangkat Lunak
* Program Studi Sarjana Terapan Teknologi Rekayasa Perangkat Lunak
* Sekolah Vokasi Universitas Gadjah Mada

---

# Lisensi
Repository ini dibuat untuk keperluan akademik dan pembelajaran pada mata kuliah Praktikum Pengujian Perangkat Lunak Universitas Gadjah Mada.