package com.arnrmn.githubapp.app

import com.arnrmn.githubapp.main.MainActivity
import com.arnrmn.githubapp.main.MainActivityModule
import com.arnrmn.githubapp.utils.dagger.ActivityScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityContributorsModule {
    @ActivityScope
    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    abstract fun contributeMainActivity(): MainActivity
}