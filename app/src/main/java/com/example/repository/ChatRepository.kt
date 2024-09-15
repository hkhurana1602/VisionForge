package com.example.repository

import android.util.Log
import com.example.network.ApiClient
import com.example.response.ChatRequest
import com.example.response.ChatResponse
import com.example.response.Message
import com.example.utils.CHAT_GPT_MODEL
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ChatRepository {

    private val apiClient = ApiClient.getInstance()

    fun createChatCompletion(message: String, onResult: (String?) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val chatRequest = ChatRequest(
                    arrayListOf(
                        Message(
                            "Introduce yourself",
                            "system"
                        ),
                        Message(
                            message,
                            "user"
                        )
                    ),
                    CHAT_GPT_MODEL
                )
                apiClient.createChatCompletion(chatRequest).enqueue(object : Callback<ChatResponse>{
                    override fun onResponse(call: Call<ChatResponse>, response: Response<ChatResponse>) {
                        if (response.isSuccessful) {
                            val responseBody = response.body()
                            Log.d("API Response", "Response: $responseBody")

                            // Check if choices list and message content are not null
                            val content = responseBody?.choices?.get(0)?.message?.content
                            if (content != null) {
                                onResult(content) // Pass the content to the callback
                            } else {
                                Log.e("API Response", "No content found in the response")
                                onResult(null) // No content in the response
                            }
                        } else {
                            Log.e("API Error", "Error: ${response.message()}, Code: ${response.code()}")
                            onResult(null) // If error, return null
                        }
                    }

                    override fun onFailure(call: Call<ChatResponse>, t: Throwable) {
                        Log.e("API Error", "Error: ${t.message}")
                        onResult(null) // If failure, return null
                    }

                })

            } catch (e: Exception) {
                e.printStackTrace()
                onResult(null)
            }
        }
    }
}