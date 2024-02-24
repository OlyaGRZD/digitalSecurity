package com.example.digitalsecurity.tests

import android.content.Intent
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
import android.view.View
import android.widget.RadioButton
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.example.digitalsecurity.MainActivity
import com.example.digitalsecurity.R
import com.example.digitalsecurity.Test
import com.example.digitalsecurity.databinding.ActivityTestOneBinding


class TestOneActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTestOneBinding
    private lateinit var currentTest: Test
    private var currentQuestionIndex: Int = 0
    private var numErrors: Int = 0

    private val tests: List<Test> = listOf(
        Test("Что такое Смартфон?", listOf(
            "Умный телевизор",
            "Умный телефон",
            "Умный фотоаппарат"), 1),

        Test("Как выключить телефон?", listOf(
            "Нажать на кнопку включения/выключения и удерживать ее несколько секунд",
            "Поочерёдно нажимать на кнопки увеличения и уменьшения громкости",
            "Ждать пока телефон разрядится"), 0),

        Test("Зачем обновлять операционную систему?", listOf(
            "Чтобы все функции смартфона работали правильно",
            "Чтобы убрать рекламу из Интернета",
            "Обновлять операционную систему не надо!!!"), 0),

        Test("Как посмотреть подробности уведомлений?", listOf(
            "Зайти в сообщения",
            "Смахнуть пальцем по экрану снизу вверх",
            "Смахнуть пальцем с самой верхней части экрана до его центра"), 2),

        Test("Над уведомлениями мы видим настройки быстрого действия. Как понять, какие из них включены?", listOf(
            "Включенные настройки подсвечиваются цветом",
            "Они перечислены в одном из уведомлений",
            "Включенные настройки ничем не отличаются от выключенных"), 0),

        Test("Какой кнопки нет в меню внизу экрана?", listOf(
            "Домой",
            "Недавно использованные приложения",
            "Искать в Интернете",
            "Назад"), 2)

    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTestOneBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        showQuestion()

        binding.nextButton.setOnClickListener { onNextButtonClicked() }
    }

    private fun showQuestion() {
        currentTest = tests[currentQuestionIndex]
        binding.questionTextView.text = currentTest.question
        val answersRadioGroup = binding.answersRadioGroup
        answersRadioGroup.removeAllViews()
            // Заполняем варианты ответов
        for ((index, answer) in currentTest.answers.withIndex()) {
            val radioButton = RadioButton(this)
            radioButton.text = answer
            radioButton.id = index

            radioButton.setTextColor(ContextCompat.getColor(this, R.color.white))
            radioButton.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20.toFloat())
            radioButton.typeface = ResourcesCompat.getFont(this, R.font.roboto_medium)
            binding.answersRadioGroup.addView(radioButton)
        }
    }

    private fun onNextButtonClicked() {
        val selectedAnswerIndex = binding.answersRadioGroup.checkedRadioButtonId
        if (selectedAnswerIndex == -1) {
            // Пользователь не выбрал ответ
            Toast.makeText(this, "Выберите ответ", Toast.LENGTH_SHORT).show()
        } else {
            val selectedAnswer = currentTest.answers[selectedAnswerIndex]
            if (selectedAnswerIndex == currentTest.correctAnswerIndex) {
                // Правильный ответ
            } else {
                // Неправильный ответ
                numErrors++
            }

            currentQuestionIndex++
            if (currentQuestionIndex < tests.size) {
                showQuestion()
            } else {
                showTestResult()
            }
        }
    }

    private fun showTestResult() {
        val resultMessage = "Количество ошибок: $numErrors/${tests.size}"
        binding.resultTextView.text = resultMessage
        binding.resultTextView.visibility = View.VISIBLE

        binding.questionTextView.visibility = View.GONE
        binding.answersRadioGroup.visibility = View.GONE
        binding.nextButton.visibility = View.GONE

        binding.restartButton.visibility = View.VISIBLE
        binding.backToMenuButton.visibility = View.VISIBLE

        binding.restartButton.setOnClickListener {

            // Начать тест заново
            currentQuestionIndex = 0
            numErrors = 0
            binding.answersRadioGroup.removeAllViews()
            showQuestion()
            binding.resultTextView.visibility = View.GONE
            binding.restartButton.visibility = View.GONE
            binding.questionTextView.visibility = View.VISIBLE
            binding.answersRadioGroup.visibility = View.VISIBLE
            binding.nextButton.visibility = View.VISIBLE
        }

        binding.backToMenuButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            finish()
            startActivity(intent)
        }
    }
}