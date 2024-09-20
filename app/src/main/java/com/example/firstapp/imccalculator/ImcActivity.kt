package com.example.firstapp.imccalculator

import android.content.Intent
import android.icu.text.DecimalFormat
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.firstapp.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.slider.RangeSlider

class ImcActivity : AppCompatActivity() {
    private var isMaleSelected: Boolean = true
    private var isFemaleSelected: Boolean = false
    private var currentWeight: Int = 50
    private var currentAge: Int = 25
    private var currentHeight: Int = 160

    private lateinit var cardMale: CardView
    private lateinit var cardFemale: CardView
    private lateinit var tvHeight: TextView
    private lateinit var rsHeight: RangeSlider
    private lateinit var tvWeight: TextView
    private lateinit var tvAge: TextView
    private lateinit var btnSubstractWeight: FloatingActionButton
    private lateinit var btnPlusWeight: FloatingActionButton
    private lateinit var btnSubstractAge: FloatingActionButton
    private lateinit var btnPlusAge: FloatingActionButton
    private lateinit var btnCalculate: Button

    companion object {
        const val IMC_KEY = "IMC_RESULT"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_imc)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        initComponents()
        initListeners()
    }

    private fun initComponents() {
        cardMale = findViewById(R.id.cvMale)
        cardFemale = findViewById(R.id.cvFemale)
        rsHeight = findViewById(R.id.rsHeight)
        tvHeight = findViewById(R.id.tvHeight)
        tvWeight = findViewById(R.id.tvWeight)
        tvAge = findViewById(R.id.tvAge)
        btnSubstractWeight = findViewById(R.id.btnSubstractWeight)
        btnPlusWeight = findViewById(R.id.btnPlusWeight)
        btnSubstractAge = findViewById(R.id.btnSubstractAge)
        btnPlusAge = findViewById(R.id.btnPlusAge)
        btnCalculate = findViewById(R.id.btnCalculate)
    }

    private fun changeSelected() {
        isMaleSelected = !isMaleSelected
        isFemaleSelected = !isFemaleSelected
    }

    private fun initListeners() {
        cardMale.setOnClickListener {
            changeSelected()
            setColorCard()
        }
        cardFemale.setOnClickListener {
            changeSelected()
            setColorCard()
        }
        rsHeight.addOnChangeListener { _, value, _ ->
            val df = DecimalFormat("#.##")
            currentHeight = df.format(value).toInt()
            tvHeight.text = currentHeight.toString()
        }
        btnSubstractWeight.setOnClickListener {
            currentWeight -= 1
            tvWeight.text = currentWeight.toString()
        }
        btnPlusWeight.setOnClickListener {
            currentWeight += 1
            tvWeight.text = currentWeight.toString()
        }
        btnSubstractAge.setOnClickListener {
            currentAge -= 1
            tvAge.text = currentAge.toString()
        }
        btnPlusAge.setOnClickListener {
            currentAge += 1
            tvAge.text = currentAge.toString()
        }
        btnCalculate.setOnClickListener {
            navigateToResultImc(calculateIMC())
        }
    }

    private fun navigateToResultImc(resultImc: Double) {
        var intent = Intent(this, ResultImcActivity::class.java)
        intent.putExtra(IMC_KEY, resultImc)
        startActivity(intent)
    }

    private fun calculateIMC(): Double {
        var df = DecimalFormat("#,##")
        return df.format(currentWeight / ((currentHeight.toDouble() / 100) * (currentHeight.toDouble() / 100)))
            .toDouble()
    }

    private fun changeValueTextView() {

    }

    private fun setColorCard() {
        cardMale.setCardBackgroundColor(getColor(isMaleSelected))
        cardFemale.setCardBackgroundColor(getColor(isFemaleSelected))
    }

    private fun getColor(isSelected: Boolean): Int {
        var referenceColor = if (isSelected) {
            R.color.background_component_selected
        } else {
            R.color.background_component
        }
        return ContextCompat.getColor(this, referenceColor)
    }
}