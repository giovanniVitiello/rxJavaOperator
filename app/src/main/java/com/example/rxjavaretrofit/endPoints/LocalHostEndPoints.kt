package com.example.rxjavaretrofit.endPoints

import com.example.rxjavaretrofit.model.User
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface LocalHostEndPoints {

    @GET("users/")
    fun getUsers(): Observable<List<User>>

    @GET("users/{id}")
    fun getUser(@Path("id") userId: Int): Observable<User>
}