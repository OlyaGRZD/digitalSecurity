package com.example.digitalsecurity.lessonTwoFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.digitalsecurity.databinding.FragmentPageThreeSecondBinding

class PageThreeSecondFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    private var _binding: FragmentPageThreeSecondBinding?= null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPageThreeSecondBinding
            .inflate(inflater, container, false)
        return binding.root
    }

    companion object {
    }
}