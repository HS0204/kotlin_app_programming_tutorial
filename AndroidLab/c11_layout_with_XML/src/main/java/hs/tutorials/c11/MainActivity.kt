package hs.tutorials.c11

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        val name = TextView(this).apply{
//            typeface = Typeface.DEFAULT_BOLD
//            text = "나의 첫번째 안드로이드 앱 UI MainActivity ver."
//        }
//
//        val image = ImageView(this).also {
//            it.setImageDrawable(ContextCompat.getDrawable(
//                this, R.drawable.android_os
//            ))
//        }
//
//        val title = TextView(this).apply {
//            typeface = Typeface.DEFAULT_BOLD
//            text = "JetPack과 Kotlin을 활용하여 안드로이드 만들기"
//       }
//
//        val layout = LinearLayout(this).apply{
//            orientation = LinearLayout.VERTICAL
//            gravity = Gravity.CENTER
//            addView(name, WRAP_CONTENT, WRAP_CONTENT)
//            addView(image, WRAP_CONTENT, WRAP_CONTENT)
//            addView(title, WRAP_CONTENT, WRAP_CONTENT)
//        }
//
//        setContentView(layout)

        setContentView(R.layout.activity_main)
    }
}