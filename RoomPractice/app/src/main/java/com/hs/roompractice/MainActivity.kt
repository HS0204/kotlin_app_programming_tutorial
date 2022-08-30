package com.hs.roompractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log.i
import android.view.View
import androidx.room.Room
import com.hs.roompractice.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private lateinit var db: UserProfileDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        db = Room.databaseBuilder(
            applicationContext,
            UserProfileDatabase::class.java,
            "user_profile"
        ).allowMainThreadQueries().build()

        fetchUserProfileList()

        binding.add.setOnClickListener {
            addUserProfile()
        }
    }

    private fun fetchUserProfileList() {
        val userProfileList = db.getUserProfileDao().getAll()
        var userListText = "사용자 목록"

        for (userProfile in userProfileList) {
            userListText += "\n" + userProfile.id + ", " + userProfile.name + ", " + userProfile.phone + ", " + userProfile.address
        }

        binding.userList.setText(userListText)
    }

    private fun addUserProfile() {
        val name = binding.name.text.toString()
        val phone = binding.phone.text.toString()
        val address = binding.address.text.toString()

        val userProfile = UserProfile(name = name, phone = phone, address = address)
        db.getUserProfileDao().insert(userProfile)

        fetchUserProfileList()
    }
}