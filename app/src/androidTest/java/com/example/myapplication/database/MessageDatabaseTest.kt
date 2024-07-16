package com.example.myapplication.database

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.example.myapplication.feature_chat.data.room.dao.MessageDao
import com.example.myapplication.feature_chat.data.room.database.MessageDatabase
import com.example.myapplication.feature_chat.domain.model.Message
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@SmallTest
class MessageDatabaseTest {

    private lateinit var database: MessageDatabase
    private lateinit var messageDao: MessageDao

    @Before
    fun setupDatabase() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            MessageDatabase::class.java
        ).build()
        messageDao = database.messageDao()
    }

    @After
    fun closeDatabase() {
        database.close()
    }

    @Test
    fun insertUser() = runBlocking {
        val message = Message(emptyList(),"url",1,1)
        messageDao.insertMessage(message)
        assert(messageDao.getAllMessages().contains(message))
    }
}