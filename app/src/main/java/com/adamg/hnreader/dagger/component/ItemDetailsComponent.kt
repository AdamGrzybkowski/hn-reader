package com.adamg.hnreader.dagger.component

import com.adamg.hnreader.dagger.module.ItemDetailsModule
import com.adamg.hnreader.dagger.scope.PerActivity
import com.adamg.hnreader.views.activities.itemdetailactivity.ItemDetailsPresenter
import dagger.Component

@PerActivity
@Component(modules = arrayOf(ItemDetailsModule::class), dependencies = arrayOf(ApplicationComponent::class))
interface ItemDetailsComponent {
    fun presenter(): ItemDetailsPresenter
}