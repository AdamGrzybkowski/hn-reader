package com.adamg.hnreader.dagger.component

import com.adamg.hnreader.dagger.module.CommentsModule
import com.adamg.hnreader.dagger.scope.PerActivity
import com.adamg.hnreader.views.comments.CommentsPresenter
import dagger.Component

@PerActivity
@Component(modules = arrayOf(CommentsModule::class), dependencies = arrayOf(ApplicationComponent::class))
interface CommentsComponent {
    fun presenter(): CommentsPresenter
}