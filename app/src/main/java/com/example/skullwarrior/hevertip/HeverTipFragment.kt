package com.example.skullwarrior.hevertip

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.skullwarrior.R
import com.example.skullwarrior.databinding.HeverTipFragmentBinding

class HeverTipFragment : Fragment() {


    private lateinit var viewModel: HeverTipViewModel

    private lateinit var binding: HeverTipFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = HeverTipFragmentBinding.inflate(layoutInflater)
        binding.lifecycleOwner = this
        viewModel = ViewModelProvider(this)[HeverTipViewModel::class.java]
        binding.heverTipViewModel = viewModel

        viewModel.tip.observe(viewLifecycleOwner) {
            if (it != null) {
                binding.tipAmountOutput.text = it
            }
        }

        binding.button.setOnClickListener {
            if (binding.amountToPayInput.text.toString() == ""
                || binding.tipPercentsInput.text.toString() == ""
                || binding.numOfPayersInput.text.toString() == "") {
                Toast.makeText(context,"Please fill in the data", Toast.LENGTH_SHORT).show()
            } else {
                val amount = binding.amountToPayInput.text.toString().toInt()
                val tipPercents = binding.tipPercentsInput.text.toString().toInt()
                val numOfPayers = binding.numOfPayersInput.text.toString().toInt()
                val arr = viewModel.onClickCalculate(amount, tipPercents, numOfPayers)
                binding.payingWithHever.visibility = View.VISIBLE
                binding.tipAmount.visibility = View.VISIBLE
                if (arr[0]) {
                    binding.payingWithCredit.visibility = View.VISIBLE
                } else{
                    binding.payingWithCredit.visibility = View.INVISIBLE
                }
                if (arr[1]) {
                    binding.splitter.visibility = View.VISIBLE
                } else{
                    binding.splitter.visibility = View.INVISIBLE
                }
            }

        }
        return binding.root
    }


}