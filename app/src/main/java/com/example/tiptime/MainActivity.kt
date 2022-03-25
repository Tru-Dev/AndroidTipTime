package com.example.tiptime

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tiptime.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {
    /**
     * Holds the bindings for the main activity
     */
    private lateinit var binding: ActivityMainBinding

    /**
     * Init logic for MainActivity
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Initialize binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Calculate the tip whenever the button is clicked
        binding.calculateButton.setOnClickListener{ calculateTip() }
    }

    /**
     * Calculates the tip and displays the result
     */
    private fun calculateTip() {
        // Get the cost as a Double?
        val cost = binding.costOfService.text.toString().toDoubleOrNull()
        // Default behavior for null
        if (cost == null) {
            binding.tipResult.text = ""
            return
        }
        // Get the tip percentage
        val tipPercent = when (binding.tipOptions.checkedRadioButtonId) {
            R.id.tip_20 -> 0.20
            R.id.tip_18 -> 0.18
            else -> 0.15
        }
        // Calculate the tip
        var tip = tipPercent * cost
        // Round up?
        if (binding.roundUpSwitch.isChecked) {
            tip = kotlin.math.ceil(tip)
        }
        // Format and display
        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        binding.tipResult.text = getString(R.string.tip_amount, formattedTip)
    }
}