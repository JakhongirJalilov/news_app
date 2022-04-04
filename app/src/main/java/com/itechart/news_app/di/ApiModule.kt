package com.itechart.news_app.di

import android.util.Log
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.itechart.news_app.data.api.NewsService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val apiModule = module {
    single {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC)
        loggingInterceptor
    }
    single {
        OkHttpClient.Builder()
            .addInterceptor(get<HttpLoggingInterceptor>())
            .addInterceptor(ChuckerInterceptor(get()))
            .build()
    }
    single<Retrofit> {
        Retrofit.Builder()
            .client(get())
            .baseUrl("https://newsapi.org")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

    }
    single<NewsService> {
        get<Retrofit>().create(NewsService::class.java)
    }
}