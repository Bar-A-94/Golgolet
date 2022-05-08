package com.example.skullwarrior.ycode

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.skullwarrior.databinding.YcodeFragmentBinding
import java.text.SimpleDateFormat

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
        binding.button.setOnClickListener {
            // Make sure there is input to make the action
            if (binding.hours.text.toString() == "" ||
                binding.minutes.text.toString() == ""
            ) {
                Toast.makeText(context, "Please fill in the data", Toast.LENGTH_SHORT).show()
            } else {
                // Make sure the input is in hours, minutes format
                if (binding.hours.text.toString().toInt() > 23 || binding.minutes.text.toString()
                        .toInt() > 59
                ) {
                    Toast.makeText(context, "Plan time doesn't make sense", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    viewModel.onClickCalculate(
                        binding.hours.text.toString().toLong(),
                        binding.minutes.text.toString().toLong()
                    )
                    // Set output to be visible
                    binding.plannerAfter.visibility = View.VISIBLE
                    binding.plannerBefore.visibility = View.VISIBLE
                    binding.original.visibility = View.VISIBLE
                }
            }
            view?.hideKeyboard()
        }
        return binding.root
    }

    /**
     * Hide the keyboard if there is one open
     */
    private fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }

}


