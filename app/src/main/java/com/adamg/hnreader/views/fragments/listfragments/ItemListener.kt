package com.adamg.hnreader.views.fragments.listfragments

import com.adamg.hnreader.models.Item

interface ItemListener {
    fun onItemClicked(item: Item)
}