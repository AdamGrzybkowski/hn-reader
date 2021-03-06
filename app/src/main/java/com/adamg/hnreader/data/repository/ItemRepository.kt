package com.adamg.hnreader.data.repository

import com.adamg.hnreader.api.HackerNewsApi
import com.adamg.hnreader.data.dao.ItemDao
import com.adamg.hnreader.models.Item
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import javax.inject.Inject

class ItemRepository @Inject constructor(private val itemDao: ItemDao,
                                         private val hackerNewsApi: HackerNewsApi) {
    fun getItem(itemId: Long): Observable<Item> {
        return itemDao.getItem(itemId)
    }

    fun getNewStories(page: Int = 0): Observable<List<Item>> {
        return hackerNewsApi.getNewStories(page)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
    }
}