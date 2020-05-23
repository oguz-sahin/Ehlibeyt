package com.example.firstapplication.esmaulhusna.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.firstapplication.R
import kotlinx.android.synthetic.main.activity_esmaul_husna_detay.*

class EsmaulHusnaDetayActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_esmaul_husna_detay)



        ehdetayname.text = intent.getStringExtra("ehname")
        ehdetayimage.setImageResource(intent.getIntExtra("ehimage", R.drawable.allah))


    }
}
