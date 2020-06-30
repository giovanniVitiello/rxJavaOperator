package com.example.rxjavaretrofit

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_main_screen.*

class DetailScreen : Fragment() {
    private lateinit var movies: Movies

    private lateinit var observable: Observable<Result>
    private lateinit var disposable: Disposable

    var key_id = 0

    val compositeDisposable = CompositeDisposable()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        finalCall()
        return inflater.inflate(R.layout.fragment_detail_screen, container, false)
    }


    private fun finalCall() {
        key_id = arguments?.getInt("movie_id")!!
        observable = ServiceBuilder.buildService().getMovieDetail(key_id, getString(R.string.api_key))

        disposable = observable
            .subscribeOn(Schedulers.io()) //serve per dire che vogliamo che i dati passino su un altro thread rispetto al main di Android(ui)
            // per non rallentare il processo (Multithreading).
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result -> onSuccess(result) },
                { error -> onError(error) })

        compositeDisposable.addAll(disposable)
    }

    // * quando facciamo observable.subsribe, ci iscriviamo all'observable ed otteniamo un oggetto di tipo observer(o disposable
    // cioè che con rxjava2 disposable che una volta pieno di oggetti da osservare li contiene e li manda a poco a poco, ed infine
    // con la funzione dispose nell'onDestroy vengono eliminati per evitare di mantenere troppi dati in memoria -Memory leak-
    // quindi praticamente ci de-iscriviamo all'observable)


    private fun onSuccess(result: Result) {
        progress_bar.visibility = View.GONE
        val response1 = result
    }

    private fun onError(t: Throwable) {
        Toast.makeText(activity, t.message, Toast.LENGTH_SHORT).show()
    }
}