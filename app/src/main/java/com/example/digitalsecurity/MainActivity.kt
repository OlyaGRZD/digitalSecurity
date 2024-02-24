package com.example.digitalsecurity

import android.content.Intent
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.digitalsecurity.databinding.ActivityMainBinding
import com.example.digitalsecurity.tests.TestFourActivity
import com.example.digitalsecurity.tests.TestOneActivity
import com.example.digitalsecurity.tests.TestThreeActivity
import com.example.digitalsecurity.tests.TestTwoActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        binding.chaptOneBtn.setOnClickListener {
            val intent = Intent(this, ShablonActivity::class.java)
            finish()
            startActivity(intent)
        }

        binding.testOneBtn.setOnClickListener {
            val intent = Intent(this, TestOneActivity::class.java)
            startActivity(intent)
        }

        binding.chaptTwoBtn.setOnClickListener {
            val intent = Intent(this, ShablonTwoActivity::class.java)
            finish()
            startActivity(intent)
        }

        binding.testTwoBtn.setOnClickListener {
            val intent = Intent(this, TestTwoActivity::class.java)
            startActivity(intent)
        }

        binding.chaptThreeBtn.setOnClickListener {
            val intent = Intent(this, ShablonThreeActivity::class.java)
            finish()
            startActivity(intent)
        }

        binding.testThreeBtn.setOnClickListener {
            val intent = Intent(this, TestThreeActivity::class.java)
            startActivity(intent)
        }

        binding.chaptFourBtn.setOnClickListener {
            val intent = Intent(this, ShablonFourActivity::class.java)
            finish()
            startActivity(intent)
        }

        binding.testFourBtn.setOnClickListener {
            val intent = Intent(this, TestFourActivity::class.java)
            startActivity(intent)
        }
    }
}