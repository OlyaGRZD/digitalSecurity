package com.example.digitalsecurity

import android.content.Intent
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.digitalsecurity.databinding.ActivityShablonThreeBinding

class ShablonThreeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityShablonThreeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShablonThreeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        binding.backtoMenu.setOnClickListener { // кнопка меню
            val intent = Intent(this, MainActivity::class.java)
            finish()
            startActivity(intent)
        }
    }
}