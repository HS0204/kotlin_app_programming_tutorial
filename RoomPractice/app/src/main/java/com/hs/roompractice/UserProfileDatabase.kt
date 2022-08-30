package com.hs.roompractice

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [UserProfile::class], version = 1)
abstract class UserProfileDatabase: RoomDatabase() {
    abstract fun getUserProfileDao() : UserProfileDao
}