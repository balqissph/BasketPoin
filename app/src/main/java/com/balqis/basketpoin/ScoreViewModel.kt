package com.balqis.basketpoin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ScoreViewModel : ViewModel() {

    companion object {
        private const val TARGET_SCORE = 25
    }

    private val _scoreTeamA = MutableLiveData(0)
    val scoreTeamA: LiveData<Int> get() = _scoreTeamA

    private val _scoreTeamB = MutableLiveData(0)
    val scoreTeamB: LiveData<Int> get() = _scoreTeamB

    private val _winner = MutableLiveData<String?>()
    val winner: LiveData<String?> get() = _winner

    private val _showWinnerDialog = MutableLiveData(false)
    val showWinnerDialog: LiveData<Boolean> get() = _showWinnerDialog

    fun incrementSkorA(poin: Int) {
        if (_winner.value == null) {
            _scoreTeamA.value = (_scoreTeamA.value ?: 0) + poin
            checkWinner()
        }
    }

    fun incrementSkorB(poin: Int) {
        if (_winner.value == null) {
            _scoreTeamB.value = (_scoreTeamB.value ?: 0) + poin
            checkWinner()
        }
    }

    private fun checkWinner() {
        when {
            (_scoreTeamA.value ?: 0) >= TARGET_SCORE -> {
                _winner.value = "Team A Menang!"
                _showWinnerDialog.value = true
            }
            (_scoreTeamB.value ?: 0) >= TARGET_SCORE -> {
                _winner.value = "Team B Menang!"
                _showWinnerDialog.value = true
            }
        }
    }

    fun resetSkor() {
        _scoreTeamA.value = 0
        _scoreTeamB.value = 0
        _winner.value = null
        _showWinnerDialog.value = false
    }
}
