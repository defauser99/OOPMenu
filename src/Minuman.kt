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