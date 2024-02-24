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
import com.example.digitalsecurity.databinding.ActivityTestTwoBinding

class TestTwoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTestTwoBinding
    private lateinit var currentTest: Test
    private var currentQuestionIndex: Int = 0
    private var numErrors: Int = 0

    private val tests: List<Test> = listOf(
        Test("Найдите сайт, в адресе которого нет ошибки:", listOf(
            "https://online.sperbank.ru",
            "https://online.sberbank.ru",
            "https://online.cberbank.ru"), 1),
        Test("Вам позвонили из Пенсионного фонда и сообщили, что сейчас проходит индексация вашей пенсии. Работник пенсионного фонда просит назвать код из сообщения. Ваши действия?", listOf(
            "Уточню ФИО работника и назову ему код.",
            "Сразу скажу ему код, чтобы о моей пенсии позаботились!",
            "Завершу звонок и сообщу близким о том, что мне звонили мошенники."), 2),
        Test("Найдите надежный пароль", listOf(
            "OlgaPavlovna1955",
            "Thwe02#%hNswA",
            "1234567890"), 1),
        Test("Вы получили электронное письмо, в котором говорится о том, что вы выиграли 100 000 рублей в новогоднем розыгрыше. Чтобы забрать выигрыш, вам нужно зарегистрироваться на сайте. Ваши действия:", listOf(
            "Не буду переходить по ссылке из письма и помечу его, как спам.",
            "Перейду по ссылке и укажу свою карту, чтобы получить выигрыш.",
            "Перешлю сообщение подругам, чтобы разделить с ними радость."), 0),
        Test("С незнакомого номера вам позвонил родственник и в слезах рассказал о том, что попал в серьезную аварию и ему срочно нужны деньги. Ваши действия:", listOf(
            "Перезвоню по этому номеру и,уточнив ситуацию, переведу деньги.",
            "Без раздумий переведу деньги, ведь близкому нужна моя помощь.",
            "Позвоню кому-нибудь из близких и расскажу о случившемся. Деньги переводить никому не буду."), 2),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTestTwoBinding.inflate(layoutInflater)
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
            radioButton.setTextSize(TypedValue.COMPLEX_UNIT_SP, 19.toFloat())
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