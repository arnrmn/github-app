package com.arnrmn.githubapp.repositories

import android.os.Bundle
import android.view.View
import com.arnrmn.githubapp.R
import com.arnrmn.githubapp.utils.android.BaseFragment
import com.arnrmn.githubapp.utils.android.ViewModelFactory
import com.arnrmn.usecase.repositories.Repository
import kotlinx.android.synthetic.main.fragment_repositories.*
import javax.inject.Inject

class RepositoriesFragment : BaseFragment() {
    @Inject
    lateinit var factory: ViewModelFactory<RepositoriesViewModel>
    private val viewModel: RepositoriesViewModel by lazy { viewModel(factory) }
    private val adapter = RepositoriesAdapter { repository -> viewModel.onRepositorySelected(repository) }

    override val layoutId = R.layout.fragment_repositories

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.repositories.observe(::showRepositories)
        viewModel.error.observe(::showError)
        viewModel.isLoading.observe(::showLoadingIndicator)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        refreshLayout.setOnRefreshListener { viewModel.onRefreshRequested() }
        repositoriesRecyclerView.adapter = adapter
    }

    private fun showRepositories(repositories: List<Repository>) {
        adapter.update(repositories)
    }

    private fun showLoadingIndicator(isLoading: Boolean) {
        progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        if (!isLoading) refreshLayout.isRefreshing = false
    }

}