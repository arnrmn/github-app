package com.arnrmn.githubapp.utils.android

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel : ViewModel() {
    private val disposables = CompositeDisposable()
    private val _error = MutableLiveData<String>()

    val error: LiveData<String> = _error

    public final override fun onCleared() {
        disposables.dispose()
    }

    protected fun <T> Single<T>.observe(action: (T) -> Unit) {
        disposables.add(this.subscribe(action, ::handleError))
    }

    private fun handleError(error: Throwable) {
        _error.postValue(error.message)
    }
}