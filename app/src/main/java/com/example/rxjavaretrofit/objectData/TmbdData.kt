package com.example.rxjavaretrofit.objectData

open class Movies(open val results: List<Result>)
//
//data class PopularMovies(override val results: List<Result>) : Movies(results)
//
//data class UpdatesMovies(override val results: List<Result>) : Movies(results)

data class Result(
    val id: Int,
    val overview: String,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val vote_average: Double,
    val vote_count: Int
)