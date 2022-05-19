package hs.tutorials.c28_valuesresource

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var textView = findViewById<TextView>(R.id.textView2)
        textView.text = getString(R.string.text_data2)
        textView.setTextColor(ResourcesCompat.getColor(resources, R.color.text_color, null))
        textView.setTextSize(resources.getDimension(R.dimen.text_size))

    }
}