package com.example.android.unscramble.ui.game

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {

    private var _score = MutableLiveData(0)
    val score: LiveData<Int> get() = _score

    private var _currentWordCount = MutableLiveData(0)
    val currentWordCount: LiveData<Int> get() = _currentWordCount

    private val _currentScrambledWord = MutableLiveData<String>()
    val currentScrambledWord: LiveData<String> get() = _currentScrambledWord

    private var wordsList: MutableList<String> = mutableListOf()
    private lateinit var currentWord: String

    init {
        Log.d("GameFragment", "GameViewModel created!")
        getNextWord()
    }

    private fun getNextWord() {
        currentWord = allWordsList.random() // 데이터 클래스 allWordsList에서 단어를 무작위로 가져옴
        val tempWord = currentWord.toCharArray() // 단어를 문자 하나하나씩 배열에 넣음

        while (String(tempWord).equals(currentWord, false)) { // 단어가 원래 단어와 같지 않을 때까지 섞음
            tempWord.shuffle()
        }

        if (wordsList.contains(currentWord)) { // 중복 문제를 막기 위해 현재 단어가 wordList에 있다면 다시 단어 찾아옴
            getNextWord()
        } else { // 처음 본 문제라면
            _currentScrambledWord.value = String(tempWord)
            _currentWordCount.value = (_currentWordCount.value)?.inc()
            wordsList.add(currentWord)
        }
    }

    private fun increaseScore() {
        _score.value = (_score.value)?.plus(SCORE_INCREASE)
    }

    fun nextWord(): Boolean {
        return if (currentWordCount.value!! < MAX_NO_OF_WORDS) {
            getNextWord()
            true
        } else {
            false
        }
    }

    fun isUserWordCorrect(playerWord: String): Boolean {
        if (playerWord.equals(currentWord, true)) {
            increaseScore()
            return true
        }
        return false
    }

    fun reinitializeData() {
        _score.value = 0
        _currentWordCount.value = 0
        wordsList.clear()
        getNextWord()
    }
}