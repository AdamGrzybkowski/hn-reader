package com.adamg.hnreader.views.newstories

import com.hannesdorfmann.mosby.mvp.MvpView

interface NewStoriesView : MvpView{

    fun render(viewState: NewStoriesModel)
}