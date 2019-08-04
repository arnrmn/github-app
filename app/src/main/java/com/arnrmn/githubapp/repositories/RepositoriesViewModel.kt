package com.arnrmn.githubapp.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.arnrmn.githubapp.utils.android.BaseViewModel
import com.arnrmn.usecase.repositories.Repository
import com.arnrmn.usecase.repositories.ToptalRepositoriesUseCase
import javax.inject.Inject

class RepositoriesViewModel @Inject constructor(
    private val repositoriesUseCase: ToptalRepositoriesUseCase
) : BaseViewModel() {

    private val _repositories = MutableLiveData<List<Repository>>()
    val repositories: LiveData<List<Repository>> = _repositories

    init {
        loadRepositories()
    }

    private fun loadRepositories() {
        repositoriesUseCase.getRepositories().observe(_repositories::postValue)
    }
}