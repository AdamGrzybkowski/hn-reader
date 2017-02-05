package com.adamg.hnreader.networking

import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import rx.Observable

interface HackerNewsApi {

    @GET("newstories.json")
    fun getNewStories(): Observable<List<Int>>

    @GET("item/{itemId}.json")
    fun getItem(@Path("itemId") itemId: String)

    companion object {
        fun create() :HackerNewsApi {
            val retrofit  = Retrofit.Builder()
                    .baseUrl("https://hacker-news.firebaseio.com/v0/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build()

            return retrofit.create(HackerNewsApi::class.java)
        }
    }

}
