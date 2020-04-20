package com.adamg.hnreader.views.fragments.listfragments.newstories

import com.adamg.hnreader.views.base.BasePresenter
import com.adamg.hnreader.data.repository.ItemRepository
import com.adamg.hnreader.views.fragments.listfragments.ItemsUiModel
import com.adamg.hnreader.views.fragments.listfragments.ItemsView
import rx.lang.kotlin.subscribeBy
import javax.inject.Inject

class NewStoriesPresenter @Inject constructor(private val itemRepository: ItemRepository) : BasePresenter<ItemsView>() {

    fun loadNewStories(pullToRefresh: Boolean){
        val subscription = itemRepository.getNewStories(1)
                .map { ItemsUiModel.success(it) }
                .onErrorReturn { ItemsUiModel.error(it.message) }
                .startWith(ItemsUiModel.loading())
                .subscribeBy(
                        onNext = { view?.render(it)}
                )
        compositeSubscription?.add(subscription)
    }

}