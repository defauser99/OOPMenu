/**
 * Program ini dibuat untuk mengupdate menu restoran dan mencatat transaksi setiap pembeli.
 * Setiap penjual bisa mengupdate menunya pada listPaket dan listMenu.
 * Setiap pembeli bisa bertransaksi dengan memilih menu dan jumlah pembelian.
 * Program ini mendukung kondisi dimana Pembeli dapat berulang kali menambahkan pembeliannya.
 * Setiap pembeli yang menjadi member tidak dikenakan PPN 10%.
 * Setiap member yang membeli ala carte makanan mendapatkan diskon 20%.
 * Program ini dibuat oleh: Defa Ihsan Ramadhan
 *
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