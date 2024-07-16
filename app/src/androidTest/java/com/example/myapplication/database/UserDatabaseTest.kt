package com.example.myapplication.database

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.example.myapplication.feature_chat.data.room.dao.UserDao
import com.example.myapplication.feature_chat.data.room.database.UserDatabase
import com.example.myapplication.feature_chat.domain.model.User
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@SmallTest
class UserDatabaseTest {

    private lateinit var database: UserDatabase
    private lateinit var userDao: UserDao

    @Before
    fun setupDatabase() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            UserDatabase::class.java
        ).build()
        userDao = database.userDao()
    }

    @After
    fun closeDatabase() {
        database.close()
    }

    @Test
    fun insertUser() = runBlocking {
        val user = User("url",1,"Joao")
        userDao.insertUser(user)
        assert(userDao.getAllUsers().contains(user))
    }
}