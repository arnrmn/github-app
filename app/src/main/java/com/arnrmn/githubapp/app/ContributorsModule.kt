package com.arnrmn.githubapp.app

import com.arnrmn.githubapp.details.DetailsFragment
import com.arnrmn.githubapp.details.DetailsFragmentModule
import com.arnrmn.githubapp.repositories.RepositoriesFragment
import com.arnrmn.githubapp.repositories.RepositoriesFragmentModule
import com.arnrmn.githubapp.utils.dagger.FragmentScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ContributorsModule {

    @FragmentScope
    @ContributesAndroidInjector(modules = [RepositoriesFragmentModule::class])
    abstract fun contributeRepositoriesFragment(): RepositoriesFragment

    @FragmentScope
    @ContributesAndroidInjector(modules = [DetailsFragmentModule::class])
    abstract fun contributeDetailsFragment(): DetailsFragment
}