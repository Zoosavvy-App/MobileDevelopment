package com.afebrii.zoosavvy.ui.news

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.afebrii.zoosavvy.R
import com.afebrii.zoosavvy.ui.main.EventAdapter
import com.afebrii.zoosavvy.ui.main.MainActivity
import com.afebrii.zoosavvy.ui.satwa.SatwaActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class NewsActivity : AppCompatActivity() {

    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var eventRecyclerView: RecyclerView
    private lateinit var eventArrayList: ArrayList<Event>
    lateinit var imageId: Array<Int>
    lateinit var judul: Array<String>
    lateinit var tempat: Array<String>
    lateinit var tanggal: Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)

        imageId = arrayOf(
            R.drawable.news_a,
            R.drawable.news_b,
            R.drawable.news_c,
        )

        judul = arrayOf(
            "Run For Zoo",
            "Dolphin Show",
            "Diversity Day 2023",
        )

        tempat = arrayOf(
            "Kebun Binatang Surabaya",
            "Kebun Binatang Tasikmalaya",
            "Kebun Binatang Jakarta"
        )

        tanggal = arrayOf(
            "Sabtu, 04 November 2023",
            "Jumat, 20 Desember 2023",
            "Senin, 10 Januari 2024",
        )

        eventRecyclerView = findViewById(R.id.recycler_view)
        eventRecyclerView.layoutManager = LinearLayoutManager(this)
        eventRecyclerView.setHasFixedSize(true)
        eventArrayList = arrayListOf<Event>()
        getEventData()

        bottomNavigationView = findViewById(R.id.navigation_bottom_view)

        bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.bottom_home -> {
                    val i = Intent (this, MainActivity::class.java)
                    startActivity(i)
                    finish()
                    return@setOnItemSelectedListener true
                }
                R.id.bottom_satwa -> {
                    val i = Intent(this, SatwaActivity::class.java)
                    startActivity(i)
                    finish()
                    return@setOnItemSelectedListener true
                }
                R.id.bottom_news -> {
                    return@setOnItemSelectedListener true
                }
                else -> return@setOnItemSelectedListener true
            }
        }
    }

    private fun getEventData() {
        for (i in imageId.indices) {
            val events =
                Event(imageId[i], judul[i], tempat[i], tanggal[i])
            eventArrayList.add(events)
        }

        eventRecyclerView.adapter = com.afebrii.zoosavvy.ui.news.EventAdapter(eventArrayList)
    }
}