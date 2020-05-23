package com.example.firstapplication.zikir.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.firstapplication.R
import com.example.firstapplication.zikir.data.Zikir


class mealFragment(var zikir: Zikir) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_meal, container, false)

        var mealtv = view.findViewById<TextView>(R.id.meal_tv)

        if (zikir.meal.isNullOrEmpty()) {
            zikir.meal = ""


        }


        mealtv.text = zikir.meal




        return view
    }


}
