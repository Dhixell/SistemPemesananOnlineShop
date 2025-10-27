import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Program Sistem Pemesanan Online Shop (Versi Lanjutan)
 * <p>
 * Program ini telah di-refactor dan ditambahkan beberapa fitur baru:
 * 1️⃣ Simpan nota ke file .txt
 * 2️⃣ Perhitungan pajak dan diskon otomatis
 * 3️⃣ Menampilkan tanggal dan waktu transaksi
 * 4️⃣ Ringkasan total keseluruhan
 *
 * @author
 * @version 2.0
 * @since 2025-10-26
 */

// Record digunakan untuk menyimpan data sederhana
/**
 * Kelas record Item digunakan untuk merepresentasikan data produk.
 * Memuat informasi nama, harga, dan jumlah pesanan dari sebuah barang.
 */
record Item(String name, double price, int quantity) {

    /** Konstanta diskon untuk setiap pembelian */
    public static final double DISCOUNT_RATE = 0.1;
    public static final double TAX_RATE = 0.11; // Pajak 11%

    /**
     * Menghitung total harga sebelum diskon dan pajak.
     *
     * @return total harga (price × quantity)
     */
    public double calculateTotal() {
        return price * quantity;
    }

    /**
     * Menghitung total harga setelah diskon diterapkan.
     *
     * @return total harga setelah diskon 10%
     */
    public double calculateDiscountedTotal() {
        return calculateTotal() - (calculateTotal() * DISCOUNT_RATE);
    }

    /**
     * Menghitung total harga setelah pajak diterapkan.
     *
     * @return total harga setelah pajak 11%
     */
    public double calculateTotalWithTax() {
        double discounted = calculateDiscountedTotal();
        return discounted + (discounted * TAX_RATE);
    }
}

/**
 * Record Customer digunakan untuk menyimpan data pelanggan.
 */
record Customer(String name, String address, String phone) {
}

/**
 * Kelas OrderInfo digunakan untuk menggabungkan data Item dan Customer
 * sebagai satu objek pesanan (parameter object).
 */
class OrderInfo {
    Item item;
    Customer customer;

    /**
     * Konstruktor OrderInfo.
     *
     * @param item     objek Item yang dipesan
     * @param customer objek Customer yang melakukan pemesanan
     */
    public OrderInfo(Item item, Customer customer) {
        this.item = item;
        this.customer = customer;
    }
}

/**
 * Interface OrderSystem digunakan sebagai abstraksi sistem pemesanan.
 */
interface OrderSystem {
    void displayOrder();

    void saveToFile();
}

/**
 * Kelas OnlineShop mengimplementasikan OrderSystem dan berfungsi
 * menampilkan serta menyimpan informasi pesanan yang dilakukan pelanggan.
 */
class OnlineShop implements OrderSystem {
    private final OrderInfo orderInfo;
    private final LocalDateTime orderDate = LocalDateTime.now();

    /**
     * Konstruktor OnlineShop.
     *
     * @param orderInfo objek OrderInfo yang berisi data pesanan
     */
    public OnlineShop(OrderInfo orderInfo) {
        this.orderInfo = orderInfo;
    }

    /**
     * Menampilkan informasi pesanan pelanggan secara lengkap ke layar.
     */
    @Override
    public void displayOrder() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        System.out.println("=== INFORMASI PESANAN ===");
        System.out.println("Tanggal Pemesanan : " + orderDate.format(formatter));
        System.out.println("Nama Customer     : " + orderInfo.customer.name());
        System.out.println("Alamat            : " + orderInfo.customer.address());
        System.out.println("Nomor Telepon     : " + orderInfo.customer.phone());
        System.out.println("Barang            : " + orderInfo.item.name());
        System.out.println("Harga Satuan      : Rp " + orderInfo.item.price());
        System.out.println("Jumlah            : " + orderInfo.item.quantity());
        System.out.println("-------------------------------");
        System.out.println("Subtotal          : Rp " + orderInfo.item.calculateTotal());
        System.out.println("Diskon (10%)      : Rp " + (orderInfo.item.calculateTotal() * Item.DISCOUNT_RATE));
        System.out.println("Pajak (11%)       : Rp " + (orderInfo.item.calculateDiscountedTotal() * Item.TAX_RATE));
        System.out.println("Total Bayar       : Rp " + orderInfo.item.calculateTotalWithTax());
        System.out.println("=================================");
    }

    /**
     * Menyimpan nota pemesanan ke dalam file teks.
     */
    @Override
    public void saveToFile() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String filename = "nota_" + orderInfo.customer.name().toLowerCase().replace(" ", "_") + ".txt";

        try (FileWriter writer = new FileWriter(filename)) {
            writer.write("=== NOTA PEMESANAN ONLINE SHOP ===\n");
            writer.write("Tanggal : " + orderDate.format(formatter) + "\n");
            writer.write("Nama Customer : " + orderInfo.customer.name() + "\n");
            writer.write("Alamat : " + orderInfo.customer.address() + "\n");
            writer.write("Nomor : " + orderInfo.customer.phone() + "\n");
            writer.write("-------------------------------\n");
            writer.write("Barang : " + orderInfo.item.name() + "\n");
            writer.write("Harga Satuan : Rp " + orderInfo.item.price() + "\n");
            writer.write("Jumlah : " + orderInfo.item.quantity() + "\n");
            writer.write("Subtotal : Rp " + orderInfo.item.calculateTotal() + "\n");
            writer.write("Diskon (10%) : Rp " + (orderInfo.item.calculateTotal() * Item.DISCOUNT_RATE) + "\n");
            writer.write("Pajak (11%) : Rp " + (orderInfo.item.calculateDiscountedTotal() * Item.TAX_RATE) + "\n");
            writer.write("Total Bayar : Rp " + orderInfo.item.calculateTotalWithTax() + "\n");
            writer.write("===============================\n");
            writer.write("Terima kasih telah berbelanja di Online Shop kami!\n");

            System.out.println("\n✅ Nota berhasil disimpan ke file: " + filename);
        } catch (IOException e) {
            System.out.println("❌ Terjadi kesalahan saat menyimpan nota: " + e.getMessage());
        }
    }
}

/**
 * Kelas utama SistemPemesananOnlineShop yang berfungsi menjalankan program.
 */
public class SistemPemesananOnlineShop {

    /**
     * Method utama (main) yang menjalankan program pemesanan.
     *
     * @param args argumen dari command line (tidak digunakan)
     */
    public static void main(String[] args) {
        Item item = new Item("Kemeja Batik", 200000, 3);
        Customer c = new Customer("Andi", "Jl. Merdeka No. 5", "08123456789");
        OrderInfo order = new OrderInfo(item, c);

        OnlineShop shop = new OnlineShop(order);
        shop.displayOrder();
        shop.saveToFile(); // 🔹 fitur tambahan: simpan nota ke file
    }
}
