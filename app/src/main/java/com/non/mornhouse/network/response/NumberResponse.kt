package com.non.mornhouse.network.response

data class NumberResponse(
    val text: String,
    val number: Int?,
    val found: Boolean,
    val type: String
)