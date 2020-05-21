package com.example.firstapplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.firstapplication.R
import com.example.firstapplication.model.TakvimModel
import kotlinx.android.synthetic.main.item_takvim_yapragi.view.*

class TakvimAdapter(var array: ArrayList<TakvimModel>) :
    RecyclerView.Adapter<TakvimAdapter.TakvimViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TakvimViewHolder {

        var view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_takvim_yapragi, parent, false)

        return TakvimViewHolder(view)


    }

    override fun getItemCount(): Int = array.size


    override fun onBindViewHolder(holder: TakvimViewHolder, position: Int) {

        holder.bg.setImageResource(array[position].bg)
        holder.sinif_text.text = array[position].sinif
        holder.text.text = array[position].sinif_text
        holder.sinif_text.setTextColor(array[position].sinif_text_color)
        holder.text.setTextColor(array[position].text_color)


    }


    class TakvimViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var bg = view.item_ty_iv as ImageView
        var sinif_text = view.item_ty_sinif_tv as TextView

        var text = view.item_ty_text_tv as TextView


    }


}