package com.hs.roompractice

import androidx.room.*

@Dao
interface UserProfileDao {
    @Insert
    fun insert(userProfile: UserProfile)

    @Update
    fun update(userProfile: UserProfile)

    @Delete
    fun delete(userProfile: UserProfile)

    @Query("SELECT * FROM UserProfile")
    fun getAll(): List<UserProfile>
}