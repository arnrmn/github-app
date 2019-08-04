package com.arnrmn.githubapp.repositories

import android.os.Bundle
import android.util.Log
import com.arnrmn.githubapp.R
import com.arnrmn.githubapp.utils.android.BaseFragment
import com.arnrmn.githubapp.utils.android.ViewModelFactory
import com.arnrmn.usecase.repositories.Repository
import javax.inject.Inject

class RepositoriesFragment : BaseFragment() {
    @Inject
    lateinit var factory: ViewModelFactory<RepositoriesViewModel>
    private val viewModel: RepositoriesViewModel by lazy { viewModel(factory) }

    override val layoutId = R.layout.fragment_repositories

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.repositories.observe(::showRepositories)
        viewModel.error.observe(::showError)
    }

    private fun showRepositories(repositories: List<Repository>) {
        Log.d("Repo", repositories.size.toString())
    }
}