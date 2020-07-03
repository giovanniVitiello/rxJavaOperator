package com.example.rxjavaretrofit.endPoints

import com.example.rxjavaretrofit.objectData.HotelList
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Header

interface TripadvisorEndPoints {

    @GET("hotels/list?offset=0&currency=USD&limit=30&order=asc&lang=en_US&sort=recommended&location_id=293919&adults=1&checkin=%3Crequired%3E&rooms=1&nights=2")
    fun getHotel(@Header("X-RapidAPI-Key") key: String): Observable<HotelList>
}