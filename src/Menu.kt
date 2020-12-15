/**
 * Program ini dibuat untuk mengupdate menu restoran dan mencatat transaksi setiap pembeli.
 * Setiap penjual bisa mengupdate menunya pada listPaket dan listMenu.
 * Setiap pembeli bisa bertransaksi dengan memilih menu dan jumlah pembelian.
 * Program ini mendukung kondisi dimana Pembeli dapat berulang kali menambahkan pembeliannya.
 * Setiap pembeli yang menjadi member tidak dikenakan PPN 10%.
 * Setiap member yang membeli ala carte makanan mendapatkan diskon 20%.
 * Program ini dibuat oleh: Defa Ihsan Ramadhan
 */

import kotlin.math.roundToInt

/**
 * Pada fungsi di bawah ini, penjual bisa mengupdate menu restoran.
 * Setiap makanan perlu diinput jumlah kalorinya (kkal).
 * Setiap minuman perlu diinput volumenya (ml).
 */
fun main() {
    val listPaket = mutableListOf(Menu("Cheese Burger + French Fries + Coca-Cola", 50_000),
                                  Menu("Fried Chicken + French Fries + Coca-Cola", 45_000),
                                  Menu("Chicken Nugget + French Fries + Coca-Cola", 52_000))

    val listMenu = mutableListOf(Makanan("Cheese Burger",25_000,310.0),
                                 Makanan("French Fries", 20_000,350.0),
                                 Makanan("Fried Chicken", 18_000, 320.0),
                                 Makanan("Chicken Nugget",26_000,250.0),
                                 Minuman("Coca-Cola",12_500,250.0))

    printMenu(listPaket, listMenu)
    pesanMakan(listPaket, listMenu)
}

// Fungsi di bawah ini mencetak menu pada tampilan awal ketika program ini dijalankan
fun printMenu(listPaket: MutableList<Menu>, listMenu: MutableList<Menu>) {
    println("                   Selamat Datang di Defa's Restaurant")

    repeat(25) {print("=*=")}

    println("\n                      Berikut Adalah Menu Hari Ini")
    println("\n                              Menu Paket:")

    for ((index, paket) in listPaket.withIndex()) {
        println("Kode P${index+1}: Paket ${index+1} ( ${paket.info()} )")
    }

    println("\n                             Menu Ala Carte:")
    for ((index, menu) in listMenu.withIndex()) {
        println("             Kode A${index+1}: ${menu.info()}")
    }

    repeat(25) {print("=*=")}
}

/**
 * Fungsi di bawah ini mencatat seluruh transaksi pembeli dan
 * mengembalikan nilai total transaksi dan jumlah uang kembali.
 */
fun pesanMakan(listPaket: MutableList<Menu>, listMenu: MutableList<Menu>) {
    val menuPilihan = mutableListOf<String?>()
    val jumlahMenu = mutableListOf<Int>()
    var totalHarga = 0
    println("")

    // Kondisi di bawah ini memungkinkan pembeli berulang kali menambahkan menu selama memilih "Y"
    do {
        print("Pilih Kode Menu (Contoh: P1): ")
        menuPilihan.add(readLine()?.toUpperCase())
        print("Masukkan Jumlah Pembelian (Contoh: 1): ")
        jumlahMenu.add(readLine()?.toIntOrNull() ?: 0)
        print("Pilih menu lagi? (Y/N): ")
        val pilihLagi = readLine()?.toUpperCase()
    } while(pilihLagi == "Y")

    /**
     * Setiap pembeli akan ditanyakan status membershipnya,
     * karena perhitungan transaksi member akan berbeda.
     */
    print("Ada kartu member? (Y/N): ")
    val member = readLine()?.toUpperCase()=="Y"

    for ((index, menu) in menuPilihan.withIndex()) {
        for (i in 1..(listPaket+listMenu).size) {
            when(menu) {
                "P${i}" -> run {totalHarga += listPaket[i-1].hitungTotalHarga(jumlahMenu[index],member)}
                "A${i}" -> run {totalHarga += listMenu[i-1].hitungTotalHarga(jumlahMenu[index],member)}
            }
        }
    }
    println("Total harga pembelian anda adalah: Rp.$totalHarga")
    do {
        print("Masukkan uang pembayaran: ")
        val bayar = readLine()?.toIntOrNull() ?: 0
        if (bayar < totalHarga) println("Uang anda kurang!")
        else println("Terimakasih 😊 uang kembalian anda adalah Rp.${bayar - totalHarga}")
    } while (bayar < totalHarga)
}

/**
 * Di bawah ini adalah kelas Menu sebagai blueprint dari kelas Makanan dan Minuman.
 * Setiap menu pasti menampilkan nama menu dan harga menu.
 * Kelas ini juga memiliki fungsi dasar untuk menghitung transaksi dari pembeli.
 */
open class Menu(private val namaMenu: String,
           private val hargaMenu: Int) {

    open fun info(): String {
        return "$namaMenu: Rp.$hargaMenu"
    }

    // Di bawah ini terjadi overloading atau static polymorphism
    private val ppn = 0.1
    private fun hitungTotalHarga(jumlah: Int): Int {
        return ((hargaMenu * jumlah).toDouble() * (1 + ppn)).roundToInt()
    }

    open fun hitungTotalHarga(jumlah: Int, member: Boolean): Int {
        var totalHarga = (hitungTotalHarga(jumlah)).toDouble()
        if (member) {
            totalHarga /= (1+ppn)
        }
        return totalHarga.roundToInt()
    }
}

/**
 * Di bawah ini adalah kelas Makanan sebagai sub-kelas Menu.
 * Pada kelas ini ada opsi tampilan info yang berbeda dari menu.
 * Dimana ada info mengenai jumlah kalori (kkal) dari setiap makanan.
 * Pada kelas ini juga ada perbedaan perhitungan transaksi karena ada diskon 20%.
 */
class Makanan(private val nama: String,
           private val harga: Int,
           private val kalori: Double) : Menu(nama, harga) {

    // Sub-kelas Makanan melakukan overriding method yang ada di kelas Menu
    override fun info(): String{
        return "$nama: Rp.$harga ($kalori kkal)"
    }

    override fun hitungTotalHarga(jumlah: Int, member: Boolean): Int {
        var totalHarga = (super.hitungTotalHarga(jumlah, member)).toDouble()
        val setelahDiskon = 0.8
        if (member) {
            totalHarga *= setelahDiskon
        }
        return totalHarga.roundToInt()
    }
}

/**
 * Di bawah ini adalah kelas Minuman sebagai sub-kelas Menu.
 * Pada kelas ini ada opsi tampilan info yang berbeda dari menu.
 * Dimana ada info mengenai volume (ml) dari setiap minuman.
 */
class Minuman(private val nama: String,
              private val harga: Int,
              private val volume: Double) : Menu(nama, harga) {

    // Sub-kelas Minuman melakukan overriding method yang ada di kelas Menu
    override fun info(): String {
        return "$nama: Rp.$harga ($volume ml)"
    }
}