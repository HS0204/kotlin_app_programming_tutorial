package com.hs.roomwitharchitecture.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.hs.roomwitharchitecture.data.UserDatabase
import com.hs.roomwitharchitecture.repository.UserRepository
import com.hs.roomwitharchitecture.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

// context를 활용해야 하는 경우에 AndroidViewModel을 필수로 쓰자
class UserViewModel(application: Application): AndroidViewModel(application) {
    // repository와 UI를 연결시키는 커뮤니케이션 센터와 같은 역할을 하게된다.

    val readAllData: LiveData<List<User>>
    private val repository: UserRepository

    init {
        val userDao = UserDatabase.getDatabase(application).userDao()
        repository = UserRepository(userDao)
        readAllData = repository.realAllData
    }

    fun addUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            // ViewModel에서 코루틴을 활용하기 위한 ViewModelScope
            // Dispatcher.IO는 이 코드는 백그라운드 스레드에서 돌리겠다는 뜻이다.
            // 메인스레드에서 데이터베이스 작업을 하면 매우 나쁜 환경이다!
            repository.addUser(user)
        }
    }

    fun updateUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateUpdate(user)
        }
    }
}