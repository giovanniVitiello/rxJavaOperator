package com.example.rxjavaretrofit.ui.third

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.rxjavaretrofit.model.User
import com.example.rxjavaretrofit.serviceBuilder.KtorServiceBuilder
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow

sealed class UserState {
    object UsersLoading : UserState ()
    data class UserListLoaded(val userList: List<User>) : UserState()
    data class UserLoaded(val user: User) : UserState()
}
sealed class UserEvent {
    object LoadUserList : UserEvent()
}
class ThirdScreenViewModel : ViewModel() {

    @ExperimentalCoroutinesApi
    var userState : MutableStateFlow<UserState> = MutableStateFlow(UserState.UsersLoading)

    fun send(event : UserEvent) {
        when (event) {
            UserEvent.LoadUserList -> loadUserList()
        }
    }
    @ExperimentalCoroutinesApi
    private fun loadUserList() {
        val compositeDisposable = CompositeDisposable()
        val observable = KtorServiceBuilder.buildService().getUsers()
        val disposable = observable
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(
                { response -> userState.value = UserState.UserListLoaded(response) },
                { t -> Log.i("error loading users ", "$t") }
            )
        compositeDisposable.add(disposable)
    }
//    private fun loadUser(userId: Int) {
//        val observable = KtorServiceBuilder.buildService().getUser(/*"0"*/)
//        val disposable = observable
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribeOn(Schedulers.io())
//            .subscribe(
//                { response -> userState.value = UserState.UserLoaded() },
//                { t -> Log.i("error loading user ", "$t") }
//            )
//    }
}