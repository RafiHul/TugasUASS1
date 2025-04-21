package com.rafih.tugasuass1

import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.rafih.tugasuass1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var radioButtonPick : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar2)

        binding.buttonDefault.setOnClickListener{
            binding.editTextInputList.setText(getRandomList().joinToString(","))
        }

        binding.radioGroup.setOnCheckedChangeListener{ group, checkedId ->
            radioButtonPick = findViewById<RadioButton>(checkedId).text.toString()
        }

        binding.buttonSort.setOnClickListener{
            val inpt = binding.editTextInputList.text.toString()
            var listData = listOf<Int>()

            if (inpt.isNotEmpty()){
                try {
                    listData = inpt.split(",").map { it.toInt() }
                } catch(e: NumberFormatException) {
                    Toast.makeText(this@MainActivity, "Masukkan Angka Yang Benar", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                val sortingResult = when(radioButtonPick){
                    "Bubble Sort" -> {
                        elapsedTime { SortingMethod.bubbleSort(listData.toMutableList()).toString() }
                    }
                    "Selection Sort" -> {
                        elapsedTime { SortingMethod.selectionSort(listData.toMutableList()).toString() }
                    }
                    "Insertion Sort" -> {
                        elapsedTime { SortingMethod.insertionSort(listData.toMutableList()).toString() }
                    }
                    else -> {
                        Toast.makeText(this@MainActivity, "Pilih Algoritma Sorting Terlebih dahulu", Toast.LENGTH_SHORT).show()
                        return@setOnClickListener
                    }
                }

                binding.apply {
                    cardViewText.visibility = View.VISIBLE
                    textViewHasil.visibility = View.VISIBLE
                    textViewHasil.text = sortingResult.first
                }
                Toast.makeText(this@MainActivity, "Sorting membutuhkan waktu ${sortingResult.second / 1_000_000} ms", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun getRandomList(): MutableList<Int> {
        val list = mutableListOf<Int>()
        for(i in 0..200){
           list.add((1..150).random())
        }
        return list
    }

    fun elapsedTime(action: () -> String): Pair<String, Long> {
        val startTime = System.nanoTime()
        val hasil = action()
        val endTime = System.nanoTime()
        val elapsed = endTime - startTime
        return Pair(hasil, elapsed)
    }
}
