package hs.tutorials.c33_listview

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    val todos = mutableListOf<String>()
    lateinit var adapter: ArrayAdapter<String>
    lateinit var listView: ListView
    lateinit var editText: EditText
    lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listView = findViewById(R.id.main_list)
        editText = findViewById(R.id.edit)
        button = findViewById(R.id.button)
        
        adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            todos
        )
        listView.adapter = adapter

        // 리스트뷰를 클릭했을 때 일어나는 이벤트
        listView.setOnItemClickListener { adapterView, view, i, l ->
            AlertDialog.Builder(this)
                .setTitle("remove todo")
                .setPositiveButton("OK"){dialog, which ->
                    // OK를 눌렀을 때 삭제됨
                    todos.removeAt(i)
                    adapter.notifyDataSetChanged()
                }
                .setNegativeButton("Cancel", null)
                .create()
                .show()
        }

        // 버튼 클릭 시 일어나는 이벤트 ---> todo리스트 추가
        button.setOnClickListener {
            todos.add(editText.text.toString())
            editText.text.clear()
            adapter.notifyDataSetChanged()
        }
    }
}