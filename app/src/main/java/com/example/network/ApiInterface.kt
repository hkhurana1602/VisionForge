package com.example.network

import com.example.response.ChatRequest
import com.example.response.ChatResponse
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST
import com.example.utils.OPEN_API_KEY
import retrofit2.Call

interface ApiInterface {

    @POST("chat/completions")
    fun createChatCompletion(
        @Body chatRequest : ChatRequest,
        @Header("Content-Type") contentType : String = "application/json",
        @Header("Authorization") authorization : String = "Bearer $OPEN_API_KEY",
    ) : Call<ChatResponse>
}