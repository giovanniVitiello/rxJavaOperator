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
            .subscribeOn(Schedulers.io())//serve per dire che vogliamo che i dati passino su un altro thread rispetto al main di Android(ui)
                                        // per non rallentare il processo (Multithreading).
            .observeOn(AndroidSchedulers.mainThread())//serve per dire che vogliamo trasferire i dati dal thread io al main Thread
                                                //*
            .subscribe({ onResponseFinalMovies(movies) },
                { t -> onFailure(t) })

        compositeDisposable.addAll(finalDisposable)
    }

    // * quando facciamo observable.subsribe, ci iscriviamo all'observable ed otteniamo un oggetto di tipo observer(o disposable
    // cioè che con rxjava2 disposable che una volta pieno di oggetti da osservare li contiene e li manda a poco a poco, ed infine
    // con la funzione dispose nell'onDestroy vengono eliminati per evitare di mantenere troppi dati in memoria -Memory leak-
    // quindi praticamente ci de-iscriviamo all'observable)

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
