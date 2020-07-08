package com.example.rxjavaretrofit.serviceBuilder

import com.example.rxjavaretrofit.endPoints.LocalHostEndPoints
import com.example.rxjavaretrofit.endPoints.TripadvisorEndPoints
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory



object LocalHostServiceBuilder {

    private val logging = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    private val client = OkHttpClient
        .Builder()
        .addInterceptor(logging)
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl("http://192.168.1.191:8080/")
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()
        .create(LocalHostEndPoints::class.java)

    fun buildService(): LocalHostEndPoints {

        return retrofit
    }
}