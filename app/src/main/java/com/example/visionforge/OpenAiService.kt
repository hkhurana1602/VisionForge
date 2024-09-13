package com.example.visionforge

import retrofit2.Call
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Body

data class GenerateRequest(val prompt: String, val max_tokens: Int)

data class GenerateResponse(val choices: List<Choice>)
data class Choice(val text: String)

interface OpenAIService {
    @Headers("Authorization: Bearer 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")
    @POST("v1/completions")
    fun generateContent(@Body request: GenerateRequest): Call<GenerateResponse>
}
