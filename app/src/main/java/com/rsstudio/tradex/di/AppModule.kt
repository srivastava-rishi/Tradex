package com.rsstudio.tradex.di

import com.google.gson.GsonBuilder
import com.rsstudio.tradex.data.restclient.AppApiClientService
import com.rsstudio.tradex.util.CoreUtil
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideAppApiClientService(): AppApiClientService =
        Retrofit.Builder()
            .baseUrl(CoreUtil.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(AppApiClientService::class.java)
}