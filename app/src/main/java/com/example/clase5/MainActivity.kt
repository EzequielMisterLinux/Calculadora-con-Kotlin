package com.example.clase5

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var tvResultado: TextView
    private lateinit var atNum1: EditText
    private lateinit var atNum2: EditText

    private lateinit var atRadioGroup: RadioGroup
    private lateinit var atBtnSend: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        atNum1 = findViewById(R.id.num1)
        atNum2 = findViewById(R.id.num2)
        atRadioGroup = findViewById(R.id.radioGroup)
        tvResultado = findViewById(R.id.tvResultado)
        atBtnSend = findViewById(R.id.send)

        atBtnSend.setOnClickListener {
            CalcularResult()
        }
    }

    private fun CalcularResult() {
        val numero1 = atNum1.text.toString().toDoubleOrNull()
        val numero2 = atNum2.text.toString().toDoubleOrNull()

        if (numero1 != null && numero2 != null) {
            val selectedOperation = when (atRadioGroup.checkedRadioButtonId) {
                R.id.sum -> numero1 + numero2
                R.id.resta -> numero1 - numero2
                R.id.multiplicacion -> numero1 * numero2
                R.id.division -> if (numero2 != 0.0) numero1 / numero2 else null
                else -> null
            }

            if (selectedOperation != null) {
                tvResultado.text = "Resultado: $selectedOperation"
            } else {
                tvResultado.text = "Error: Operación inválida o división por cero."
            }
        } else {
            tvResultado.text = "Por favor ingrese números válidos"
        }
    }
}
