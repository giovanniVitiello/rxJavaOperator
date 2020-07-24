package com.example.rxjavaretrofit.ui.third

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.rxjavaretrofit.R
import com.example.rxjavaretrofit.model.User
import com.example.rxjavaretrofit.serviceBuilder.KtorServiceBuilder
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ThirdScreen : Fragment() {

//    private lateinit var observable: Observable<List<User>>
//    private lateinit var disposable: Disposable
    private lateinit var thirdScreenViewModel: ThirdScreenViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        thirdScreenViewModel = ThirdScreenViewModel()

        thirdScreenViewModel.send(UserEvent.LoadUserList)

        observeUserState()

//        finalCall()

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_third_screen, container, false)
    }

    private fun observeUserState() {
        lifecycleScope.launch {
            thirdScreenViewModel.userState.collect { state ->
                when(state) {
                    is UserState.UserListLoaded -> onSuccess(state.userList)
                }
            }
        }
    }

//    private fun finalCall() {
////        key_id = arguments?.getInt("movie_id")!!
////        val args = DetailScreenArgs.fromBundle(requireArguments())
//
//        observable = KtorServiceBuilder.buildService().getUsers()
//
//        disposable = observable
//            .subscribeOn(Schedulers.io()) //serve per dire che vogliamo che i dati passino su un altro thread rispetto al main di Android(ui)
//            // per non rallentare il processo (Multithreading).
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe(
//                { result -> onSuccess(result) },
//                { error -> onError(error) })
//
//        val compositeDisposable = CompositeDisposable()
//
//        compositeDisposable.add(disposable)
//    }

    private fun onSuccess(result: List<User>) {
        Log.i("result", result.toString())

    }

    private fun onError(t: Throwable) {
        Toast.makeText(activity, t.message, Toast.LENGTH_SHORT).show()
    }
}