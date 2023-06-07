package com.example.trafa.ui.info

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.trafa.databinding.ActivityInfoBinding
import com.example.trafa.databinding.ActivitySettingBinding

class InfoActivity : AppCompatActivity() {

    private lateinit var binding : ActivitySettingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}