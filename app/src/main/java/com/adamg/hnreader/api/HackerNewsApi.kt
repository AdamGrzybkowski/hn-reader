package com.adamg.hnreader.api

import com.adamg.hnreader.models.Item
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import rx.Observable

interface HackerNewsApi {

    @GET("news")
    fun getNewStories(@Query("page") page: Int): Observable<List<Item>>

    @GET("ask")
    fun getAsks(@Query("page") page: Int): Observable<List<Item>>

    @GET("jobs")
    fun getJobs(@Query("page") page: Int): Observable<List<Item>>

    @GET("show")
    fun getShows(@Query("page") page: Int): Observable<List<Item>>

    @GET("item/{itemId}")
    fun getItem(@Path("itemId") itemId: Long): Observable<Item>

}
