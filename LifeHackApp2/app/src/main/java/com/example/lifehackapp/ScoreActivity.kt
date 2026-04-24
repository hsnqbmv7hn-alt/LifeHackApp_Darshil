package com.example.lifehackapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ScoreActivity : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score)

        val txtScore = findViewById<TextView>(R.id.txtScore)
        val txtMessage = findViewById<TextView>(R.id.txtMessage)
        val btnReview = findViewById<Button>(R.id.btnReview)

        val score = intent.getIntExtra("score", 0)
        val total = intent.getIntExtra("total", 0)

        txtScore.text = "Score: $score / $total"

        val message = if (score >= 4) {
            "🔥 Master Hacker!"
        } else {
            "⚠️ Stay Safe Online!"
        }

        txtMessage.text = message

        btnReview.setOnClickListener {
            val intent = Intent(this, ReviewActivity::class.java)
            intent.putExtras(getIntent())
            startActivity(intent)
        }
    }
}

