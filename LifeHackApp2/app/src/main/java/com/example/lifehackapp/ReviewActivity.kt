package com.example.lifehackapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ReviewActivity : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_review)

        val txtReview = findViewById<TextView>(R.id.txtReview)

        val questions = intent.getStringArrayExtra("questions")!!
        val answers = intent.getBooleanArrayExtra("answers")!!
        val userAnswers = intent.getBooleanArrayExtra("userAnswers")!!
        val explanations = intent.getStringArrayExtra("explanations")!!

        val reviewText = StringBuilder()

        for (i in questions.indices) {
            reviewText.append("Q: ${questions[i]}\n")
            reviewText.append("Your Answer: ${userAnswers[i]}\n")
            reviewText.append("Correct Answer: ${answers[i]}\n")
            reviewText.append("Explanation: ${explanations[i]}\n\n")
        }

        txtReview.text = reviewText.toString()
    }
}
