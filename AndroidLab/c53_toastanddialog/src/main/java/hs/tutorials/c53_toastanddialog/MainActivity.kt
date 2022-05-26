package hs.tutorials.c53_toastanddialog

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.DialogInterface
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.TimePicker
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val alertDialog = findViewById<Button>(R.id.alertDialogButton)
        val listDialogButton = findViewById<Button>(R.id.listDialogButton)
        val dateDialogButton = findViewById<Button>(R.id.dateDialogButton)
        val timeDialogButton = findViewById<Button>(R.id.timeDialogButton)

        alertDialog.setOnClickListener {
            AlertDialog.Builder(this).run{
                setTitle("다이알로그 테스트")
                setIcon(android.R.drawable.ic_dialog_alert)
                setMessage("종료할까요?")
                setPositiveButton("OK", null)
                setNegativeButton("Cancel", null)
                show()
            }
        }

        listDialogButton.setOnClickListener {
            val items = arrayOf<String>("사과", "복숭아", "수박")
            AlertDialog.Builder(this).run{
                setTitle("리스트 다이알로그 테스트")
                setItems(items, object : DialogInterface.OnClickListener{
                    override fun onClick(dialog: DialogInterface?, which: Int) {
                        Toast.makeText(this@MainActivity, "선택 항목:${items[which]}", Toast.LENGTH_SHORT).show()
                    }
                })
                setPositiveButton("ok", null)
                show()
            }
        }

        dateDialogButton.setOnClickListener {
            DatePickerDialog(this, object: DatePickerDialog.OnDateSetListener{
                override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
                    Toast.makeText(this@MainActivity, "선택 날짜:$year, $month, $dayOfMonth", Toast.LENGTH_SHORT).show()
                }
            }, 2022, 5, 26).show()
        }

        timeDialogButton.setOnClickListener {
            TimePickerDialog(this, object: TimePickerDialog.OnTimeSetListener{
                override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
                    Toast.makeText(this@MainActivity, "선택 시간:$hourOfDay, $minute", Toast.LENGTH_SHORT).show()
                }
            }, 15, 0, true).show()
        }
    }
}