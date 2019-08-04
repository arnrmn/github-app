package com.arnrmn.githubapp.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.arnrmn.githubapp.utils.android.BaseViewModel
import com.arnrmn.githubapp.utils.android.SingleLiveData
import com.arnrmn.usecase.repositories.Repository
import com.arnrmn.usecase.repositories.ToptalRepositoriesUseCase
import javax.inject.Inject

class RepositoriesViewModel @Inject constructor(
    private val repositoriesUseCase: ToptalRepositoriesUseCase
) : BaseViewModel() {

    private val _repositories = MutableLiveData<List<Repository>>()
    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData()
    private val _selectedRepository: SingleLiveData<Repository> = SingleLiveData()

    val repositories: LiveData<List<Repository>> = _repositories
    val isLoading: LiveData<Boolean> = _isLoading
    val selectedRepository: LiveData<Repository> = _selectedRepository

    init {
        loadRepositories()
    }

    fun onRefreshRequested() {
        loadRepositories()
    }

    fun onRepositorySelected(repository: Repository) {
        _selectedRepository.postValue(repository)
    }

    private fun loadRepositories() {
        repositoriesUseCase.getRepositories()
            .doOnSubscribe { _isLoading.postValue(true) }
            .doOnEvent { _, _ -> _isLoading.postValue(false) }
            .observe(_repositories::postValue)
    }
}