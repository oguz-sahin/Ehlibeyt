package com.example.firstapplication.esmaulhusna.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.firstapplication.R
import com.example.firstapplication.esmaulhusna.model.EsmaulHusnaModel
import com.example.firstapplication.esmaulhusna.utill.onEsmaulhusnaClickListener
import kotlinx.android.synthetic.main.item_esmaul_husna.view.*

class EsmaulHusnaAdapter(
    var context: Context,
    var array: ArrayList<EsmaulHusnaModel>,
    var onEsmaulhusnaClickListener: onEsmaulhusnaClickListener
) : RecyclerView.Adapter<EsmaulHusnaAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        var view = LayoutInflater.from(context).inflate(R.layout.item_esmaul_husna, parent, false)
        return ViewHolder(
            view
        )

    }

    override fun getItemCount(): Int = array.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.iv.setImageResource(array[position].image)
        holder.tv.text = array[position].isim


        holder.itemView.setOnClickListener {


            onEsmaulhusnaClickListener.onesmaulhusnaclick(array[position])


        }


    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var iv = view.item_esmaul_iv as ImageView
        var tv = view.item_esmaul_name_tv as TextView


    }

}