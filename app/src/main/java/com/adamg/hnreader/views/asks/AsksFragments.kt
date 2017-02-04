package com.adamg.hnreader.views.asks


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.adamg.hnreader.R


/**
 * A simple [Fragment] subclass.
 */
class AsksFragments : Fragment() {


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_asks_fragments, container, false)
    }

}// Required empty public constructor
