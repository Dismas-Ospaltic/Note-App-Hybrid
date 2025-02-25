package com.example.noteapphybrid.model



//// Response model for user registration
//data class RegisterResponse(
//    val status: String,
//    val message: String
//)

data class RegisterResponse(
    val data: Map<String, Any>,
    val message: String,
    val status: String
)
