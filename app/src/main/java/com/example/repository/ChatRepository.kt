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

    fun createChatCompletion(message: String) {
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
                    override fun onResponse(
                        call: Call<ChatResponse>,
                        response: Response<ChatResponse>
                    ) {
                        val code = response.code()
                        if(code == 200){

                            response.body()?.choices?.get(0)?.message?.let {
                                Log.d("message", it.toString())
                            }
                        } else{
                            Log.d("error", response.errorBody().toString())
                        }
                    }

                    override fun onFailure(call: Call<ChatResponse>, t: Throwable) {
                        t.printStackTrace()
                    }

                })

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}