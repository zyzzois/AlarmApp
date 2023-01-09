package com.octaneocatane.alarmapp

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import com.octaneocatane.alarmapp.databinding.ActivityMainBinding
import java.util.Calendar

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(s: Bundle?) {
        super.onCreate(s)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonSetAlarm.setOnClickListener {
            val alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager
            val picker =
                MaterialTimePicker.Builder()
                    .setTimeFormat(TimeFormat.CLOCK_12H)
                    .setHour(12)
                    .setMinute(10)
                    .setTitleText("Select Appointment time")
                    .build()

            val calendar = Calendar.getInstance()
            picker.addOnPositiveButtonClickListener {
                calendar.set(Calendar.SECOND, 0)
                calendar.set(Calendar.MILLISECOND, 0)
                calendar.set(Calendar.MINUTE, picker.minute)
                calendar.set(Calendar.HOUR_OF_DAY, picker.hour)

//                alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager
//                val alarmClockInfo =
//                    AlarmManager.AlarmClockInfo(calendar.timeInMillis, getAlarmInfoPendingIntent())
//                alarmManager.setAlarmClock(alarmClockInfo, getAlarmActionPendingIntent())
//                Toast.makeText(this, "Будильник установлен на ${sdf.format(calendar.time)}", Toast.LENGTH_SHORT).show()
            }
            picker.show(supportFragmentManager, "tag_picker")

            val intent = AlarmReceiver.newIntent(this)
            val pendingIntent = PendingIntent.getBroadcast(this, 100, intent, 0)
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, pendingIntent)
        }

    }


}