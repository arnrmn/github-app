package com.arnrmn.githubapp.app

import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AndroidInjectionModule::class, ActivityContributorsModule::class]
)
interface AppComponent : AndroidInjector<GithubApp> {
    @Component.Factory
    interface Factory : AndroidInjector.Factory<GithubApp>
}