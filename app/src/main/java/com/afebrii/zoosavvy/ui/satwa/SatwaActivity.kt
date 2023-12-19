package com.afebrii.zoosavvy.ui.satwa

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.afebrii.zoosavvy.R
import com.afebrii.zoosavvy.ui.main.MainActivity
import com.afebrii.zoosavvy.ui.news.NewsActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class SatwaActivity : AppCompatActivity() {

    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var satwaRecyclerView: RecyclerView
    private lateinit var satwaArrayList: ArrayList<Satwa>
    lateinit var imageId: Array<Int>
    lateinit var nama: Array<String>
    lateinit var namaLatin: Array<String>
    lateinit var deskripsi: Array<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_satwa)

        imageId = arrayOf(
            R.drawable.satwa_a,
            R.drawable.satwa_b,
            R.drawable.satwa_c,
            R.drawable.satwa_d,
            R.drawable.satwa_e,
        )

        nama = arrayOf(
            "Harimau",
            "Badak Jawa",
            "Beruang Madu",
            "Rusa Tutul",
            "Kudanil"
        )

        namaLatin = arrayOf(
            "Panthera tigris sumatrae",
            "Rhinoceros sondaicus",
            "Helarctos malayanus",
            "Axis axis",
            "Tapirus indicus"
        )

        deskripsi = arrayOf(
            "Harimau Sumatera, atau Panthera tigris sumatrae, adalah salah satu subspesies harimau yang secara eksklusif dapat ditemui di pulau Sumatera, Indonesia. Harimau ini dikenal karena ukurannya yang lebih kecil dibandingkan dengan beberapa subspesies harimau lainnya, tetapi kecantikan dan keanggunannya tetap memukau. Dengan bulu berwarna kuning kecokelatan yang dihiasi oleh garis-garis hitam tebal, harimau Sumatera memiliki pola corak unik yang membedakannya dari harimau lain. Keberadaan mereka kini terancam oleh hilangnya habitat alaminya, perburuan ilegal, dan konflik dengan manusia. Sebagai spesies yang terancam punah, upaya konservasi yang serius diperlukan untuk melindungi harimau Sumatera dan memastikan kelangsungan hidup mereka di alam liar. Keberadaan mereka tidak hanya penting bagi ekosistem, tetapi juga sebagai simbol keanekaragaman hayati Indonesia yang perlu dilestarikan untuk generasi mendatang.",
            "Badak Jawa, atau Rhinoceros sondaicus, merupakan salah satu spesies badak yang hanya dapat ditemukan di pulau Jawa, Indonesia. Badak ini dikenal karena tubuhnya yang besar dan berat, dilapisi oleh kulit yang tebal dan lipatan-lipatan yang memberikan perlindungan tambahan. Ciri khasnya adalah tanduk tunggal yang relatif kecil, dibandingkan dengan badak lainnya. Bulu badak Jawa umumnya pendek dan jarang, memberikan tampilan tubuh yang hampir gundul. Keberadaan badak Jawa saat ini sangat terancam akibat hilangnya habitat alaminya, perburuan ilegal, dan konflik dengan manusia. Populasinya telah menurun secara signifikan, membuatnya menjadi salah satu spesies badak yang paling terancam punah di dunia. Upaya konservasi yang serius diperlukan untuk melindungi badak Jawa dan memastikan kelangsungan hidup mereka. Badak Jawa memiliki peran penting dalam menjaga keseimbangan ekosistem serta mencerminkan keanekaragaman hayati Indonesia yang perlu dilestarikan demi keberlanjutan lingkungan hidup.",
            "Beruang Madu, atau Helarctos malayanus, merupakan salah satu spesies beruang kecil yang dapat ditemukan di Asia Tenggara, termasuk di Indonesia. Beruang ini dikenal dengan sebutan \"madu\" karena makanan utamanya adalah madu lebah dan sarang lebah. Beruang Madu memiliki ukuran tubuh yang relatif kecil, dengan bulu berwarna hitam atau coklat gelap yang tebal dan lebat. Ciri khas beruang madu meliputi telinga yang bulat dan kecil, mata besar, dan dada yang memiliki bercak atau pola khas yang membedakannya dari spesies beruang lainnya. Meskipun terlihat imut, beruang madu memiliki cakar yang kuat dan gigi taring yang tajam sebagai adaptasi untuk mendapatkan makanan di alam liar.",
            "Rusa Tutul, atau Axis axis, adalah salah satu spesies rusa yang dapat ditemui di berbagai wilayah Asia, termasuk di Indonesia. Nama \"tutul\" merujuk pada pola corak bulu mereka yang mirip dengan tutul, yang terdiri dari bintik-bintik putih di atas bulu berwarna merah cokelat. Rusa tutul memiliki tubuh yang agak ramping dengan kaki yang tinggi, dan rata-rata, ukuran tubuhnya lebih kecil dibandingkan dengan beberapa spesies rusa lainnya.",
            "Kudanil, atau Tapirus indicus, adalah mamalia herbivora besar yang dapat ditemukan di wilayah Asia Tenggara, termasuk di Indonesia. Kudanil memiliki penampilan yang khas, dengan tubuh yang besar, kaki pendek, dan moncong yang panjang. Kulit kudanil dilapisi oleh bulu yang kasar dan berwarna cokelat tua hingga hitam. Yang membedakan kudanil dari hewan-hewan lainnya adalah moncongnya yang menyerupai beluncas atau terompet, yang bermanfaat untuk meraih makanan, seperti daun, buah-buahan, dan tunas tanaman."
        )

        satwaRecyclerView = findViewById(R.id.recycler_view_satwa)
        satwaRecyclerView.layoutManager = LinearLayoutManager(this)
        satwaRecyclerView.setHasFixedSize(true)
        satwaArrayList = arrayListOf<Satwa>()
        getSatwaData()

        bottomNavigationView = findViewById(R.id.navigation_bottom_view)

        bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.bottom_home -> {
                    val i = Intent (this, MainActivity::class.java)
                    startActivity(i)
                    true
                }
                R.id.bottom_satwa -> {
                    true
                }
                R.id.bottom_news -> {
                    val i = Intent(this, NewsActivity::class.java)
                    startActivity(i)
                    finish()
                    true
                }
                else -> true
            }
        }
    }

    private fun getSatwaData() {
        for (i in imageId.indices) {
            val satwas =
                Satwa(imageId[i], nama[i], namaLatin[i], deskripsi[i])
            satwaArrayList.add(satwas)
        }

        satwaRecyclerView.adapter = SatwaAdapter(satwaArrayList)
    }
}