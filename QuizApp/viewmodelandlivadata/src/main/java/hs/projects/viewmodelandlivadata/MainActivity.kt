package hs.projects.viewmodelandlivadata

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import hs.projects.viewmodelandlivadata.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    lateinit var myNumberViewModel: MyNumberViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // 뷰 모델 프로바이더로 뷰 모델 가져오기
        // 라이프사이클을 가지고 있는 것을 넘겨 줌 = 자기 자신
        myNumberViewModel = ViewModelProvider(this).get(MyNumberViewModel::class.java)

        // 뷰 모델이 가지고 있는 값의 변경사항을 관찰할 수 있는 라이브 데이터 옵저빙
        myNumberViewModel.currentValue.observe(this, Observer {
            Log.d(TAG, "MainActivity - myNumberViewModel - currentValue 라이브 데이터 값 변경 : $it")
            binding.numberTextView.text = it.toString()
        })

        // 리스너 연결
        binding.plusBtn.setOnClickListener(this)
        binding.minusBtn.setOnClickListener(this)
    }

    companion object {
        private const val TAG = "MYLOG"
    }

    override fun onClick(view: View?) {
        val userInput = binding.userinputEdittext.text.toString().toInt()

        // 뷰 모델이 가진 라이브 데이터 값을 변경하는 메서드
        when(view) {
            binding.plusBtn ->
                myNumberViewModel.updateValue(actionType = ActionType.PLUS, userInput)
            binding.minusBtn ->
                myNumberViewModel.updateValue(actionType = ActionType.MINUS, userInput)
        }
    }
}