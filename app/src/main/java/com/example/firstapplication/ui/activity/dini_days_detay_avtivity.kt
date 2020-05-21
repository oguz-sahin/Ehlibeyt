package com.example.firstapplication.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.firstapplication.R
import kotlinx.android.synthetic.main.activity_dini_days_detay_avtivity.*

class dini_days_detay_avtivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dini_days_detay_avtivity)





        textView2.text = intent.getStringExtra("diniGün")


    }
}
