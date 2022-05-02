package com.example.skullwarrior

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.skullwarrior.adapters.ViewPagerAdapter
import com.example.skullwarrior.databinding.ActivityMainBinding
import com.example.skullwarrior.gunangle.GunAngleFragment
import com.example.skullwarrior.hevertip.HeverTipFragment
import com.example.skullwarrior.ycode.YcodeFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.appBar)

        setUpTab()
    }

    private fun setUpTab(){
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(GunAngleFragment(), "Gun Angle")
        adapter.addFragment(HeverTipFragment(), "Hever Tip")
        adapter.addFragment(YcodeFragment(), "Y-Code")
        binding.viewPager.adapter = adapter
        binding.tabs.setupWithViewPager(binding.viewPager)



    }
}