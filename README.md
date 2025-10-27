#  Sistem Pemesanan Online Shop (Java)

Program ini merupakan hasil refactoring dari Modul 2, yang mengimplementasikan **Object-Oriented Programming (OOP)** dengan berbagai teknik refactoring seperti:

-  **Introduce Constant** — Menambahkan konstanta `DISCOUNT_RATE`
-  **Extract Method** — Memisahkan logika perhitungan total harga ke method terpisah
-  **Introduce Parameter Object** — Menggabungkan `Item` dan `Customer` ke dalam `OrderInfo`
-  **Extract Interface** — Membuat interface `OrderSystem`
- **Rename Method** — Mengubah nama method agar lebih bermakna (`displayOrder`)
-  **Move Method** — Memindahkan logika agar lebih sesuai tanggung jawab kelas

---

##  Struktur Kelas

| Kelas / Record | Deskripsi |
|----------------|------------|
| **Item** | Menyimpan data barang seperti nama, harga, dan jumlah |
| **Customer** | Menyimpan data pelanggan (nama, alamat, nomor telepon) |
| **OrderInfo** | Menggabungkan data `Item` dan `Customer` |
| **OrderSystem** | Interface dasar untuk sistem pemesanan |
| **OnlineShop** | Implementasi `OrderSystem`, menampilkan informasi pesanan |
| **SistemPemesananOnlineShop** | Kelas utama yang menjalankan program |

---
