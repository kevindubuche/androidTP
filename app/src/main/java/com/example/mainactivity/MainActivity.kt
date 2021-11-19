package com.example.mainactivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible

class MainActivity : AppCompatActivity() {
    private lateinit var clickButton: Button
    private lateinit var textView: TextView
    private var nbClick = 0
    private lateinit var computeButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        clickButton = findViewById(R.id.btn_click_me)
        computeButton = findViewById(R.id.btn_compute)
        textView = findViewById(R.id.textView)
        textView.isVisible = false

        clickButton.setOnClickListener {
            //Toast.makeText(baseContext, "Tu m'as cliqué", Toast.LENGTH_LONG).show()
            textView.isVisible = true
            nbClick++
            val newText = "Vous avez cliqué $nbClick"
            textView.text = newText
            if(nbClick >= 5)
                clickButton.isEnabled = false
        }
        computeButton.setOnClickListener {
            val intent = Intent(baseContext, ComputeActivity::class.java)
            startActivity(intent)
        }
    }
}