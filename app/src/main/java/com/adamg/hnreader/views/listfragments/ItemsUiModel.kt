package com.adamg.hnreader.views.listfragments

import com.adamg.hnreader.models.Item

data class ItemsUiModel(private val isLoading: Boolean = false,
                        private val items: List<Item>? = null,
                        private val error: String? = null) {

    fun isLoading() = isLoading
    fun isError() = error != null
    fun isSuccess() = items != null && items.isNotEmpty()
    fun isEmpty() = items != null && items.isEmpty()

    fun getItems(): List<Item> {
        return items!!
    }

    fun getError(): String {
        return error!!
    }

    companion object {
        fun loading() = ItemsUiModel(isLoading = true)
        fun success(items: List<Item>) = ItemsUiModel(items = items)
        fun error(error: String?) = ItemsUiModel(error = error)
    }

}