package com.adamg.hnreader.dagger.module

import com.adamg.hnreader.api.HackerNewsApi
import com.adamg.hnreader.dagger.scope.PerApplication
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class ApplicationModule(val baseUrl: String) {

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
    fun provideGsonConverterFactory(): GsonConverterFactory {
        val gson = GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create()
        return GsonConverterFactory.create(gson)
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
    fun provideRetrofit(client: OkHttpClient, converterFactory: GsonConverterFactory, adapterFactory: RxJavaCallAdapterFactory): Retrofit{
        return Retrofit.Builder()
                .baseUrl(baseUrl)
                .addCallAdapterFactory(adapterFactory)
                .addConverterFactory(converterFactory)
                .client(client)
                .build()
    }
}