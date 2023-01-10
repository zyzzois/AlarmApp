package com.octaneocatane.alarmapp

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import com.octaneocatane.alarmapp.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(s: Bundle?) {
        super.onCreate(s)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonSetAlarm.setOnClickListener {
            val picker =
                MaterialTimePicker.Builder()
                    .setTimeFormat(TimeFormat.CLOCK_24H)
                    .setHour(12)
                    .setMinute(10)
                    .setTitleText("Select Appointment time")
                    .build()

            picker.addOnPositiveButtonClickListener {
                val calendar = Calendar.getInstance() // текущее время
                calendar.set(Calendar.SECOND, 0)
                calendar.set(Calendar.MILLISECOND, 0)
                calendar.set(Calendar.MINUTE, picker.minute)
                calendar.set(Calendar.HOUR_OF_DAY, picker.hour)

                val alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager
                /*val alarmClockInfo =
                    AlarmManager.AlarmClockInfo(calendar.timeInMillis, getAlarmInfoPendingIntent())*/

                val intent = AlarmReceiver.newIntent(this)
                val pendingIntent = PendingIntent.getBroadcast(this, 11, intent, 0)

                alarmManager.setExactAndAllowWhileIdle(
                    AlarmManager.RTC_WAKEUP,
                    calendar.timeInMillis,
                    pendingIntent
                )

                Toast.makeText(this, "Будильник установлен", Toast.LENGTH_SHORT).show()
            }
            picker.show(supportFragmentManager, "tag_picker")
        }

    }
/*
    private fun getAlarmInfoPendingIntent(): PendingIntent {
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
        return PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
    }
    private fun getAlarmActionPendingIntent(): PendingIntent {
        val intent = Intent(this, AlarmActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
        return PendingIntent.getActivity(this, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT)
    }*/
}
