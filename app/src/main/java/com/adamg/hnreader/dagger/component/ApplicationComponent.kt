package com.adamg.hnreader.dagger.component

import com.adamg.hnreader.api.HackerNewsApi
import com.adamg.hnreader.dagger.module.ApplicationModule
import com.adamg.hnreader.dagger.scope.PerApplication
import dagger.Component

@PerApplication
@Component(modules = kotlin.arrayOf(ApplicationModule::class))
interface ApplicationComponent{

    fun exposeHackerNewApi(): HackerNewsApi
}