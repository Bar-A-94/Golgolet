package com.example.skullwarrior.ycode

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.DataBindingUtil.bind
import androidx.databinding.DataBindingUtil.setContentView
import com.example.skullwarrior.R
import com.example.skullwarrior.databinding.YcodeFragmentBinding

class YcodeFragment : Fragment() {


    private lateinit var viewModel: YcodeViewModel

    private lateinit var binding: YcodeFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = YcodeFragmentBinding.inflate(layoutInflater)
        binding.setLifecycleOwner(this)
        viewModel = ViewModelProvider(this).get(YcodeViewModel::class.java)
        binding.ycodeViewModel = viewModel
        return binding.root
    }
}
