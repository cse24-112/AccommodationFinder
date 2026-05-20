package com.example.accommodationfinder.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface MessageDao {

    @Insert
    suspend fun insert(message: Message): Long

    @Query("""
        SELECT * FROM messages 
        WHERE (senderId = :userId OR receiverId = :userId)
        AND listingId = :listingId
        ORDER BY timestamp ASC
    """)
    fun getMessagesForListing(
        userId: Int,
        listingId: Int
    ): Flow<List<Message>>

    @Query("""
        SELECT * FROM messages 
        WHERE (senderId = :userId1 AND receiverId = :userId2)
        OR (senderId = :userId2 AND receiverId = :userId1)
        ORDER BY timestamp DESC
    """)
    fun getConversation(
        userId1: Int,
        userId2: Int
    ): Flow<List<Message>>

    @Query("SELECT * FROM messages ORDER BY timestamp DESC")
    fun getAllMessages(): Flow<List<Message>>

    @Delete
    suspend fun delete(message: Message)
}