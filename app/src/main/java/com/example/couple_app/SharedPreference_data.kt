package com.example.couple_app

import android.content.Context
import android.content.SharedPreferences
import android.content.SharedPreferences.OnSharedPreferenceChangeListener
import android.util.Log
import java.util.*

object SharedPreference_data {
    // sharedpreferences
    private val sharedPrefFile = "kotlinsharedpreference"
    val year_key = "year"
    fun LoadYear(context: Context?) : Int{
        // 현재 값을 확인하기 위해서 넣었음
        val sharedPreferences: SharedPreferences = context!!.getSharedPreferences(
            sharedPrefFile,
            Context.MODE_PRIVATE
        )
        val data_y = sharedPreferences.getInt("$year_key", 0)

        return data_y
    }
    fun LoadMonth(context: Context?) : Int{
        // 현재 값을 확인하기 위해서 넣었음
        val sharedPreferences: SharedPreferences = context!!.getSharedPreferences(
            sharedPrefFile,
            Context.MODE_PRIVATE
        )
        val data_m = sharedPreferences.getInt("month", 0)

        return data_m
    }
    fun LoadDay(context: Context?) : Int{
        // 현재 값을 확인하기 위해서 넣었음
        val sharedPreferences: SharedPreferences = context!!.getSharedPreferences(
            sharedPrefFile,
            Context.MODE_PRIVATE
        )
        val data_d = sharedPreferences.getInt("day", 0)

        return data_d
    }
    fun SaveData(context: Context?, year: Int, monthOfYear: Int, dayOfMonth: Int) {
        val sharedPreferences: SharedPreferences = context!!.getSharedPreferences(
            sharedPrefFile,
            Context.MODE_PRIVATE
        )
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putInt("$year_key", year)
        editor.putInt("month", monthOfYear + 1)
        editor.putInt("day", dayOfMonth)
        editor.apply()
        editor.commit()
    }
    fun ClearData(context: Context?) {
        val sharedPreferences: SharedPreferences = context!!.getSharedPreferences(
            sharedPrefFile,
            Context.MODE_PRIVATE
        )
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.clear();
        editor.commit();
    }
    fun LoadData(context: Context?) : String{
        // 현재 값을 확인하기 위해서 넣었음
        val sharedPreferences: SharedPreferences = context!!.getSharedPreferences(
            sharedPrefFile,
            Context.MODE_PRIVATE
        )
        val data_y = sharedPreferences.getInt("$year_key", 0)
        val data_m = sharedPreferences.getInt("month", 0)
        val data_d = sharedPreferences.getInt("day", 0)
        return "${data_y.toString()}-${data_m.toString()}-${data_d.toString()}"
    }
    fun registerPref(context: Context, listener: OnSharedPreferenceChangeListener?) {
        val sharedPreferences: SharedPreferences = context!!.getSharedPreferences(
            sharedPrefFile,
            Context.MODE_PRIVATE
        )
        sharedPreferences.registerOnSharedPreferenceChangeListener(listener)
    }

    fun unregisterPref(context: Context, listener: OnSharedPreferenceChangeListener?) {
        val sharedPreferences: SharedPreferences = context!!.getSharedPreferences(
            sharedPrefFile,
            Context.MODE_PRIVATE
        )
        sharedPreferences.unregisterOnSharedPreferenceChangeListener(listener)
    }

    fun calculate(context: Context?) : Long {
        val c = Calendar.getInstance()
        val c1 = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        c.set(year, month+1, day)
        Log.d("c", "${c.toString()}")
        c1.set(LoadYear(context), LoadMonth(context), LoadDay(context))
        Log.d("c1", "${c1.toString()}")

        var millis1 = c.timeInMillis
        Log.d("millis1", "${millis1.toString()}")
        var millis2 = c1.timeInMillis
        Log.d("millis2", "${millis2.toString()}")

        val diff = millis1 - millis2
        val diffDays = diff / (24 * 60 * 60 * 1000)

        return diffDays+1    // 빼기때문에 만난지 하루만에 넣으면 0이 나옴
    }
}
