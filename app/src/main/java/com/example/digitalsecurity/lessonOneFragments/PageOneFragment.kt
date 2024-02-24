package com.example.digitalsecurity.lessonOneFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.digitalsecurity.databinding.FragmentPageOneBinding


//Use the [PageOneFragment.newInstance] factory method

class PageOneFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    private var _binding: FragmentPageOneBinding?= null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPageOneBinding
            .inflate(inflater, container, false)
        return binding.root
    }

    companion object {
    }
}