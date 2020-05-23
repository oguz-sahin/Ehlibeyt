package com.example.firstapplication.kazahesapla.adapter

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.firstapplication.R
import com.example.firstapplication.kazahesapla.data.Kaza
import com.example.firstapplication.kazahesapla.utill.OnKazaItemClickListener
import kotlinx.android.synthetic.main.item_kaza_hesapla.view.*

class KazaHesaplaAdapter(
    var context: Context,
    var array: List<Kaza>,
    var OnkazaItemClickListener: OnKazaItemClickListener
) : RecyclerView.Adapter<KazaHesaplaAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.item_kaza_hesapla, parent, false)
        return ViewHolder(
            view
        )
    }

    override fun getItemCount(): Int = array.size

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {


        holder.vakit_tv.text = array[position].vakitName
        holder.kaza_value_tv.text = array[position].kazaSayisi.toString()

        holder.itemView.setOnClickListener {

            OnkazaItemClickListener.kazaitemclick(array[position])


        }


    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {


        var vakit_tv = view.item_vakit_tv as TextView
        var kaza_value_tv = view.item_kaza_sayisi_tv as TextView

    }


}








