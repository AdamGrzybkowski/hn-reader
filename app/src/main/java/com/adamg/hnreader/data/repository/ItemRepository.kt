package com.adamg.hnreader.data.repository

import com.adamg.hnreader.networking.HackerNewsApi
import com.adamg.hnreader.data.dao.ItemDao
import com.adamg.hnreader.models.Item
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import javax.inject.Inject

class ItemRepository @Inject constructor(private val itemDao: ItemDao,
                                         private val hackerNewsApi: HackerNewsApi) {
    fun getItem(itemId: Long): Observable<Item> {
        val cachedItem = itemDao.getItem(itemId)

        val item = hackerNewsApi.getItem(itemId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.io())
                .doOnNext { itemDao.saveItem(it!!) }
                .flatMap { itemDao.getItemAsObservable(it.id) }

        return item.mergeWith(Observable.just(cachedItem))
    }

    fun getNewStories(page: Int = 0): Observable<List<Item>> {
        return hackerNewsApi.getNewStories(page)
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.io())
                .doOnNext { itemDao.saveItems(it!!) }
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun getAsks(page: Int = 0): Observable<List<Item>> {
        return hackerNewsApi.getAsks(page)
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.io())
                .doOnNext { itemDao.saveItems(it!!) }
                .observeOn(AndroidSchedulers.mainThread())
    }
}