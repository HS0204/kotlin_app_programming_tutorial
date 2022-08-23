package hs.project.quizapp

import android.app.Activity
import android.app.ActivityOptions
import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModelProvider
import hs.project.quizapp.databinding.ActivityMainBinding

private const val TAG = "MainActivity"
private const val KEY_INDEX = "index"
private const val REQUEST_CODE_CHEAT = 0

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    private lateinit var trueBtn: Button
    private lateinit var falseBtn: Button
    private lateinit var nextBtn: Button
    private lateinit var cheatBtn: Button
    private lateinit var questionTxtView: TextView

    private lateinit var resultLauncher: ActivityResultLauncher<Intent>
    private val quizViewModel: QuizViewModel by lazy { ViewModelProvider(this).get(QuizViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate(Bundle?) called")
        setContentView(binding.root)

        val currentIndex = savedInstanceState?.getInt(KEY_INDEX, 0) ?: 0
        quizViewModel.currentIndex = currentIndex

        trueBtn = binding.trueBtn
        falseBtn = binding.falseBtn
        nextBtn = binding.nextBtn
        cheatBtn = binding.cheatBtn
        questionTxtView = binding.questionTxtView

        trueBtn.setOnClickListener {
            checkAnswer(true)
        }

        falseBtn.setOnClickListener {
            checkAnswer(false)
        }

        nextBtn.setOnClickListener {
            quizViewModel.moveToNext()

            updateQuestion()
        }

        resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == RESULT_OK) {
                val code = it.data?.getIntExtra("code", REQUEST_CODE_CHEAT)

                Log.d("TEST", "$code")
                if (code == REQUEST_CODE_CHEAT) {
                    quizViewModel.isCheater = it.data?.getBooleanExtra(EXTRA_ANSWER_SHOWN, false) ?: false
                }
            }
        }

        cheatBtn.setOnClickListener {
            val answerIsTrue = quizViewModel.currentQuestionAnswer
            val intent = CheatActivity.newIntent(this, answerIsTrue)

            resultLauncher.launch(intent)
        }

        updateQuestion()
    }


    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart() called")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume() called")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause() called")
    }

    override fun onSaveInstanceState(savedInstanceState: Bundle) {
        super.onSaveInstanceState(savedInstanceState)
        Log.d(TAG, "onSaveInstanceState")
        savedInstanceState.putInt(KEY_INDEX, quizViewModel.currentIndex)
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop() called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy() called")
    }

    private fun updateQuestion() {
        val questionTxtResId = quizViewModel.currentQuestionText
        questionTxtView.setText(questionTxtResId)
    }

    private fun checkAnswer(userAnswer: Boolean) {
        val correctAnswer = quizViewModel.currentQuestionAnswer

        val messageResId = when {
            quizViewModel.isCheater -> R.string.judgment_toast
            userAnswer == correctAnswer -> R.string.correct_toast
            else -> R.string.incorrect_toast
        }

        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show()
    }
}