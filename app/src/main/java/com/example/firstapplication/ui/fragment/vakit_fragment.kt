package com.example.firstapplication.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.firstapplication.Conts
import com.example.firstapplication.R
import kotlinx.android.synthetic.main.fragment_vakit_fragment.*


class vakit_fragment : Fragment() {

    companion object {
        fun newInstance(kalanVakit: String): vakit_fragment {
            val args = Bundle()
            args.putString(Conts.KalanVakit, kalanVakit)
            val fragment = vakit_fragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_vakit_fragment, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        textView.text = arguments!!.getString(Conts.KalanVakit)
    }

}
