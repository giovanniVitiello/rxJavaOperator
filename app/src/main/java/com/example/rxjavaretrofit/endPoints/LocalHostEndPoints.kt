package com.example.rxjavaretrofit.endPoints

import com.example.rxjavaretrofit.objectData.HotelList
import com.example.rxjavaretrofit.objectData.User
import com.example.rxjavaretrofit.objectData.Users
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Header

interface LocalHostEndPoints {

    @GET("users/")
    fun getUsers(): Observable<Users>

    @GET("users/{id}")
    fun getUser(): Observable<User>

}