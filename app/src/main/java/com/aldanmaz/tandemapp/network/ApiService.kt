package com.aldanmaz.tandemapp.network

import com.aldanmaz.tandemapp.model.UserResponse
import com.aldanmaz.tandemapp.utils.Constants
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET(Constants.END_PNT)
    suspend fun getUserList(
        @Path("page") page: Int
    ) : UserResponse
}