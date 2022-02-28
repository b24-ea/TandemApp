package com.aldanmaz.tandemapp.di

import com.aldanmaz.tandemapp.network.ApiService
import com.aldanmaz.tandemapp.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiClient {

    @Singleton
    @Provides
    fun getLoggingInterceptor() = HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.BASIC)

    @Singleton
    @Provides
    fun getOkHttpClient() = OkHttpClient.Builder()
        .addInterceptor(getLoggingInterceptor())
        .build()

    @Singleton
    @Provides
    fun getClient(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(getOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }
    val apiService: ApiService = getClient().create(ApiService::class.java)
}