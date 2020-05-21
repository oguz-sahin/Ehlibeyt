package com.example.firstapplication.onClickInterface

import android.app.AlertDialog
import android.app.Dialog
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.example.firstapplication.R
import com.example.firstapplication.model.KazaModel
import kotlin.properties.Delegates

class kazaHesaplaDialogClass(var position: KazaModel, var callbackDialog: CallbackDialog) :
    DialogFragment() {


    lateinit var gelenSp: SharedPreferences
    var value by Delegates.notNull<Int>()
    lateinit var vakit: String
    lateinit var valuesTv: TextView
    lateinit var increaseButton: ImageButton
    lateinit var removeButton: ImageButton

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        var builder = AlertDialog.Builder(activity)
        var view = activity?.layoutInflater?.inflate(R.layout.customkazahesapladialog, null)

        builder.setView(view)

        gelenSp = position.sp
        value = position.value
        vakit = position.vakit



        increaseButton = view!!.findViewById(R.id.increaseButton)
        removeButton = view.findViewById(R.id.removeButton)
        valuesTv = view.findViewById(R.id.values_tv)

        ReadValue()
        valuesTv.text = value.toString()



        increaseButton.setOnClickListener {
            incresaValue()
            SaveValue()
            callbackDialog.onCallbackDialog()

        }

        removeButton.setOnClickListener {
            removeValue()
            SaveValue()

            callbackDialog.onCallbackDialog()

        }



        return builder.create()

    }


    fun incresaValue() {

        value++
        valuesTv.text = value.toString()


    }


    fun removeValue() {
        if (value != 0) {

            value--

            valuesTv.text = value.toString()

        }


    }

    fun SaveValue() {


        val editor = gelenSp.edit()

        editor.apply {


            putInt(vakit, value)

            apply()
        }


    }

    fun ReadValue() {

        value = gelenSp.getInt(vakit, 0)


    }


}

