package com.example.calculadora

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.calculadora.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.buttonCalculate.setOnClickListener(this)


    }

    override fun onClick(view: View) {
        if (view.id == R.id.button_calculate) {
            calculator()
        }
    }

    private fun isValid(): Boolean {
        return (
                        binding.editDistance.text.toString() != "" &&
                        binding.editPrice.text.toString() != "" &&
                        binding.editAutonomia.text.toString() != "" &&
                        binding.editPedagio.text.toString() != "" &&
                        binding.editAutonomia.text.toString().toFloat() != 0f
                )

    }

    @SuppressLint("SetTextI18n")
    private fun calculator() {
        if (isValid()) {
            val distance = binding.editDistance.text.toString().toFloat()
            val price = binding.editPrice.text.toString().toFloat()
            val autonomy = binding.editAutonomia.text.toString().toFloat()
            val pedagio2 = binding.editPedagio.text.toString().toFloat()
            val totalValue = (distance * price) / autonomy + pedagio2

            binding.textGastoTotal.text = "R$ ${"%2.f".format(totalValue)}"

        } else {
            Toast.makeText(this, getString(R.string.preenchaOscampos), Toast.LENGTH_SHORT).show()

        }
    }


}
