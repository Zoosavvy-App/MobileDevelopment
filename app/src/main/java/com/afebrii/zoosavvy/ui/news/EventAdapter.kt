package com.afebrii.zoosavvy.ui.news

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.afebrii.zoosavvy.R

class EventAdapter(private val newListEvent: ArrayList<Event>): RecyclerView.Adapter<EventAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventAdapter.MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item_event_news, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: EventAdapter.MyViewHolder, position: Int) {
        val currentItem = newListEvent[position]
        holder.ivImage.setImageResource(currentItem.image)
        holder.tvJudul.text = currentItem.judul
        holder.tvTempat.text = currentItem.tempat
        holder.tvTanggal.text = currentItem.tanggal
    }

    override fun getItemCount(): Int {
        return newListEvent.size
    }

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val ivImage: ImageView = itemView.findViewById(R.id.iv_news)
        val tvJudul: TextView = itemView.findViewById(R.id.judul_news)
        val tvTempat: TextView = itemView.findViewById(R.id.tempat_news)
        val tvTanggal: TextView = itemView.findViewById(R.id.tanggal_news)
    }
}