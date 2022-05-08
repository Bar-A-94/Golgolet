package com.example.skullwarrior.gunangle

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.skullwarrior.databinding.GunAngleFragmentBinding

class GunAngleFragment : Fragment() {

    private lateinit var viewModel: GunAngleViewModel

    private lateinit var binding: GunAngleFragmentBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // preset altitude, used if there is no altitude input
        var altitude = 12000F
        binding = GunAngleFragmentBinding.inflate(layoutInflater)
        binding.lifecycleOwner = this
        viewModel = ViewModelProvider(this)[GunAngleViewModel::class.java]
        binding.gunAngleViewModel = viewModel

        binding.button.setOnClickListener {
            // Make sure there is input to make the action
            if (binding.shooterVelocityInput.text.toString() == ""
                || binding.victimVelocityInput.text.toString() == ""
                || binding.closingVelocityInput.text.toString() == ""
            ) Toast.makeText(context, "Please fill in the data", Toast.LENGTH_SHORT).show()
            else {
                // check if there is altitude for the input
                if (binding.altitudeInput.text.toString() != "")
                    altitude = binding.altitudeInput.text.toString().toFloat()
                val shooterSpeed = binding.shooterVelocityInput.text.toString().toFloat()
                val victimSpeed = binding.victimVelocityInput.text.toString().toFloat()
                val closingSpeed = binding.closingVelocityInput.text.toString().toFloat()
                val above = viewModel.onClickCalculate(shooterSpeed, victimSpeed, altitude, closingSpeed)
                // Make output visible
                binding.angleIntro.visibility = View.VISIBLE
                binding.angle.visibility = View.VISIBLE
                // Pop up a message according to the angle
                if (above)
                    Toast.makeText(context, "תותח מעל 90 עדיין פוגע", Toast.LENGTH_SHORT).show()
                else Toast.makeText(context, "בדקת גם את הטווח?", Toast.LENGTH_SHORT).show()
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