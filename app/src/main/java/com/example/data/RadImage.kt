package com.example.data

data class RadImage(
    val imageId: Int,
    val license: String,
    val forOnlineUse: String,
    val description: String,
    val longerDescription: String? = null
)
