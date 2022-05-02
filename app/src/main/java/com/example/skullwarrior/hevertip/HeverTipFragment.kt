package com.example.skullwarrior.hevertip

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.skullwarrior.R

class HeverTipFragment : Fragment() {



    private lateinit var viewModel: HeverTipViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.hever_tip_fragment, container, false)
    }



}