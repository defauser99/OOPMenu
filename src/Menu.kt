import kotlin.math.roundToInt

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