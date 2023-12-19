package com.afebrii.zoosavvy.ui.satwa

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.afebrii.zoosavvy.R
import org.w3c.dom.Text

class SatwaAdapter(private val newListSatwa: ArrayList<Satwa>):
    RecyclerView.Adapter<SatwaAdapter.MyViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SatwaAdapter.MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item_satwa, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: SatwaAdapter.MyViewHolder, position: Int) {
        val currentItem = newListSatwa[position]
        holder.ivSatwa.setImageResource(currentItem.image)
        holder.tvNama.text = currentItem.nama
        holder.tvNamaLatin.text = currentItem.namaLatin
        holder.tvDeskripsi.text = currentItem.deskripsi
    }

    override fun getItemCount(): Int {
        return newListSatwa.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivSatwa: ImageView = itemView.findViewById(R.id.iv_satwa)
        val tvNama: TextView = itemView.findViewById(R.id.tv_nama)
        val tvNamaLatin: TextView = itemView.findViewById(R.id.tv_nama_latin)
        val tvDeskripsi: TextView = itemView.findViewById(R.id.tv_deskripsi)
    }

}