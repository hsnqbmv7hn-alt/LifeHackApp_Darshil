package com.example.lifehackapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class QuizActivity : AppCompatActivity() {

    private lateinit var questionText: TextView
    private lateinit var feedbackText: TextView

    private val questions = arrayOf(
        "Putting your phone in rice fixes water damage",
        "Drinking water helps improve concentration",
        "Cracking knuckles causes arthritis",
        "Using dark mode saves battery",
        "Eating carrots improves eyesight drastically"
    )

    private val answers = arrayOf(
        false, true, false, true, false
    )

    private val explanations = arrayOf(
        "Rice doesn't effectively remove moisture from phones.",
        "Hydration improves brain performance.",
        "No scientific evidence links it to arthritis.",
        "Dark mode helps save battery on OLED screens.",
        "Carrots help, but not drastically."
    )

    private var currentIndex = 0
    private var score = 0
    private val userAnswers = mutableListOf<Boolean>()

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        questionText = findViewById(R.id.txtQuestion)
        feedbackText = findViewById(R.id.txtFeedback)

        val btnHack = findViewById<Button>(R.id.btnHack)
        val btnMyth = findViewById<Button>(R.id.btnMyth)
        val btnNext = findViewById<Button>(R.id.btnNext)

        loadQuestion()

        btnHack.setOnClickListener { checkAnswer(true) }
        btnMyth.setOnClickListener { checkAnswer(false) }

        btnNext.setOnClickListener {
            currentIndex++
            if (currentIndex < questions.size) {
                loadQuestion()
                feedbackText.text = ""
            } else {
                val intent = Intent(this, ScoreActivity::class.java)
                intent.putExtra("score", score)
                intent.putExtra("total", questions.size)
                intent.putExtra("questions", questions)
                intent.putExtra("answers", answers)
                intent.putExtra("userAnswers", userAnswers.toBooleanArray())
                intent.putExtra("explanations", explanations)
                startActivity(intent)
                finish()
            }
        }
    }

    private fun loadQuestion() {
        questionText.text = questions[currentIndex]
    }

    @SuppressLint("SetTextI18n")
    private fun checkAnswer(userAnswer: Boolean) {
        val correct = answers[currentIndex]
        userAnswers.add(userAnswer)

        if (userAnswer == correct) {
            score++
            feedbackText.text = "✅ Correct!"
        } else {
            feedbackText.text = "❌ Wrong!"
        }
    }
}