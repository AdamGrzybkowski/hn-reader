package com.adamg.hnreader.dagger.component

import com.adamg.hnreader.dagger.module.JobsModule
import com.adamg.hnreader.dagger.scope.PerActivity
import com.adamg.hnreader.views.listfragments.newstories.JobsPresenter
import dagger.Component

@PerActivity
@Component(modules = arrayOf(JobsModule::class), dependencies = arrayOf(ApplicationComponent::class))
interface JobsComponent{
    fun presenter(): JobsPresenter
}