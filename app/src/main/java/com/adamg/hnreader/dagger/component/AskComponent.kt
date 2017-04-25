package com.adamg.hnreader.dagger.component

import com.adamg.hnreader.dagger.module.AskModule
import com.adamg.hnreader.dagger.scope.PerActivity
import com.adamg.hnreader.views.askview.AskPresenter
import dagger.Component

@PerActivity
@Component(modules = arrayOf(AskModule::class), dependencies = arrayOf(ApplicationComponent::class))
interface AskComponent {
    fun presenter(): AskPresenter
}