package com.example.myapplication.di

import android.content.Context
import androidx.room.Room
import com.example.myapplication.feature_chat.data.repository.DatabasesRepositoryImpl
import com.example.myapplication.feature_chat.data.room.dao.MessageDao
import com.example.myapplication.feature_chat.data.room.dao.UserDao
import com.example.myapplication.feature_chat.data.room.database.MessageDatabase
import com.example.myapplication.feature_chat.data.room.database.UserDatabase
import com.example.myapplication.feature_chat.domain.repository.DatabaseRepository
import com.example.myapplication.feature_chat.domain.usecase.ChatUseCases
import com.example.myapplication.feature_chat.domain.usecase.GetAllMessages
import com.example.myapplication.feature_chat.domain.usecase.GetAllUsers
import com.example.myapplication.feature_chat.domain.usecase.InsertAllMessages
import com.example.myapplication.feature_chat.domain.usecase.InsertAllUsers
import com.example.myapplication.feature_chat.domain.usecase.InsertMessage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideUserDatabase(@ApplicationContext appContext: Context): UserDatabase {
        return Room.databaseBuilder(
            appContext,
            UserDatabase::class.java,
            "user_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideMessageDatabase(@ApplicationContext appContext: Context): MessageDatabase {
        return Room.databaseBuilder(
            appContext,
            MessageDatabase::class.java,
            "message_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideUserDao(userDatabase: UserDatabase): UserDao {
        return userDatabase.userDao()
    }

    @Provides
    @Singleton
    fun provideMessageDao(messageDatabase: MessageDatabase): MessageDao {
        return messageDatabase.messageDao()
    }

    @Provides
    @Singleton
    fun provideDatabaseRepository(userDB: UserDatabase, messageDB: MessageDatabase): DatabaseRepository {
        return DatabasesRepositoryImpl(userDB.userDao(),messageDB.messageDao())
    }

    @Provides
    @Singleton
    fun provideChatUseCases(databaseRepository: DatabaseRepository): ChatUseCases {
        return ChatUseCases(
            insertAllUsers = InsertAllUsers(databaseRepository),
            getAllUsers = GetAllUsers(databaseRepository),
            getAllMessages = GetAllMessages(databaseRepository),
            insertMessage = InsertMessage(databaseRepository),
            insertAllMessages = InsertAllMessages(databaseRepository)
        )
    }
}