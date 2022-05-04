package com.example.skullwarrior.ycode

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.skullwarrior.databinding.YcodeFragmentBinding

class YcodeFragment : Fragment() {


    private lateinit var viewModel: YcodeViewModel

    private lateinit var binding: YcodeFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = YcodeFragmentBinding.inflate(layoutInflater)
        binding.lifecycleOwner = this
        viewModel = ViewModelProvider(this)[YcodeViewModel::class.java]
        binding.ycodeViewModel = viewModel
        return binding.root
    }
}
