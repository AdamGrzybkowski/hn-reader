package com.adamg.hnreader.data.dao

import com.adamg.hnreader.models.Item
import com.vicpin.krealmextensions.*
import rx.Observable
import javax.inject.Inject

class ItemDao @Inject constructor() {

    fun getAllItems() = Item().queryAllAsObservable()

    fun getItem(itemId: Long) = Item().queryFirst { query -> query.equalTo("id", itemId) }

    fun getItemAsObservable(itemId: Long): Observable<Item> {
        return Item().queryAsObservable { query -> query.equalTo("id", itemId) }
                .map { it.first() }
    }

    fun saveItems(items: List<Item>) {
        items.saveAll()
    }

    fun saveItem(item: Item) {
        item.save()
    }


}