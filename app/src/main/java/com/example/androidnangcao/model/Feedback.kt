package com.example.androidnangcao.model

import java.io.Serializable

data class Feedback(
    val urlImgEmail: String,
    val email: String,
    val feedback: String
) : Serializable
