package com.arnrmn.githubapp.app

import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AndroidSupportInjectionModule::class, ActivityContributorsModule::class]
)
interface AppComponent : AndroidInjector<GithubApp> {
    @Component.Factory
    interface Factory : AndroidInjector.Factory<GithubApp>
}