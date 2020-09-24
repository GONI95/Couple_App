package com.example.couple_app

import android.app.DatePickerDialog
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import kotlinx.android.synthetic.main.home.*
import java.util.*


class Home : AppCompatActivity(), SharedPreferences.OnSharedPreferenceChangeListener {
    var sharedpreferenceData = SharedPreference_data

    //lateinit var constraintLayout : ConstraintLayout  // 늦은 초기화 , 색변경

    val c = Calendar.getInstance()
    val year = c.get(Calendar.YEAR)
    val month = c.get(Calendar.MONTH)
    val day = c.get(Calendar.DAY_OF_MONTH)

    //private var result = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home)

        //constraintLayout = findViewById(R.id.hi) as ConstraintLayout

        var illustration = findViewById(R.id.illustration) as ImageView

        var calculate_day = findViewById(R.id.Calculate_days) as TextView
        var d_day_100 = findViewById(R.id.Dday_100) as TextView
        var d_day_200 = findViewById(R.id.Dday_200) as TextView
        // 만난지 몇일이 되었는지
        calculate_day.setText(resources.getString(R.string.we) + sharedpreferenceData.calculate(this).toString())
        //백일까지 몇일이 남았는지
        d_day_100.setText("100일 D-day :" + sharedpreferenceData.dday_100(this).toString())
        //이백일까지 몇일이 남았는지
        d_day_200.setText("200일 D-day :" + sharedpreferenceData.dday_200(this).toString())


    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.item, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.calendar -> {
                calendar()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun calendar() {
        val dpd = DatePickerDialog(
            this, R.style.DialogTheme, // style.xml에서 데이트피커 색상 변경했음
            DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                // Display Selected date in Toast
                Toast.makeText(
                    this,
                    """$dayOfMonth - ${monthOfYear + 1} - $year""",
                    Toast.LENGTH_LONG
                ).show()

                sharedpreferenceData.SaveData(this, year, monthOfYear, dayOfMonth)
                println(sharedpreferenceData.LoadData(this))

                onSharedPreferenceChanged(sharedPreferences = null, year.toString())

            }, year, month, day
        )
        dpd.getDatePicker().setMaxDate(Date().time)
        dpd.show()
    }

    // 메모리 문제로 이렇게 해주어야한다고함
    @Override
    override fun onDestroy() {
        super.onDestroy()
        sharedpreferenceData.unregisterPref(this, this)
    }

    @Override
    override fun onStart() {
        super.onStart()
        sharedpreferenceData.registerPref(this, this)
    }

    // 공유리프런스 값 변경되면 리스너가 확인함
    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {
        if (key.equals("${SharedPreference_data.year_key}")) {
            //result = sharedpreferenceData.LoadData(this)  // 값이 제대로 넣어졌는지 확인하려고 넣음 지우지마!
            //println("$result")
            //constraintLayout.setBackgroundColor(applicationContext.resources.getColor(R.color.sub_color)) 색 변경
        }
        // 이 둘은 layout 아이디를 그대로 가져와 쓰는 것임
        Calculate_days.setText(resources.getString(R.string.we) + sharedpreferenceData.calculate(this).toString())
        Dday_100.setText("100일 D-day :" + sharedpreferenceData.dday_100(this).toString())
        Dday_200.setText("200일 D-day :" + sharedpreferenceData.dday_200(this).toString())

    }


}