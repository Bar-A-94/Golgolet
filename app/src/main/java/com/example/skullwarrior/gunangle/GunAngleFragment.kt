package com.example.skullwarrior.gunangle

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.skullwarrior.R
import com.example.skullwarrior.databinding.GunAngleFragmentBinding

class GunAngleFragment : Fragment() {

    private lateinit var viewModel: GunAngleViewModel

    private lateinit var binding: GunAngleFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = GunAngleFragmentBinding.inflate(layoutInflater)
        binding.setLifecycleOwner(this)
        viewModel = ViewModelProvider(this).get(GunAngleViewModel::class.java)
        binding.gunAngleViewModel = viewModel

        return binding.root
    }



}