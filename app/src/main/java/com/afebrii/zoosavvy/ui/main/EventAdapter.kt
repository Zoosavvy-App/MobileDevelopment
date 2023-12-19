package com.afebrii.zoosavvy.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.afebrii.zoosavvy.R
import org.w3c.dom.Text

class EventAdapter(private val newListEvent: ArrayList<Event>):
    RecyclerView.Adapter<EventAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item_event, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return newListEvent.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = newListEvent[position]
        holder.ivImage.setImageResource(currentItem.image)
        holder.tvJudul.text = currentItem.judul
        holder.tvTempat.text = currentItem.tempat
        holder.tvTanggal.text = currentItem.tanggal
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val ivImage: ImageView = itemView.findViewById(R.id.iv_event)
        val tvJudul: TextView = itemView.findViewById(R.id.tv_judul_event)
        val tvTempat: TextView = itemView.findViewById(R.id.tv_tempat_event)
        val tvTanggal: TextView = itemView.findViewById(R.id.tv_tanggal_event)
    }

}