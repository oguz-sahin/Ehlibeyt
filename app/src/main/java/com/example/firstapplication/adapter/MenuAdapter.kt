package com.example.firstapplication.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.firstapplication.R
import com.example.firstapplication.model.MenuModel
import com.example.firstapplication.onClickInterface.OnclickMenuItem
import kotlinx.android.synthetic.main.item_menu.view.*

class MenuAdapter(
    var context: Context,
    var menuModelList: ArrayList<MenuModel>,
    var onclickMenuItem: OnclickMenuItem
) : RecyclerView.Adapter<MenuAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.item_menu, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = menuModelList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.menuiconiv.setImageResource(menuModelList[position].menuImage)
        holder.menunametv.text = menuModelList[position].menuName

        holder.itemView.setOnClickListener {

            onclickMenuItem.OnclickMenu(menuModelList[position])


        }

    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var menuiconiv = view.item_menu_icon as ImageView
        var menunametv = view.item_menu_text as TextView


    }


}