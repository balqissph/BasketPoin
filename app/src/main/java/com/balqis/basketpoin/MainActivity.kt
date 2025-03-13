package com.balqis.basketpoin

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.balqis.basketpoin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: ScoreViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        binding.buttonA1.setOnClickListener { viewModel.incrementSkorA(1) }
        binding.buttonA2.setOnClickListener { viewModel.incrementSkorA(2) }
        binding.buttonA3.setOnClickListener { viewModel.incrementSkorA(3) }

        binding.buttonB1.setOnClickListener { viewModel.incrementSkorB(1) }
        binding.buttonB2.setOnClickListener { viewModel.incrementSkorB(2) }
        binding.buttonB3.setOnClickListener { viewModel.incrementSkorB(3) }

        binding.buttonReset.setOnClickListener { viewModel.resetSkor() }

        viewModel.winner.observe(this) { winner ->
            if (winner != null) {
                showWinnerToast(winner)
                showWinnerPopup(winner)
            }
        }
    }

    private fun showWinnerToast(winnerText: String) {
        Toast.makeText(this, winnerText, Toast.LENGTH_LONG).show()
    }

    private fun showWinnerPopup(winnerMessage: String) {
        AlertDialog.Builder(this)
            .setTitle("Selamat!")
            .setMessage(winnerMessage)
            .setPositiveButton("OK") { dialog, _ ->
                viewModel.resetSkor()
                dialog.dismiss()
            }
            .show()
    }
}
