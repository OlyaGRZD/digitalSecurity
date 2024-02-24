package com.example.digitalsecurity

import android.content.Intent
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.digitalsecurity.databinding.ActivityShablonTwoBinding
import com.example.digitalsecurity.lessonOneFragments.PageOneFragment
import com.example.digitalsecurity.lessonTwoFragments.PageFiveSecondFragment
import com.example.digitalsecurity.lessonTwoFragments.PageFourSecondFragment
import com.example.digitalsecurity.lessonTwoFragments.PageOneSecondFragment
import com.example.digitalsecurity.lessonTwoFragments.PageSixSecondFragment
import com.example.digitalsecurity.lessonTwoFragments.PageThreeSecondFragment
import com.example.digitalsecurity.lessonTwoFragments.PageTwoSecondFragment

class ShablonTwoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityShablonTwoBinding
    private lateinit var currentFragment: Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShablonTwoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        currentFragment = PageOneSecondFragment()
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
            is PageOneSecondFragment -> PageTwoSecondFragment()
            is PageTwoSecondFragment -> PageThreeSecondFragment()
            is PageThreeSecondFragment -> PageFourSecondFragment()
            is PageFourSecondFragment -> PageFiveSecondFragment()
            is PageFiveSecondFragment -> PageSixSecondFragment()
            is PageSixSecondFragment -> {
                finishShablonActivity()
                null
            }
            else -> PageOneFragment()
        }
    }

    private fun getPreviousFragment(): Fragment? {
        return when (currentFragment) {
            is PageOneSecondFragment -> {
                finishShablonActivity()
                null
            }
            is PageTwoSecondFragment -> PageOneSecondFragment()
            is PageThreeSecondFragment -> PageTwoSecondFragment()
            is PageFourSecondFragment -> PageThreeSecondFragment()
            is PageFiveSecondFragment -> PageFourSecondFragment()
            is PageSixSecondFragment -> PageFiveSecondFragment()

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