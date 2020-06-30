package com.example.rxjavaretrofit

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

interface Response {
    fun receive(moviesResult: List<Result>)
}

class MainActivity : AppCompatActivity() {

    private lateinit var movies: Movies

    private lateinit var observable1: Observable<Movies>
    private lateinit var observable2: Observable<Movies>
    private lateinit var finalDisposable: Disposable

    val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        finalCall()
    }

    private fun finalCall() {
        observable1 = ServiceBuilder.buildService().getUpmovies(getString(R.string.api_key))

        observable2 = ServiceBuilder.buildService().getMovies(getString(R.string.api_key))

        finalDisposable = Observable.zip(
            observable1,
            observable2,
            BiFunction<Movies, Movies, Movies> { one, second ->
                // here we get both the results at a time.
                sumMovies(one, second)
            })
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ onResponseFinalMovies(movies) },
                { t -> onFailure(t) })

        compositeDisposable.addAll(finalDisposable)
    }

    private fun sumMovies(one: Movies, second: Movies): Movies {
        movies = Movies(one.results.plus(second.results))
        return movies
    }

    private fun onResponseFinalMovies(response: Movies) {
        progress_bar.visibility = View.GONE
        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = MoviesAdapter(response.results)
        }
    }

    private fun onFailure(t: Throwable) {
        Toast.makeText(this, t.message, Toast.LENGTH_SHORT).show()
    }
}
