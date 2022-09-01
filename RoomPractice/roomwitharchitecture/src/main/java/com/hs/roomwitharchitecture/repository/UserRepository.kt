package com.hs.roomwitharchitecture.repository

import androidx.lifecycle.LiveData
import com.hs.roomwitharchitecture.data.UserDao
import com.hs.roomwitharchitecture.model.User

class UserRepository(private val userDao: UserDao) {

    val realAllData: LiveData<List<User>> = userDao.readAllData()

    suspend fun addUser(user: User) {
        userDao.addUser(user)
    }

    suspend fun updateUpdate(user: User) {
        userDao.updateUser(user)
    }
}