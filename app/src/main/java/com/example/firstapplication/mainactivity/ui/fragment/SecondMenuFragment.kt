package com.example.firstapplication.mainactivity.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.firstapplication.R
import com.example.firstapplication.dinigunler.ui.dini_days_activity
import com.example.firstapplication.mainactivity.adapter.MenuAdapter
import com.example.firstapplication.mainactivity.model.MenuModel
import com.example.firstapplication.mainactivity.utill.OnclickMenuItem


class SecondMenuFragment : Fragment(),
    OnclickMenuItem {

    lateinit var menu: ArrayList<MenuModel>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view: View = inflater.inflate(R.layout.fragment_second_menu, container, false)

        var rv = view.findViewById<RecyclerView>(R.id.second_menu_recyclerview)
        rv.layoutManager = GridLayoutManager(activity!!, 3)
        rv.adapter = context?.let {
            MenuAdapter(
                it,
                menu, this
            )
        }

        return view


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        menu = ArrayList<MenuModel>()

        menu.add(
            MenuModel(
                android.R.drawable.ic_menu_compass,
                "menu1"
                , Intent(activity, dini_days_activity::class.java)
            )
        )
        menu.add(
            MenuModel(
                android.R.drawable.ic_menu_delete,
                "menu2"
                , Intent(activity, dini_days_activity::class.java)
            )
        )
        menu.add(
            MenuModel(
                android.R.drawable.ic_menu_add,
                "menu3"
                , Intent(activity, dini_days_activity::class.java)
            )
        )
        menu.add(
            MenuModel(
                android.R.drawable.ic_menu_compass,
                "menu4"
                , Intent(activity, dini_days_activity::class.java)
            )
        )
        menu.add(
            MenuModel(
                android.R.drawable.ic_menu_compass,
                "menu5"
                , Intent(activity, dini_days_activity::class.java)
            )
        )
        menu.add(
            MenuModel(
                android.R.drawable.ic_menu_compass,
                "menu6"
                , Intent(activity, dini_days_activity::class.java)
            )
        )


    }

    override fun OnclickMenu(Menu: MenuModel) {
        startActivity(Menu.MenuIntent)
    }

}





