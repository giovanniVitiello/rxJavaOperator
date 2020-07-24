package com.example.rxjavaretrofit.endPoints

import com.example.rxjavaretrofit.model.User
import io.reactivex.Observable
import retrofit2.http.GET

interface LocalHostEndPoints {

    @GET("users/")
    fun getUsers(): Observable<List<User>>

    @GET("users/{id}")
    fun getUser(): Observable<User>

}