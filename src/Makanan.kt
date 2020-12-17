import kotlin.math.roundToInt

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