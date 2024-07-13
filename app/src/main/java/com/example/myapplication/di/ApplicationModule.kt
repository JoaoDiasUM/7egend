package com.example.myapplication.di

import com.example.myapplication.data.repository.MessagingRepositoryImpl
import com.example.myapplication.domain.repository.MessagingRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class ApplicationModule {

    @Binds
    abstract fun provideMessagingRepository(
        messagingRepositoryImpl: MessagingRepositoryImpl,
    ): MessagingRepository
}