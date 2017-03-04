package com.adamg.hnreader.dagger.component

import com.adamg.hnreader.dagger.module.ShowsModule
import com.adamg.hnreader.dagger.scope.PerActivity
import com.adamg.hnreader.views.listfragments.newstories.ShowsPresenter
import dagger.Component

@PerActivity
@Component(modules = arrayOf(ShowsModule::class), dependencies = arrayOf(ApplicationComponent::class))
interface ShowsComponent {
    fun presenter(): ShowsPresenter
}