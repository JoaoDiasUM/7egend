package com.example.myapplication.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myapplication.data.remote.dto.Attachment


@Dao
interface AttachmentDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAttachment(attachment: Attachment)

    @Query("SELECT * FROM attachment")
    suspend fun getAllAttachments(): List<Attachment>
}