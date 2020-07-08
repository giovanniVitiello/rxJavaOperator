package com.example.rxjavaretrofit.objectData

open class Users(open val users: List<User>)
//
//data class PopularMovies(override val results: List<Result>) : Movies(results)
//
//data class UpdatesMovies(override val results: List<Result>) : Movies(results)

data class User(
    val id: Int,
    val name: String,
    val lastname: String
)