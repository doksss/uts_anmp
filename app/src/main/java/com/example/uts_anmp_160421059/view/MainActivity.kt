package com.example.uts_anmp_160421059.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.uts_anmp_160421059.R
import com.example.uts_anmp_160421059.databinding.ActivityMainBinding
import com.example.uts_anmp_160421059.model.Game
import com.example.uts_anmp_160421059.model.Paragraph
import com.example.uts_anmp_160421059.viewmodel.DetailViewModel
import com.example.uts_anmp_160421059.viewmodel.ListGameViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    internal lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: ListGameViewModel
    private lateinit var viewmodelparagaf: DetailViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =ActivityMainBinding.inflate(layoutInflater)

//        setContentView(R.layout.activity_main)
        setContentView(binding.root)
        navController = (supportFragmentManager.findFragmentById(R.id.hostFragment) as
                NavHostFragment).navController
        NavigationUI.setupActionBarWithNavController(this,navController,binding.drawerLayout)
        NavigationUI.setupWithNavController(binding.navView,navController)
        binding.bottomNav.setupWithNavController(navController)
        binding.bottomNav.visibility = View.GONE
        binding.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)


        var indeks = 0
        viewModel = ViewModelProvider(this).get(ListGameViewModel::class.java)
        viewmodelparagaf = ViewModelProvider(this).get(DetailViewModel::class.java)

        if(indeks==0){
            val game = Game("Review Game Terbaru: Cyberpunk 2077", "Ulasan tentang salah satu game paling dinanti tahun ini",
                "@Doksss", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTRRR2sNJOIpkZpG6rlWhrHkUpwbbo2I7GWeg&s")
            viewModel.addGame(game)
            val game2 = Game("Tips & Trik untuk Pemain Pemula di Valorant", "Panduan untuk memulai dan meningkatkan permainan Anda di Valorant",
                "@Zampy", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSt6xbwN8mk7zeJGRkH7yc5z8PhdRxLPUYXBw&s")
            viewModel.addGame(game2)

            val paragraf = listOf(
                Paragraph("2","Pilih agent yang sesuai dengan gaya bermain", "Agent dalam Valorant adalah karakter yang dapat dipilih oleh pemain untuk dimainkan. Setiap agen memiliki kemampuan dan role berbeda-beda. Valorant memiliki 18 agent. Pilihlah agent yang sesuai dengan gaya bermainmu agar kamu bisa lebih efektif dalam bermain. Contoh role Agent di Valorant adalah Duelist yang berfokus untuk menyerang dan membunuh. Kemudian, ada Controller yang berfokus pada mengontrol lawan. Ada juga Sentinel yang berfokus pada pertahanan."),
                Paragraph("2","Pelajari map dengan baik", "Salah satu yang harus dikuasai agar bisa memenangkan pertempuran di Valorant adalah menguasai dan menghafal serial detail map. Pahami letak-letak choke point serta spot yang bagus untuk mengintai sekaligus area-area yang sering digunakan musuh untuk bersembunyi. Penguasaan map akan meningkat seiring bertambahnya jam bermain. Jadi, sering-seringlah bermain dan jangan ragu untuk terus mempelajari karakteristik setiap map."),
                Paragraph("1", "Selamat Datang di Night City!", "Jika kita harus berbicara soal salah satu aspek terbaik yang berhasil dieksekusi manis oleh CD Projekt Red dengan Cyberpunk 2077, maka keberhasilan mereka untuk membangun dan menyajikan Night City yang begitu memesona adalah salah satu yang pantas untuk diapresiasi. Kita tentu saja tidak hanya bicara dari sisi presentasi visual saja, bagaimana gedung tinggi megah berbaris di banyak sudut dengan iklan-iklan terang-benderang yang terus menuntut Anda untuk mengkonsumsi lebih banyak produk"),
                Paragraph("1","Lebih ke Aksi Daripada RPG", "Ketika Cyberpunk 2077 diperkenalkan kepada publik, memang ada kesan bahwa CD Projekt Red hendak mengindentifikasikan proyek ambisius mereka ini sebagai game action RPG. Dimana Anda akan bergerak dan berperan aktif saat bertarung, baik saat menggunakan senjata api ataupun senjata melee, sembari menikmati elemen-elemen RPG yang menyeruak di beragam sudut. Namun seiring dengan progress permainan, Anda yang selama ini menyenangi game RPG barat sepertinya akan jatuh ke dalam satu kesimpulan yang sama"),
                Paragraph("1", "Penuh Masalah Teknis", "Di tengah situasi pertarungan yang genting, tidak bisa mengeluarkan senjata tanpa alasan yang jelas adalah resep kematian. Kami juga sempat bertemu dengan situasi dimana V, tanpa alasan yang jelas, terus terjengkal dan terjatuh di lokasi yang sama berulang-ulang walaupun kami sama sekali tidak melompat. Butuh menunggu sekitar 3-4 menit lewat beberapa kali animasi terjengkal untuk melewati fase yang tidak jelas pemicunya ini.")
            )

            for(para in paragraf){
                viewmodelparagaf.addParagraf(para)
            }
            indeks++
        }



//        //android back button
//        navController = (supportFragmentManager.findFragmentById(R.id.hostFragment)as NavHostFragment).navController
//        NavigationUI.setupActionBarWithNavController(this,navController)
    }

//    //android back button
        override fun onSupportNavigateUp(): Boolean {
        //artinysa bisa memunculkan drawer atau memunculkan backbutton di kiri atas
//        return NavigationUI.navigateUp(navController,binding.drawerLayout) || super.onSupportNavigateUp()
        return NavigationUI.navigateUp(navController,binding.drawerLayout) || super.onSupportNavigateUp()
}
}