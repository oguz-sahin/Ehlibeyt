package com.example.firstapplication.Cards

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.firstapplication.R

class CardAdapter(
    var context: Context,
    var CardArray: ArrayList<String>,
    var CardItemClickListener: CardItemClickListener
) : RecyclerView.Adapter<CardAdapter.CardViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.item_card, parent, false)
        return CardViewHolder(view)
    }

    override fun getItemCount(): Int = CardArray.size

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {

        Glide.with(context).load(CardArray[position]).into(holder.card_iv)
        holder.itemView.setOnClickListener {

            CardItemClickListener.CardItemClick(CardArray[position], context)

        }

    }


    class CardViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var card_iv = view.findViewById<ImageView>(R.id.card_iv)


    }


}