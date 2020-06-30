package com.example.rxjavaretrofit

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TmdbEndpoints {

    @GET("/3/movie/popular")
    fun getMovies(@Query("api_key") key: String): Observable<Movies>

    @GET("/3/movie/upcoming")
    fun getUpmovies(@Query("api_key") key: String): Observable<Movies>

    @GET("/3/movie/{movie_id}")
    fun getMovieDetail(
        @Path("movie_id") uuid: Int,
        @Query("api_key") key: String): Observable<Result>
}