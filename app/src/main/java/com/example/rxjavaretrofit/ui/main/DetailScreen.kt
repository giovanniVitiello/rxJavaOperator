package com.example.rxjavaretrofit.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.rxjavaretrofit.R
import com.example.rxjavaretrofit.model.Result
import com.example.rxjavaretrofit.serviceBuilder.TmdbServiceBuilder
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class DetailScreen : Fragment() {

    private lateinit var observable: Observable<Result>
    private lateinit var disposable: Disposable


    private lateinit var photo: ImageView
    private lateinit var title: TextView
    private lateinit var overview: TextView
    private lateinit var rating: TextView

    val compositeDisposable = CompositeDisposable()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_detail_screen, container, false)

        photo = view.findViewById(R.id.detail_movie_photo)
        title = view.findViewById(R.id.detail_movie_title)
        overview = view.findViewById(R.id.detail_movie_overview)
        rating = view.findViewById(R.id.detail_movie_rating)

        finalCall()

        return view
    }


    private fun finalCall() {
//        key_id = arguments?.getInt("movie_id")!!
        val args = DetailScreenArgs.fromBundle(requireArguments())

        observable = TmdbServiceBuilder.buildService().getMovieDetail(args.keyId, getString(R.string.api_key))

        disposable = observable
            .subscribeOn(Schedulers.io()) //serve per dire che vogliamo che i dati passino su un altro thread rispetto al main di Android(ui)
            // per non rallentare il processo (Multithreading).
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result -> onSuccess(result) },
                { error -> onError(error) })

        compositeDisposable.add(disposable)
    }

    // * quando facciamo observable.subsribe, ci iscriviamo all'observable ed otteniamo un oggetto di tipo observer(o disposable
    // cioè che con rxjava2 disposable che una volta pieno di oggetti da osservare li contiene e li manda a poco a poco, ed infine
    // con la funzione dispose nell'onDestroy vengono eliminati per evitare di mantenere troppi dati in memoria -Memory leak-
    // quindi praticamente ci de-iscriviamo all'observable)


    private fun onSuccess(result: Result) {
//        progress_bar.visibility = View.GONE
        Glide
            .with(requireActivity())
            .load("https://image.tmdb.org/t/p/w500${result.poster_path}")
            .into(photo)
        title.text = "Title: "+ result.title
        overview.text = result.overview
        rating.text = "Rating : "+ result.vote_average.toString()
    }

    private fun onError(t: Throwable) {
        Toast.makeText(activity, t.message, Toast.LENGTH_SHORT).show()
    }
}