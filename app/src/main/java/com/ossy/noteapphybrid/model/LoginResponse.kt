package com.ossy.noteapphybrid.model


//// Response model for login
//data class LoginResponse(
//    val status: String,
//    val message: String,
//    val token: String? // Token received upon successful login
//)
data class LoginResponse(
    val data: Map<String, Any>,
    val message: String,
    val status: String
)
