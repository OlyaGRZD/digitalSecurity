package com.example.digitalsecurity.lessonTwoFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import com.example.digitalsecurity.databinding.FragmentPageSixSecondBinding
import java.util.regex.Pattern

class PageSixSecondFragment : Fragment() {

    private var _binding: FragmentPageSixSecondBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPageSixSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.checkBtn.setOnClickListener {
            binding.passResults.visibility = View.GONE
            validatePassword()
        }
    }

    private fun validatePassword() {
        // Сброс сообщения об ошибке перед проверкой пароля
        binding.passwordInputLayout.error = null

        val password = binding.passwordEditText.text.toString()

        val passwordPattern = Pattern.compile(
            "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!,.:?*@#$%^&+=])(?=\\S+\$).{8,}\$"
        )
        val passwordMatcher = passwordPattern.matcher(password)

        if (!passwordMatcher.matches()) {
            val results = "Попробуйте еще раз! Пароль должеть быть длиной не менее 8 символов, содержать цифры, строчные и заглавные буквы"
            binding.passResults.text = results
            binding.passResults.visibility = View.VISIBLE
        } else {
            val results = "Отличный пароль, Вы молодец!"
            binding.passResults.text = results
            binding.passResults.visibility = View.VISIBLE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

