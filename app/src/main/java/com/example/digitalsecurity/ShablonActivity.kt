package com.example.digitalsecurity

import android.content.Intent
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.digitalsecurity.databinding.ActivityShablonBinding
import com.example.digitalsecurity.lessonOneFragments.PageEightFragment
import com.example.digitalsecurity.lessonOneFragments.PageFiveFragment
import com.example.digitalsecurity.lessonOneFragments.PageFourFragment
import com.example.digitalsecurity.lessonOneFragments.PageNineFragment
import com.example.digitalsecurity.lessonOneFragments.PageOneFragment
import com.example.digitalsecurity.lessonOneFragments.PageSevenFragment
import com.example.digitalsecurity.lessonOneFragments.PageSixFragment
import com.example.digitalsecurity.lessonOneFragments.PageThreeFragment
import com.example.digitalsecurity.lessonOneFragments.PageTwoFragment

class ShablonActivity : AppCompatActivity() {

    private lateinit var binding: ActivityShablonBinding
    private lateinit var currentFragment: Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShablonBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        currentFragment = PageOneFragment()
        replaceFragment(currentFragment)

        binding.backBtn.setOnClickListener { // кнопка назад
            val previousFragment = getPreviousFragment()
            if (previousFragment != null) {
                replaceFragment(previousFragment)
            }

        }

        binding.mainBtn.setOnClickListener { // кнопка меню
            val intent = Intent(this, MainActivity::class.java)
            finish()
            startActivity(intent)
        }

        binding.nextBtn.setOnClickListener { // кнопка продолжить
            val nextFragment = getNextFragment()
            if (nextFragment != null) {
                replaceFragment(nextFragment)
            }
        }
    }

    private fun getNextFragment(): Fragment? {

        return when (currentFragment) {
            is PageOneFragment -> PageTwoFragment()
            is PageTwoFragment -> PageThreeFragment()
            is PageThreeFragment -> PageFourFragment()
            is PageFourFragment -> PageFiveFragment()
            is PageFiveFragment -> PageSixFragment()
            is PageSixFragment -> PageSevenFragment()
            is PageSevenFragment -> PageEightFragment()
            is PageEightFragment -> PageNineFragment()
            is PageNineFragment -> {
                finishShablonActivity()
                null
            }
            else -> PageOneFragment()
        }
    }

    private fun getPreviousFragment(): Fragment? {

        return when (currentFragment) {
            is PageOneFragment -> {
                finishShablonActivity()
                null
            }
            is PageTwoFragment -> PageOneFragment()
            is PageThreeFragment -> PageTwoFragment()
            is PageFourFragment -> PageThreeFragment()
            is PageFiveFragment -> PageFourFragment()
            is PageSixFragment -> PageFiveFragment()
            is PageSevenFragment -> PageSixFragment()
            is PageEightFragment -> PageSevenFragment()
            is PageNineFragment -> PageEightFragment()

            else -> PageOneFragment()
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .addToBackStack(null)
            .commit()
         currentFragment = fragment // Обновляем текущий фрагмент
    }

    private fun finishShablonActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}