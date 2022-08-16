package hs.project.quizapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
<<<<<<< Updated upstream
=======
import android.util.Log
import android.view.View
>>>>>>> Stashed changes
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import hs.project.quizapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    private lateinit var trueBtn: Button
    private lateinit var falseBtn: Button
    private lateinit var prevBtn: ImageButton
    private lateinit var nextBtn: ImageButton
    private lateinit var questionTxtView: TextView

    private val questionBank = listOf(
        Question(R.string.question_australia, true),
        Question(R.string.question_oceans, true),
        Question(R.string.question_mideast, false),
        Question(R.string.question_africa, false),
        Question(R.string.question_americas, true),
        Question(R.string.question_asia, true)
    )

    private var checkAnswer: ArrayList<CheckAnswer> = arrayListOf()
    private var collectAnswer = 0

    private var currentIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        trueBtn = binding.trueBtn
        falseBtn = binding.falseBtn
        prevBtn = binding.prevBtn
        nextBtn = binding.nextBtn
        questionTxtView = binding.questionTxtView

        questionTxtView.setOnClickListener {
            currentIndex = (currentIndex + 1) % questionBank.size

            updateQuestion()
        }

        trueBtn.setOnClickListener {
            checkAnswer(true)

            calScore()
        }

        falseBtn.setOnClickListener {
            checkAnswer(false)

            calScore()
        }

        prevBtn.setOnClickListener {
            currentIndex = (currentIndex - 1) % questionBank.size

            if (currentIndex < 0) currentIndex = questionBank.size - 1

            updateQuestion()
        }

        nextBtn.setOnClickListener {
            currentIndex = (currentIndex + 1) % questionBank.size

            updateQuestion()
        }

        updateQuestion()
    }

    private fun updateQuestion() {
        val questionTxtResId = questionBank[currentIndex].textResId
        questionTxtView.setText(questionTxtResId)

        if (currentIndex >= checkAnswer.size)
            checkAnswer.add(currentIndex, CheckAnswer(false))

        if (checkAnswer[currentIndex].answer) {
            trueBtn.visibility = View.INVISIBLE
            falseBtn.visibility = View.INVISIBLE
        } else {
            trueBtn.visibility = View.VISIBLE
            falseBtn.visibility = View.VISIBLE
        }
    }

    private fun checkAnswer(userAnswer: Boolean) {
        val correctAnswer = questionBank[currentIndex].answer

        if (userAnswer == correctAnswer) {
            checkAnswer.add(currentIndex, CheckAnswer(true))
            collectAnswer += 1
        } else {
            checkAnswer.add(currentIndex, CheckAnswer(false))
        }

        val messageResId = if (userAnswer == correctAnswer) {
            R.string.correct_toast
        } else {
            R.string.incorrect_toast
        }

        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show()
    }
<<<<<<< Updated upstream
=======

    private fun calScore() {
        if (currentIndex == questionBank.size - 1) {
            val score = (collectAnswer.toDouble() / questionBank.size.toDouble()) * 100
            Toast.makeText(this, "점수 백분율 환산 : ${score.toInt()}%", Toast.LENGTH_SHORT).show()
        }
    }

    companion object {
        private const val TAG = "MainActivity"
    }
>>>>>>> Stashed changes
}