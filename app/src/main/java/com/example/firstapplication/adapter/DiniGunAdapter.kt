package com.example.firstapplication.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.firstapplication.R
import com.example.firstapplication.model.DiniGunModel
import com.example.firstapplication.onClickInterface.onDiniDaysClickListener
import kotlinx.android.synthetic.main.item_dini_days.view.*
import java.util.*

class DiniGunAdapter(
    var context: Context,
    var array: ArrayList<DiniGunModel>,
    var onDiniDaysClickListener: onDiniDaysClickListener
) : RecyclerView.Adapter<DiniGunAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        var view = LayoutInflater.from(context).inflate(R.layout.item_dini_days, parent, false)
        return ViewHolder(view)


    }

    override fun getItemCount(): Int = array.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {


        var month = Calendar.getInstance().get(Calendar.MONTH)
        val day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH)


        month += 1
        Log.e(" month", month.toString())
        Log.e("day ", day.toString())
        Log.e("ayınt", array[position].ayInt.toString())
        Log.e("aygün", array[position].tarihInt.toString())


        if (array[position].ayInt < month) {

            holder.tarih_tv.text = array[position].tarih
            holder.tarih_tv.setTextColor(context.getColor(R.color.gri))

            holder.ay_tv.text = array[position].Ay
            holder.ay_tv.setTextColor(context.getColor(R.color.gri))

            holder.text_tv.text = array[position].ozel_gun

            holder.text_tv.setTextColor(context.getColor(R.color.gri))


        } else if (array[position].ayInt == month && array[position].tarihInt < day) {


            holder.tarih_tv.text = array[position].tarih
            holder.tarih_tv.setTextColor(context.getColor(R.color.gri))

            holder.ay_tv.text = array[position].Ay
            holder.ay_tv.setTextColor(context.getColor(R.color.gri))

            holder.text_tv.text = array[position].ozel_gun
            holder.text_tv.setTextColor(context.getColor(R.color.gri))


        } else {
            holder.tarih_tv.text = array[position].tarih
            holder.tarih_tv.setTextColor(context.getColor(R.color.Sari))
            holder.ay_tv.text = array[position].Ay
            holder.ay_tv.setTextColor(context.getColor(R.color.colorBlack))

            holder.text_tv.setTextColor(context.getColor(android.R.color.holo_green_light))
            holder.text_tv.text = array[position].ozel_gun


        }


        holder.itemView.setOnClickListener {


            onDiniDaysClickListener.ondDiniDaysClick(array[position])

        }


    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var tarih_tv = view.dini_day_tarih_tv as TextView
        var ay_tv = view.dini_day_ay_tv as TextView
        var text_tv = view.dini_day_tv as TextView


    }


}