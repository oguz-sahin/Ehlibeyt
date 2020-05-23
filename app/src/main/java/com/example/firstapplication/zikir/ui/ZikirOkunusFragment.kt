package com.example.firstapplication.zikir.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.firstapplication.R
import com.example.firstapplication.zikir.data.Zikir


class ZikirOkunusFragment(var zikir: Zikir) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_zikir_okunus, container, false)

        var okunus_tv = view.findViewById<TextView>(R.id.zikir_okunus_tv)

        okunus_tv.text = zikir.name



        return view
    }

}
