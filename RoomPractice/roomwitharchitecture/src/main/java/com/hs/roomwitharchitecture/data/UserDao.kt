package com.hs.roomwitharchitecture.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.hs.roomwitharchitecture.model.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user: User) // suspend를 붙인 이유는 코루틴 때문에

    @Update
    suspend fun updateUser(user: User)

    @Query("SELECT * FROM user_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<User>>
}