package com.adamg.hnreader.dagger.component

import com.adamg.hnreader.dagger.module.NewStoriesModule
import com.adamg.hnreader.dagger.scope.PerActivity
import com.adamg.hnreader.views.newstories.NewStoriesPresenter
import dagger.Component

@PerActivity
@Component(modules = arrayOf(NewStoriesModule::class), dependencies = arrayOf(ApplicationComponent::class))
interface NewStoriesComponent {
    fun presenter(): NewStoriesPresenter
}