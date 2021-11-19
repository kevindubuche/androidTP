package com.example.mainactivity

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible

class ComputeActivity: AppCompatActivity() {
    private lateinit var field_1: EditText
    private lateinit var field_2: EditText
    private lateinit var resultat: TextView
    private lateinit var btn_calculer: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.compute_activity)
        field_1 = findViewById(R.id.field_1)
        field_2 = findViewById(R.id.field_2)
        resultat = findViewById(R.id.resultat)
        btn_calculer = findViewById(R.id.btn_calculer)
        btn_calculer.isEnabled = false

        field_1.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                btn_calculer.isEnabled = field_1.text.toString().isNotEmpty() && field_2.text.toString().isNotEmpty()
            }
        })
        field_2.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                btn_calculer.isEnabled = field_1.text.toString().isNotEmpty() && field_2.text.toString().isNotEmpty()
            }
        })
        btn_calculer.setOnClickListener {
            val res = (field_1.text.toString().toDouble() + field_2.text.toString().toDouble()).toString()
            val newText = "Le résultat est  $res"
            resultat.text = newText
        }



    }
}