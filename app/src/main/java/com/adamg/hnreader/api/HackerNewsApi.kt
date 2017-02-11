package com.adamg.hnreader.api

import com.adamg.hnreader.models.Story
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import rx.Observable

interface HackerNewsApi {

    @GET("news")
    fun getNewStories(@Query("page") page: Int): Observable<List<Story>>

    @GET("item/{itemId}")
    fun getItem(@Path("itemId") itemId: String)

}
