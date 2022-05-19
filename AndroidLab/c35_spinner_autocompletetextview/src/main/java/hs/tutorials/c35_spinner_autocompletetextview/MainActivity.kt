package hs.tutorials.c35_spinner_autocompletetextview

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /* spinner */
        val spinner = findViewById<Spinner>(R.id.spinner)
        val data = resources.getStringArray(R.array.language)
        val adapter : ArrayAdapter<String> = ArrayAdapter(
            this,
            android.R.layout.simple_dropdown_item_1line, data
        )

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spinner.adapter = adapter

        /* autoCompleteTextView */
        val autoCompleteTextView = findViewById<AutoCompleteTextView>(R.id.auto)
        val autoData = arrayOf<String>("apply", "apple", "below")
        val autoAdapter = ArrayAdapter<String>(
            this,
            android.R.layout.simple_dropdown_item_1line, autoData
        )

        autoCompleteTextView.setAdapter(autoAdapter)
    }
}