package com.example.firstapplication.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.firstapplication.R
import com.example.firstapplication.model.MenuModel
import com.example.firstapplication.onClickInterface.OnclickMenuItem
import com.example.firstapplication.ui.activity.*
import com.example.firstapplication.ui.zikir.ui.zikir_activity


class FirstMenu : Fragment(), OnclickMenuItem {


    lateinit var menu: ArrayList<MenuModel>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view: View = inflater.inflate(R.layout.fragment_first_menu, container, false)


        var rv = view.findViewById<RecyclerView>(R.id.rvfirst)
        rv.layoutManager = GridLayoutManager(activity!!, 3)
        rv.adapter = context?.let {
            com.example.firstapplication.adapter.MenuAdapter(
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
                "Takvim Yaprağı"
                , Intent(activity, takvim_yapragi_activity::class.java)
            )
        )
        menu.add(
            MenuModel(
                android.R.drawable.ic_menu_delete,
                "Kaza Hesapla"
                , Intent(activity, kaza_hesapla_activity::class.java)
            )
        )
        menu.add(
            MenuModel(
                android.R.drawable.ic_menu_add,
                "Esmaül Hüsna"
                , Intent(activity, esmaul_husna_activity::class.java)
            )
        )
        menu.add(
            MenuModel(
                android.R.drawable.ic_menu_compass,
                "Dini Günler"
                , Intent(activity, dini_days_activity::class.java)
            )
        )
        menu.add(
            MenuModel(
                android.R.drawable.ic_menu_compass,
                "Zikir"
                , Intent(activity, zikir_activity::class.java)
            )
        )
        menu.add(
            MenuModel(
                android.R.drawable.ic_menu_compass,
                "Kabe Canlı"
                , Intent(activity, kabe_canli_activity::class.java)
            )
        )

    }

    override fun OnclickMenu(Menu: MenuModel) {

        startActivity(Menu.MenuIntent)


    }


}
