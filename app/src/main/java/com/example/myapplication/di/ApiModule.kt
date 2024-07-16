package com.example.myapplication.di

import com.example.myapplication.feature_chat.data.remote.MessagingApiService
import com.example.myapplication.feature_chat.data.repository.MessagingRepositoryImpl
import com.example.myapplication.feature_chat.domain.repository.MessagingRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {
    private const val BASE_URL =
        "https://private-edd460-7egendchallengeandroid.apiary-mock.com/"

    @Singleton
    @Provides
    fun providesHttpClient(): OkHttpClient =
        OkHttpClient
            .Builder()
            .connectTimeout(60000, TimeUnit.MILLISECONDS)
            .readTimeout(60000, TimeUnit.MILLISECONDS)
            .writeTimeout(60000, TimeUnit.MILLISECONDS)
            .build()

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .build()

    @Singleton
    @Provides
    fun provideMessagingApi(retrofit: Retrofit): MessagingApiService {
        return retrofit.create(MessagingApiService::class.java)
    }
}