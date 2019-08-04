package com.arnrmn.githubapp.utils.android

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.snackbar.Snackbar
import dagger.android.support.AndroidSupportInjection

abstract class BaseFragment : Fragment() {

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(layoutId, container, false)
    }

    protected inline fun <reified T : ViewModel> viewModel(factory: ViewModelFactory<T>): T {
        return ViewModelProviders.of(this, factory).get(T::class.java)
    }

    protected inline fun <reified T> LiveData<T>.observe(crossinline action: (T) -> Unit) {
        observe(this@BaseFragment, Observer { data -> data?.let(action) })
    }

    protected fun showError(message: String) {
        view?.let { view -> Snackbar.make(view, message, Snackbar.LENGTH_LONG).show() }
    }

    protected abstract val layoutId: Int
}