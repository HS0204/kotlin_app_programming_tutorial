package hs.projects.viewmodelandlivadata

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

enum class ActionType {
    PLUS, MINUS
}

class MyNumberViewModel : ViewModel() {

    private val TAG = "MYLOG"

    // 뮤터블 라이브 데이터 - 수정 가능
    // 라이브 데이터 - 수정 불가, 읽기 전용
    // 내부에서 설정하는 자료형은 뮤터블로 변경 가능하게 함
    private val _currentValue = MutableLiveData<Int>()

    // 변경되지 않는 데이터를 가져올 때 이름을 _(언더스코어) 없이 설정
    // 공개적으로 가져오는 변수는 private 하지 말고 퍼블릭으로 외부 접근 가능하도록
    // 값을 직접 라이브 데이터에 접근하지 말고 뷰모델로 가져올 수 있도록 함
    val currentValue: LiveData<Int> get() = _currentValue

    init {
        Log.d(TAG, "MyNumberViewModel - 생성자 호출")
        _currentValue.value = 0
    }

    fun updateValue(actionType: ActionType, input: Int) {
        when(actionType) {
            ActionType.PLUS ->
                _currentValue.value = _currentValue.value?.plus(input)
            ActionType.MINUS ->
                _currentValue.value = _currentValue.value?.minus(input)
        }
    }
}