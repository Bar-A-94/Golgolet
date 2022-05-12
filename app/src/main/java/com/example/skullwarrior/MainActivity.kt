package com.example.skullwarrior

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.skullwarrior.about.AboutEnglishFragment
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

    /**
     * Set up the tabs, tabs titles and their fragments
     */
    private fun setUpTab(){
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(YcodeFragment(), "Y-Code")
        adapter.addFragment(GunAngleFragment(), "Gun Angle")
        adapter.addFragment(HeverTipFragment(), "Hever Tip")
        adapter.addFragment(AboutEnglishFragment(), "About")
        binding.viewPager.adapter = adapter
        binding.tabs.setupWithViewPager(binding.viewPager)

    }
}