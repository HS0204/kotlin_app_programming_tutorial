package hs.tutorials.c41_extradata

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listView = findViewById<ListView>(R.id.main_list)
        val data = arrayOf<String>("android","kotlin","jetpack")
        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            data
        )
        listView.adapter = adapter

        listView.setOnItemClickListener { parent, view, position, id ->
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("id", position)
            intent.putExtra("title", data[position])
            startActivity(intent)
        }
    }
}