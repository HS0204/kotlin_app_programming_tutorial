package hs.tutorials.c42_activityresult

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    lateinit var resultView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button1 = findViewById<Button>(R.id.button1)
        val button2 = findViewById<Button>(R.id.button2)
        resultView = findViewById(R.id.resultView)

        // 구버전
        button1.setOnClickListener {
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("id", "old version")
            startActivityForResult(intent, 10) // 수행하고 돌아왔을 때 onAcivitiyResult 자동 콜
        }

        // 11버전부터
        val resultLauncher: ActivityResultLauncher<Intent> = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()){
            resultView.text = "result : ${it.data?.getStringExtra("result")}" // 되돌아왔을 때 사후처리되는 부분
        }
        button2.setOnClickListener {
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("id", "new version")
            resultLauncher.launch(intent)
        }
    }

    // 구버전
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 10 && resultCode == RESULT_OK){
            val result: String? = data?.getStringExtra("result")
            resultView.text = "result : $result"
        }
    }
}