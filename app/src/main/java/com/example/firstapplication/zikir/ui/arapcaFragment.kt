package com.example.firstapplication.zikir.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.firstapplication.R
import com.example.firstapplication.zikir.data.Zikir

class arapcaFragment(var zikir: Zikir) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_arapca, container, false)

        if (zikir.arapca_okunusu.isNullOrEmpty()) {
            zikir.arapca_okunusu = ""
        }

        val arapcatv = view.findViewById<TextView>(R.id.arapca_tv)
        arapcatv.text = zikir.arapca_okunusu

        return view
    }


}
