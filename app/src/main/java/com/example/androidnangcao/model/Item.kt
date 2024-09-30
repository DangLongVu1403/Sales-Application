package com.example.androidnangcao.model

import java.io.Serializable

data class Item(
    val urlImg: String,
    val urlImgBanner: String,
    val name: String,
    val price: String,
    val description: String,
    val quantity: Int,
    val discountPercent: Int,
    val sold: Int
) : Serializable
