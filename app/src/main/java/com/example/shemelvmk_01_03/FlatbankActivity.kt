package com.example.shemelvmk_01_03

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class FlatbankActivity : AppCompatActivity() {

    private lateinit var button: Button
    private lateinit var loginEditText: EditText
    private lateinit var passwordEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.flatbank)

        button = findViewById(R.id.button)
        loginEditText = findViewById(R.id.loginEditText)
        passwordEditText = findViewById(R.id.passwordEditText)

        button.setOnClickListener{
            val login = loginEditText.text.toString()
            val password = passwordEditText.text.toString()

            if (login == "111" && password == "111"){

                val intent = Intent(this, RaschetActivity::class.java)
                startActivity(intent)
            }else {
                Toast.makeText(this, "Пожалуйста, введите логин и пароль", Toast.LENGTH_SHORT).show()
            }

        }
    }
}