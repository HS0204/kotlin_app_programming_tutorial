package com.hs.roomwitharchitecture.data

import androidx.lifecycle.LiveData

class UserRepository(private val userDao: UserDao) {

    val realAllData: LiveData<List<User>> = userDao.readAllData()

    suspend fun addUser(user: User) {
        userDao.addUser(user)
    }
}