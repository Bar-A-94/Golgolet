package com.example.skullwarrior.about

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.skullwarrior.databinding.FragmentAboutEnglishBinding

class AboutEnglishFragment : Fragment() {
    private lateinit var binding: FragmentAboutEnglishBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentAboutEnglishBinding.inflate(layoutInflater)
        binding.email.setOnClickListener {
            val to = "balon94@gmail.com"
            val subject = "Golgolet app - E-mail contact "
            val message = "Thank you for the app!"

            val intent = Intent(Intent.ACTION_SEND)
            val addressees = arrayOf(to)
            intent.putExtra(Intent.EXTRA_EMAIL, addressees)
            intent.putExtra(Intent.EXTRA_SUBJECT, subject)
            intent.putExtra(Intent.EXTRA_TEXT, message)
            intent.type = "message/rfc822"
            startActivity(Intent.createChooser(intent, "Send Email using:"))
        }
        return binding.root

    }

}