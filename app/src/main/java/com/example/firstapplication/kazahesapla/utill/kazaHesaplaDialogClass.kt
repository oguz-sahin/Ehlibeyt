package com.example.firstapplication.kazahesapla.utill

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProviders
import com.example.firstapplication.R
import com.example.firstapplication.kazahesapla.data.Kaza
import com.example.firstapplication.kazahesapla.kazaupdate.kazaupdateviewmodel

class kazaHesaplaDialogClass(var position: Kaza) :
    DialogFragment() {
    private lateinit var updateViewModel: kazaupdateviewmodel
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val builder = AlertDialog.Builder(activity)
        val view = activity?.layoutInflater?.inflate(R.layout.customkazahesapladialog, null)

        builder.setView(view)

        val increasebtn = view!!.findViewById<ImageButton>(R.id.increaseButton)
        val removebtn = view.findViewById<ImageButton>(R.id.removeButton)
        val valuestv = view.findViewById<TextView>(R.id.values_tv)

        valuestv.text = position.kazaSayisi.toString()
        updateViewModel = ViewModelProviders.of(this).get(kazaupdateviewmodel::class.java)

        increasebtn.setOnClickListener {

            position.kazaSayisi++
            valuestv.text = position.kazaSayisi.toString()
            updateViewModel.updateKaza(position)

        }

        removebtn.setOnClickListener {

            if (position.kazaSayisi !== 0) {
                position.kazaSayisi--
                valuestv.text = position.kazaSayisi.toString()
                updateViewModel.updateKaza(position)
            }

        }


        return builder.create()

    }






}

