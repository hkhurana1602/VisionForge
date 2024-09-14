package com.example.response

data class ChatRequest(
    val messages: List<Message>,
    val model: String
)