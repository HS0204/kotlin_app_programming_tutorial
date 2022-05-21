package hs.tutorials.minagecalc

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private var selectedDate : TextView? = null // private 때문에 다른 클래스에서 사용할 수 없음
    private var ageInMinutes : TextView? = null
    private var dateLayout : LinearLayout? = null
    private var ageInMinutesLayout : LinearLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnDatePicker : Button = findViewById<Button>(R.id.btnDatePicker)
        selectedDate = findViewById(R.id.selectedDate)
        ageInMinutes = findViewById(R.id.ageInMinutes)
        dateLayout = findViewById(R.id.dateLayout)
        ageInMinutesLayout = findViewById(R.id.ageInMinutesLayout)

        btnDatePicker.setOnClickListener {
            clickDatePicker()
        }
    }

    private fun clickDatePicker(){

        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)
        val calendar = DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener{ _, selectedYear, selectedMonth, selectedDayOfMonth ->
                val date = "${selectedYear}년 ${selectedMonth+1}월 ${selectedDayOfMonth}일"
                selectedDate?.text = date

                val sdf = SimpleDateFormat("yyyy년 MM월 dd일", Locale.KOREA) // 날짜에 사용할 포맷 지정

                val theDate = sdf.parse(date)
                theDate?.let{
                    // 날짜가 선택된 경우에만 실행되도록 --> null safety
                    val selectedDateInMinutes = theDate.time / 60000
                    val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                    currentDate?.let{
                        val currentDateInMinutes = currentDate.time / 60000
                        val differenceInMinutes = currentDateInMinutes - selectedDateInMinutes

                        ageInMinutes?.text = differenceInMinutes.toString()

                        dateLayout?.visibility = View.VISIBLE
                        ageInMinutesLayout?.visibility = View.VISIBLE
                    }
                }

            },
            year,
            month,
            day
            )

        calendar.datePicker.maxDate = System.currentTimeMillis() - 86400000
        calendar.show()

    }
}