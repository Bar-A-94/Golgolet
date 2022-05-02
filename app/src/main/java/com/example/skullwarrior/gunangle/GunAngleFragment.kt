package com.example.skullwarrior.gunangle

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.skullwarrior.R

class GunAngleFragment : Fragment() {

    private lateinit var viewModel: GunAngleViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(GunAngleViewModel::class.java)
        return inflater.inflate(R.layout.gun_angle_fragment, container, false)
    }



}