package com.example.network

import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST
import com.example.response.ChatRequest
import com.example.response.ChatResponse

interface ApiInterface {
    @POST("chat/completions")
    fun createChatCompletion(){
        @Body chatRequest : ChatRequest
    }
}