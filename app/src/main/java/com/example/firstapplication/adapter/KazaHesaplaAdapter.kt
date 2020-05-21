package com.example.firstapplication.adapter

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.firstapplication.R
import com.example.firstapplication.model.KazaModel
import com.example.firstapplication.onClickInterface.CallbackDialog
import com.example.firstapplication.onClickInterface.OnKazaItemClickListener
import kotlinx.android.synthetic.main.item_kaza_hesapla.view.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class KazaHesaplaAdapter(
    var context: Context,
    var array: ArrayList<KazaModel>,
    var OnkazaItemClickListener: OnKazaItemClickListener,
    var CallbackDialog: CallbackDialog
) :
    RecyclerView.Adapter<KazaHesaplaAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.item_kaza_hesapla, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = array.size

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {


        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
        val formatted = current.format(formatter)


        var changedValue = array[position].sp.getInt(array[position].vakit, 0)

        array[position].value = changedValue
        holder.change_time_tv.text = formatted

        holder.vakit_tv.text = array[position].vakit
        holder.kaza_value_tv.text = array[position].value.toString()

        holder.itemView.setOnClickListener {

            OnkazaItemClickListener.kazaitemclick(array[position], CallbackDialog)


        }


    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {


        var vakit_tv = view.item_vakit_tv as TextView
        var kaza_value_tv = view.item_kaza_sayisi_tv as TextView
        var change_time_tv = view.item_kaza_change_time_tv as TextView


    }


}








