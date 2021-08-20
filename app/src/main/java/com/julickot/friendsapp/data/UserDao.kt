package com.julickot.friendsapp.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

//    @Query("SELECT * FROM users WHERE id = :userId")
//    suspend fun getUser(userId: Int): User

    @Query("SELECT * FROM users")
    fun getAll(): Flow<List<User>>

    @Query("DELETE FROM users")
    suspend fun clear()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAll(users: List<User>)
}