package com.example.shemelvmk_01_03

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.shemelvmk_01_03.databinding.EndBinding

class EndActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: EndBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.end)

        val metres = intent.getStringExtra("METRES") ?: "0"
        val resultValue = intent.getStringExtra("RESULT") ?: "0 руб."

        val metresText: EditText = findViewById(R.id.metresText)
        val resultText: TextView = findViewById(R.id.result)
        val registerButton: Button = findViewById(R.id.buttonRegister)

        metresText.setText(metres)
        resultText.text = resultValue

        registerButton.setOnClickListener {
            val intent = Intent(this, FlatbankActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP 
            startActivity(intent)
            finish()
        }
    }
}