package com.example.firstapplication.Cards

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
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

            override fun onDataChange(DataSnapshot: DataSnapshot) {
                DataSnapshot.child("Cards").children.forEach {
                    CardArray.add(it.value.toString())


                }

                val rv = findViewById<RecyclerView>(R.id.card_rv)
                rv.adapter = CardAdapter(this@CardActivity, CardArray, this@CardActivity)
                rv.layoutManager = GridLayoutManager(this@CardActivity, 2)


            }


        })


    }

    override fun CardItemClick(url: String, context: Context) {


        val image = Glide.with(applicationContext).load(url)
        val uri = Uri.parse(image.toString())

        val shareIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            type = "image/*"
            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            putExtra(Intent.EXTRA_STREAM, url)

        }
        startActivity(Intent.createChooser(shareIntent, "asd"))

        Log.e("url", url)
        val string = "#ffffff"
        val white = Color.parseColor(string)


    }
}
