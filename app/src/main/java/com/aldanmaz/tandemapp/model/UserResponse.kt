package com.aldanmaz.tandemapp.model


import com.google.gson.annotations.SerializedName

data class UserResponse(
    var page: Int,

    @SerializedName("errorCode")
    val errorCode: String,

    @SerializedName("response")
    val response: List<Response>,

    @SerializedName("type")
    val type: String
)