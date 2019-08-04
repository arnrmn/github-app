package com.arnrmn.githubapp.app

import com.arnrmn.githubapp.utils.usecases.UseCasesModule
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        AndroidSupportInjectionModule::class,
        ContributorsModule::class,
        UseCasesModule::class
    ]
)
interface AppComponent : AndroidInjector<GithubApp> {
    @Component.Factory
    interface Factory : AndroidInjector.Factory<GithubApp>
}