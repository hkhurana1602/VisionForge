package com.example.visionforge



import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.repository.ChatRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeScreen : AppCompatActivity() {
    private val chatRepository = ChatRepository()
    private lateinit var editTextTopic: EditText
    private lateinit var buttonGenerate: Button
    private lateinit var textViewGeneratedContent: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_screen)

        // Initialize views after setContentView
        editTextTopic = findViewById(R.id.editTextTopic)
        buttonGenerate = findViewById(R.id.buttonGenerate)
        textViewGeneratedContent = findViewById(R.id.textViewGeneratedContent)

        buttonGenerate.setOnClickListener {
            val topic = editTextTopic.text.toString()

            // Check if the input is not empty
            if (topic.isNotBlank()) {
                generateContent(topic)
            } else {
                Toast.makeText(this, "Please enter a topic or keywords.", Toast.LENGTH_SHORT).show()
            }
        }
    }

//    private fun generateContent(topic: String) {
//        textViewGeneratedContent.text = "Generating content for: $topic"
//        // TODO: Call the API and display the generated content
//    }

//    private fun generateContent(topic: String) {
//        val request = GenerateRequest(prompt = topic, max_tokens = 100)
//
//        RetrofitClient.instance.generateContent(request).enqueue(object :
//            Callback<GenerateResponse> {
//            override fun onResponse(
//                call: Call<GenerateResponse>,
//                response: Response<GenerateResponse>
//            ) {
//                if (response.isSuccessful) {
//                    val content = response.body()?.choices?.get(0)?.text
//                    textViewGeneratedContent.text = content ?: "No content generated."
//                } else {
//                    Toast.makeText(
//                        this@HomeScreen,
//                        "API Error: ${response.message()}",
//                        Toast.LENGTH_SHORT
//                    ).show()
//                }
//
//            }
//                override fun onFailure(call: Call<GenerateResponse>, t: Throwable) {
//                    //hideLoading()
//                    Toast.makeText(
//                        this@HomeScreen,
//                        "Network Error: ${t.message}",
//                        Toast.LENGTH_SHORT
//                    ).show()
//                }
//            })
//        }

    private fun generateContent(topic: String) {
        chatRepository.createChatCompletion(topic) { responseMessage ->
            runOnUiThread {
                textViewGeneratedContent.text = responseMessage ?: "No content generated."
            }
        }
    }
    }

