package com.adamg.hnreader.dagger.module

import com.adamg.hnreader.api.HackerNewsApi
import com.adamg.hnreader.dagger.scope.PerApplication
import com.commit451.regalia.moshi.RealmListJsonAdapterFactory
import com.facebook.stetho.okhttp3.StethoInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import com.squareup.moshi.Moshi



@Module
class ApplicationModule(private val baseUrl: String) {

    @PerApplication
    @Provides
    fun provideApiService(retrofit: Retrofit): HackerNewsApi {
        return retrofit.create(HackerNewsApi::class.java)
    }

    @PerApplication
    @Provides
    fun provideRxJavaCallAdapterFactory(): RxJavaCallAdapterFactory{
        return RxJavaCallAdapterFactory.create()
    }

    @PerApplication
    @Provides
    fun provideMoshiConverterFactory(): MoshiConverterFactory {
        val moshi = Moshi.Builder()
                .add(RealmListJsonAdapterFactory())
                .build()
        return MoshiConverterFactory.create(moshi)
    }

    @PerApplication
    @Provides
    fun provideOkHttpClient(): OkHttpClient{
        return OkHttpClient.Builder()
                .addNetworkInterceptor(StethoInterceptor())
                .build()
    }

    @PerApplication
    @Provides
    fun provideRetrofit(client: OkHttpClient, converterFactory: MoshiConverterFactory, adapterFactory: RxJavaCallAdapterFactory): Retrofit{
        return Retrofit.Builder()
                .baseUrl(baseUrl)
                .addCallAdapterFactory(adapterFactory)
                .addConverterFactory(converterFactory)
                .client(client)
                .build()
    }
}