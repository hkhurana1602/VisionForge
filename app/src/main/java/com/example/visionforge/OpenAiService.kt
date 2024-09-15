package com.example.visionforge

import retrofit2.Call
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Body

data class GenerateRequest(val prompt: String, val max_tokens: Int)

data class GenerateResponse(val choices: List<Choice>)
data class Choice(val text: String)

interface OpenAIService {
    @Headers("Authorization: Bearer API_KEY")
    @POST("v1/completions")
    fun generateContent(@Body request: GenerateRequest): Call<GenerateResponse>
}
