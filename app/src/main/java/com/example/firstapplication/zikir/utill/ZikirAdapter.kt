package com.example.firstapplication.zikir.utill

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.example.firstapplication.R
import com.example.firstapplication.zikir.RepositoryanViewmodel.DeleteZikirViewModel
import com.example.firstapplication.zikir.data.Zikir
import com.example.firstapplication.zikir.ui.ZikirUpdateActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.item_zikir.view.*

class ZikirAdapter(var ZikirListesi: List<Zikir>, var onClickZikir: OnClickZikir) :
    RecyclerView.Adapter<ZikirAdapter.ViewHolder>() {

    lateinit var viewModel: DeleteZikirViewModel


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        var view = LayoutInflater.from(parent.context).inflate(R.layout.item_zikir, parent, false)

        return ViewHolder(
            view
        )
    }

    override fun getItemCount(): Int = ZikirListesi.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.zikir_tv.text = ZikirListesi[position].name

        holder.itemView.setOnClickListener {

            onClickZikir.OnClickZikirItem(ZikirListesi[position])

        }


    }


    fun notifyEditItem(activity: Activity, context: Context, position: Int) {
        val intent = Intent(context, ZikirUpdateActivity::class.java)
        intent.putExtra("notify_zikir", ZikirListesi[position])
        // Activity is started with requestCode
        activity.startActivity(intent)

        notifyItemChanged(position) // Notify any registered observers that the item at position has changed.
    }

    fun notifyDeleteItem(context: Context, activity: FragmentActivity, Position: Int) {

        val builder = MaterialAlertDialogBuilder(context)
            .setBackground(context.getDrawable(R.drawable.alert_shape))
            .setTitle("Silme Onayı")
            .setMessage("Zikiri Silmek istediğinize emin misiniz?")
            .setIcon(R.drawable.ic_delete_black_24dp)
            .setNegativeButton("Hayır", { dialog, which ->

                dialog.dismiss()
                notifyItemChanged(Position)

            })
            .setPositiveButton("Evet", { dialog, which ->

                viewModel = ViewModelProviders.of(activity).get(DeleteZikirViewModel::class.java)

                viewModel.delete(ZikirListesi[Position])

                notifyItemRemoved(Position)


            })
        val dialog = builder.create()
        dialog.show()

        //notifyItemRemoved(Position)


    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var zikir_tv = view.zikir_tv as TextView

    }


}