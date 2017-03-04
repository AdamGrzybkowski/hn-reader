package com.adamg.hnreader.dagger.component

import com.adamg.hnreader.dagger.module.AsksModule
import com.adamg.hnreader.dagger.scope.PerActivity
import com.adamg.hnreader.views.listfragments.newstories.AsksPresenter
import dagger.Component

@PerActivity
@Component(modules = arrayOf(AsksModule::class), dependencies = arrayOf(ApplicationComponent::class))
interface AsksComponent {
    fun presenter(): AsksPresenter
}