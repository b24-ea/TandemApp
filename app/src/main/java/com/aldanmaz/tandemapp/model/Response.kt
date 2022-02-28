package com.aldanmaz.tandemapp.model


import com.google.gson.annotations.SerializedName

data class Response(
    @SerializedName("firstName")
    val firstName: String,

    @SerializedName("id")
    val id: Int,

    @SerializedName("learns")
    val learns: List<String>,

    @SerializedName("natives")
    val natives: List<String>,

    @SerializedName("pictureUrl")
    val pictureUrl: String,

    @SerializedName("referenceCnt")
    val referenceCnt: String,

    @SerializedName("topic")
    val topic: String
)