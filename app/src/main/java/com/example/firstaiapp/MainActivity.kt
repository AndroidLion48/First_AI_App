package com.example.firstaiapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.ai.client.generativeai.GenerativeModel
import kotlinx.coroutines.runBlocking


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val editTextPrompt = findViewById<EditText>(R.id.editTextPrompt)
        val btnSubmit = findViewById<Button>(R.id.btnSubmit)
        val tVResult = findViewById<TextView>(R.id.tVResult)


        btnSubmit.setOnClickListener {
            val prompt = editTextPrompt.text.toString()

            val generativeModel = GenerativeModel(
                // For text-only input, use the gemini-pro model
                modelName = "gemini-2.5-flash",
                // Access your API key as a BuildConfig variable
                apiKey = "ApikeyGoesHere" // Replace with your actual API key
            )

            runBlocking {
                val response = generativeModel.generateContent(prompt)
                tVResult.text = response.text
            }
        }
    }
}
