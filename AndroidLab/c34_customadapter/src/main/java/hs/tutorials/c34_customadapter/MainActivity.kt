package hs.tutorials.c34_customadapter

import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 데이터 준비
        val mutableList = mutableListOf<DriveVO>()
        mutableList.add(DriveVO("안드로이드", "5월 19일","doc"))
        mutableList.add(DriveVO("db.zip", "5월 19일","file"))
        mutableList.add(DriveVO("이미지", "5월 19일","img"))

        // 어댑터 사용
        val listView = findViewById<ListView>(R.id.custom_list)
        val adapter = DriveAdapter(this, R.layout.custom_item, mutableList)
        listView.adapter = adapter
    }
}