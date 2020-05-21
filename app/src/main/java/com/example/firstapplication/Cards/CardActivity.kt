package com.example.firstapplication.Cards

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.firstapplication.R
import com.google.firebase.database.*
import java.util.*

class CardActivity : AppCompatActivity(), CardItemClickListener {

    val DatabaseReference: DatabaseReference by lazy { FirebaseDatabase.getInstance().reference }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card)
        val CardArray = ArrayList<String>()
        DatabaseReference.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                Log.e("Cancel", "Başarısız")
            }

            override fun onDataChange(p0: DataSnapshot) {
                p0.child("Cards").children.forEach {
                    CardArray.add(it.value.toString())


                }

                val rv = findViewById<RecyclerView>(R.id.card_rv)
                rv.adapter = CardAdapter(this@CardActivity, CardArray, this@CardActivity)
                rv.layoutManager = GridLayoutManager(this@CardActivity, 2)


            }


        })


    }

    override fun CardItemClick(url: String, context: Context) {


    }
}
