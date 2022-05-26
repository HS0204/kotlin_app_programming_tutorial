package hs.tutorials.c49_coroutine

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

class MainActivity : AppCompatActivity() {

    // 코루틴 스코프 선언 --> 다량 연산(CPU 작업) 목적이기 때문에 Defualt. 화면 건드린다면 Main
    var backgroundScope = CoroutineScope(Dispatchers.Default+ Job())

    lateinit var button: Button
    lateinit var resultView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button = findViewById(R.id.button)
        resultView = findViewById(R.id.resultView)

        button.setOnClickListener {
            backgroundScope.launch {
                var sum = 0L
                var time = measureTimeMillis {
                    for(i in 1..2_000_000_000){
                        sum += i
                    }
                }
                withContext(Dispatchers.Main){
                    // Default 디스패처가 결과값을 전달할 수 없기 때문에 Main 디스패처에 의뢰
                    resultView.text = "sum: $sum"
                }
            }
        }
    }
}