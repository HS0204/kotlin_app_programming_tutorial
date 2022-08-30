package com.hs.roomwitharchitecture.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class UserDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {
        // 데이터베이스는 클래스에 하나의 인스턴스를 가지니까 싱글톤으로 만들자

        @Volatile // 다른 스레드에서 활용되어도 즉각적으로 보이게 해준다. 변수 선언시 이 어노테이션이 지정되었을 때 값을 메인 메모리에 적재하기 때문이다.
        private var INSTANCE: UserDatabase? = null

        fun getDatabase(context: Context): UserDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) { // 지금 우리 데이터베이스가 준비되었을 때, 즉 존재할 때
                return tempInstance
            }
            synchronized(this) { // 지금 우리 데이터베이스가 null로 준비되지 않았을 때
                // 싱글톤의 중복생성을 방지하기 위해서 synchronized을 사용했다.
                // 여러 스레드에서 동시에 이곳에 안전하게 액세스할 수 있기 때문이다.
                // 여러 개의 인스턴스가 작동하게 되면 퍼포먼스에 있어 굉장히 고비용이다.
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UserDatabase::class.java,
                    "user_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }

}