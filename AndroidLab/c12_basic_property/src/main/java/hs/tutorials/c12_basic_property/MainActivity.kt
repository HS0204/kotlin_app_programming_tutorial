package hs.tutorials.c12_basic_property

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    lateinit var visible_btn: Button
    lateinit var invisible_btn: Button
    lateinit var logo: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        visible_btn = findViewById(R.id.visible_btn)
        invisible_btn = findViewById(R.id.invisible_btn)
        logo = findViewById(R.id.logo)

        visible_btn.setOnClickListener {
            logo.visibility = View.VISIBLE
        }

        invisible_btn.setOnClickListener {
            logo.visibility = View.INVISIBLE
        }

    }
}