package com.adamg.hnreader.data.dao

import com.adamg.hnreader.models.Item
import com.vicpin.krealmextensions.queryAllAsObservable
import com.vicpin.krealmextensions.queryAsObservable
import rx.Observable
import javax.inject.Inject

class ItemDao @Inject constructor() {

    fun getAllItems() = Item().queryAllAsObservable()

    fun getItem(itemId: Long): Observable<Item> {
        return Item().queryAsObservable { query -> query.equalTo("id", itemId) }
                .map { it.first() }
    }
}