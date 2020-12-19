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