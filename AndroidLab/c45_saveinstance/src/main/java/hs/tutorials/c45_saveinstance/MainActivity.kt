package hs.tutorials.c45_saveinstance

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    var count = 0
    lateinit var editView: EditText
    lateinit var countView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        countView = findViewById(R.id.countView)
        val button = findViewById<Button>(R.id.button)
        editView = findViewById(R.id.edit)

        button.setOnClickListener {
            count ++
            countView.text = "$count"
        }
    }

    // 종료 전 상태 데이터 저장 함수
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("count", count)
        outState.putString("edit", editView.text.toString())
    }

    // 재생성 시 상태 데이터 복원 함수
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        count = savedInstanceState.getInt("count")
        countView.setText("$count")
        editView.setText(savedInstanceState.getString("edit"))
    }

}