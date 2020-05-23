package com.example.firstapplication.zikir.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.firstapplication.R
import com.example.firstapplication.mainactivity.ui.activity.MainActivity
import com.example.firstapplication.zikir.RepositoryanViewmodel.ZikirViewvModel
import com.example.firstapplication.zikir.data.Zikir
import com.example.firstapplication.zikir.utill.OnClickZikir
import com.example.firstapplication.zikir.utill.SwipeToDeleteCallback
import com.example.firstapplication.zikir.utill.SwipeToEditCallback
import com.example.firstapplication.zikir.utill.ZikirAdapter
import kotlinx.android.synthetic.main.activity_zikir_activity.*
import kotlinx.android.synthetic.main.activity_zikir_activity.view.*

class zikir_activity : AppCompatActivity(), OnClickZikir {


    private lateinit var ViewModel: ZikirViewvModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_zikir_activity)

        setSupportActionBar(zikir_Toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        zikir_Toolbar.Toolbar_title_tv.text = "Zikirler"

        Toolbar_back_iv.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        ViewModel = ViewModelProviders.of(this).get(ZikirViewvModel::class.java)

        ViewModel.allZikir.observe(this, androidx.lifecycle.Observer {

            zikir_rv.layoutManager = LinearLayoutManager(this)
            zikir_rv.adapter =
                ZikirAdapter(it, this)

        }


        )


        action_button.setOnClickListener {

            var intent = Intent(
                this,
                zikir_ekle_activity::class.java
            )

            startActivity(intent)


        }


        val editSwipeHandler = object : SwipeToEditCallback(this) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

                val adapter = zikir_rv.adapter as ZikirAdapter //cast istiyor
                adapter.notifyEditItem(
                    this@zikir_activity,
                    this@zikir_activity,
                    viewHolder.adapterPosition
                )

            }


        }
        val editItemTouchHelper = ItemTouchHelper(editSwipeHandler)
        editItemTouchHelper.attachToRecyclerView(zikir_rv)

        val deleteSwipeHandler = object : SwipeToDeleteCallback(this) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

                val adapter = zikir_rv.adapter as ZikirAdapter //cast istiyor
                adapter.notifyDeleteItem(
                    this@zikir_activity,
                    this@zikir_activity,
                    viewHolder.adapterPosition
                )

            }


        }
        val deleteItemTouchHelper = ItemTouchHelper(deleteSwipeHandler)
        deleteItemTouchHelper.attachToRecyclerView(zikir_rv)


    }

    override fun OnClickZikirItem(zikir: Zikir) {

        var intent = Intent(this, zikir_detay_activity::class.java)

        var gonderilen_zikir = intent.putExtra("zikir", zikir)

        startActivity(intent)


    }


}
