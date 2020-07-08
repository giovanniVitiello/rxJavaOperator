package com.example.rxjavaretrofit

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rxjavaretrofit.adapter.HotelsAdapter
import com.example.rxjavaretrofit.objectData.HotelList
import com.example.rxjavaretrofit.objectData.Users
import com.example.rxjavaretrofit.serviceBuilder.LocalHostServiceBuilder
import com.example.rxjavaretrofit.serviceBuilder.TripadvisorServiceBuilder
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_second_screen.*

class ThirdScreen : Fragment() {

    private lateinit var observable: Observable<Users>
    private lateinit var disposable: Disposable

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        finalCall()

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_third_screen, container, false)
    }

    private fun finalCall() {
//        key_id = arguments?.getInt("movie_id")!!
//        val args = DetailScreenArgs.fromBundle(requireArguments())

        observable = LocalHostServiceBuilder.buildService().getUsers()

        disposable = observable
            .subscribeOn(Schedulers.io()) //serve per dire che vogliamo che i dati passino su un altro thread rispetto al main di Android(ui)
            // per non rallentare il processo (Multithreading).
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result -> onSuccess(result) },
                { error -> onError(error) })

        val compositeDisposable = CompositeDisposable()

        compositeDisposable.add(disposable)
    }

    private fun onSuccess(result: Users) {
        Log.i("result", result.users.toString())
//        recyclerViewHotels.apply {
//            setHasFixedSize(true)
//            layoutManager = LinearLayoutManager(activity)
//            adapter = HotelsAdapter(result.data)
//        }
    }

    private fun onError(t: Throwable) {
        Toast.makeText(activity, t.message, Toast.LENGTH_SHORT).show()
    }
}