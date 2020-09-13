package com.example.couple_app

import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import com.skyfishjy.library.RippleBackground
import java.util.*

class Keep_the_Love : AppCompatActivity() {
    // sharedpreferences
    private val sharedPrefFile = "kotlinsharedpreference"

    // 데이트피커
    val c = Calendar.getInstance()  // 현재 시스템의 날짜를 얻어옴
    val year = c.get(Calendar.YEAR)     // 년도 얻어옴
    val month = c.get(Calendar.MONTH)    // 월 얻어옴 0부터 시작함 + 1
    val day = c.get(Calendar.DAY_OF_MONTH)      // 날짜 얻어옴

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.keep_the_love)

        var sharedpreferenceData = SharedPreference_data

        // 물결 잔상 사용
        val rippleBackground = findViewById(R.id.content) as RippleBackground
        rippleBackground.startRippleAnimation()

        // 공유프리프랜스
        val sharedPreferences: SharedPreferences = this.getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)
        val editor:SharedPreferences.Editor =  sharedPreferences.edit()
        //sharedpreferenceData.ClearData(this)


        // 하트 이미지 클릭 시
        var imageView = findViewById(R.id.centerImage) as ImageView
        imageView.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                // year로 저장된 데이터를 가져와서 text에 저장
                val text = sharedPreferences.getInt("year", -1)

                // 현재 값을 확인하기 위해서 넣었음
                val data_y = sharedPreferences.getInt("year",0)
                val data_m = sharedPreferences.getInt("month",0)
                val data_d = sharedPreferences.getInt("day",0)
                Log.d("===year===month===day", "$data_y - $data_m - $data_d")

                // 만약 저장된 데이터를 가져와 저장한 text가 default 값이라면 if문 실행
                if(text.equals(-1)) {
                    val dpd = DatePickerDialog(
                        this@Keep_the_Love, R.style.DialogTheme, // style.xml에서 데이트피커 색상 변경했음
                        DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                            Toast.makeText(this@Keep_the_Love, "$year - ${monthOfYear + 1} - $dayOfMonth", Toast.LENGTH_SHORT).show()


                            sharedpreferenceData.SaveData(this@Keep_the_Love, year, monthOfYear, dayOfMonth)
                            println(sharedpreferenceData.LoadData(this@Keep_the_Love))

                            val intent = Intent(this@Keep_the_Love, Home::class.java)
                            startActivity(intent)
                        }, year, month, day)
                    dpd.getDatePicker().setMaxDate(Date().time) // 현재 날짜를 가져와 최대값으로 설정했음
                    dpd.show()
                }
                // 만약 저장된 데이터를 가져와 저장한 text가 default 값이 아니라면 else문 실행
                else {
                    val intent = Intent(this@Keep_the_Love, Home::class.java)
                    startActivity(intent)
                }

            }
        })
    }
}
