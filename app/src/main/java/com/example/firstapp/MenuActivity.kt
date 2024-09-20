package com.example.firstapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.firstapp.imccalculator.ImcActivity

class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_menu)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val btnStartApp = findViewById<Button>(R.id.btnSaludarApp)
        btnStartApp.setOnClickListener{navigateSaludarApp()}
        val btnImcApp = findViewById<Button>(R.id.btnImcApp)
        btnImcApp.setOnClickListener{
            navigateIMCApp()
        }

    }
    private fun navigateSaludarApp(){
        val intent = Intent(this,FirstAppActivity::class.java)
        startActivity(intent)
    }
    private fun navigateIMCApp(){
        val intent = Intent(this,ImcActivity::class.java)
        startActivity(intent)
    }
}