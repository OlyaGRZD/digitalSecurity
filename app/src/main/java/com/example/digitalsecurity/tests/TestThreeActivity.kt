package com.example.digitalsecurity.tests

import android.content.Intent
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.digitalsecurity.MainActivity
import com.example.digitalsecurity.R
import com.example.digitalsecurity.databinding.ActivityMainBinding
import com.example.digitalsecurity.databinding.ActivityTestThreeBinding

class TestThreeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTestThreeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTestThreeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        binding.backtoMenu.setOnClickListener { // кнопка меню
            val intent = Intent(this, MainActivity::class.java)
            finish()
            startActivity(intent)
        }
    }
}