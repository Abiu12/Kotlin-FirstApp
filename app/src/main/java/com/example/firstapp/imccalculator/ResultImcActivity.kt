package com.example.firstapp.imccalculator

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.firstapp.R
import com.example.firstapp.imccalculator.ImcActivity.Companion.IMC_KEY

class ResultImcActivity : AppCompatActivity() {
    private lateinit var tvResultImc: TextView
    private lateinit var tvImc: TextView
    private lateinit var tvDescriptionImc: TextView
    private lateinit var btnRecalculate: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_result_imc)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val resultImc = intent.extras?.getDouble(IMC_KEY) ?: -1.0
        initComponents()
        initListeners()
        initGUI(resultImc)
    }
    private  fun initComponents(){
        tvResultImc = findViewById(R.id.tvResultImc)
        tvImc = findViewById(R.id.tvImc)
        tvDescriptionImc = findViewById(R.id.tvDescriptionImc)
        btnRecalculate = findViewById(R.id.btnRecalculate)
    }
    private fun initListeners(){
        btnRecalculate.setOnClickListener{
            onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun initGUI(resultImc: Double) {
        tvImc.text = resultImc.toString()
        when(resultImc){
            in 0.00 .. 30.00 -> {
                tvResultImc.text = getString(R.string.normal)
                tvResultImc.setTextColor(ContextCompat.getColor(this,R.color.green))
                tvDescriptionImc.text = getString(R.string.normal)
            }
            in 31.00 .. 10000000.00 -> {
                tvResultImc.text = getString(R.string.anormal   )
                tvResultImc.setTextColor(ContextCompat.getColor(this,R.color.red))
                tvDescriptionImc.text = getString(R.string.anormal)
            }
            else -> {
                tvResultImc.text = getString(R.string.error)
                tvResultImc.setTextColor(ContextCompat.getColor(this,R.color.red))
            }
        }
    }

}