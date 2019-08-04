package com.arnrmn.githubapp.repositories

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
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
        viewModel.selectedRepository.observe(::showDetails)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.title = getString(R.string.app_name)
        refreshLayout.setOnRefreshListener { viewModel.onRefreshRequested() }
        repositoriesRecyclerView.adapter = adapter
    }

    private fun showRepositories(repositories: List<Repository>) {
        adapter.update(repositories)
    }

    private fun showLoadingIndicator(isLoading: Boolean) {
        refreshLayout.isRefreshing = isLoading
    }

    private fun showDetails(repository: Repository) {
        val action = RepositoriesFragmentDirections.actionRepositoriesFragmentToDetailsFragment(repository)
        findNavController().navigate(action)
    }

}