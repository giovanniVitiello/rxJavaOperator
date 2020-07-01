package com.example.rxjavaretrofit

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SpotifyEndpoints {

    @GET("/v1/artists")
    fun getArtist(@Query("api_key_spotify") key: String): Observable<ArtistList>
}