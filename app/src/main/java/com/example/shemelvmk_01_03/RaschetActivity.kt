package com.example.shemelvmk_01_03

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.*
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import java.text.DecimalFormat

class RaschetActivity : AppCompatActivity() {

    private lateinit var button: Button
    private lateinit var spin: Spinner
    private lateinit var metrText: EditText
    private lateinit var result: TextView
    private lateinit var selectedItemText: TextView

    private val pricePerMetre = 60000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.raschet)

        button = findViewById(R.id.button)
        metrText = findViewById(R.id.metresText)
        spin = findViewById(R.id.idSpiner)
        result = findViewById(R.id.result)
        selectedItemText = findViewById(R.id.selectedItemText)

        val kv = arrayOf(
            "1-о комнатная квартира",
            "2-х комнатная квартира",
            "3-х комнатная квартира",
            "Студия"
        )

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, kv)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spin.adapter = adapter

        spin.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                selectedItemText.text = "Вы выбрали: ${kv[position]}"
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                selectedItemText.text = "Выберите вариант"
            }
        }

        button.setOnClickListener {
            val metresText = metrText.text.toString()
            if (metresText.isEmpty()) {
                Toast.makeText(this, "Введите количество метров", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val metres = metresText.toDoubleOrNull()
            if (metres == null) {
                Toast.makeText(this, "Некорректное значение метров", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val selectedKV = spin.selectedItemPosition
            val coefficient = when (selectedKV) {
                0 -> 1.4  // 1-о комнатная квартира
                1 -> 1.0  // 2-х комнатная квартира
                2 -> 0.8  // 3-х комнатная квартира
                3 -> 1.1  // Студия
                else -> 1.0
            }

            val totalPrice = metres * pricePerMetre * coefficient
            val formattedPrice = DecimalFormat("#,###").format(totalPrice)

            result.text = "Стоимость: $formattedPrice руб."

            Handler(Looper.getMainLooper()).postDelayed({
                val intent = Intent(this, EndActivity::class.java)
                intent.putExtra("METRES", metresText)
                intent.putExtra("RESULT", "Стоимость: $formattedPrice руб.")
                startActivity(intent)
            }, 3000)
        }
    }
}
