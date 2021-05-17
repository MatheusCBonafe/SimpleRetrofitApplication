package com.example.simpleretrofitapplication

import com.google.gson.annotations.SerializedName

data class DataItem(
    val body: String,
    val id: Int,
    val title: String,
    val userId: Int
)