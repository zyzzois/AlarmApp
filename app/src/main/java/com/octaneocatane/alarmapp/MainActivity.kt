package com.octaneocatane.alarmapp

import android.app.AlarmManager
import android.app.AlarmManager.AlarmClockInfo
import android.app.PendingIntent
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import com.octaneocatane.alarmapp.databinding.ActivityMainBinding
import java.util.*


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var requestCode = 0

    override fun onCreate(s: Bundle?) {
        super.onCreate(s)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        checkOverlayPermission()

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

                val alarmClockInfo = AlarmClockInfo(calendar.timeInMillis, getAlarmInfoPendingIntent(requestCode++))
                alarmManager.setAlarmClock(alarmClockInfo, getAlarmActionPendingIntent(requestCode++))

                Toast.makeText(this, "Будильник установлен", Toast.LENGTH_SHORT).show()
            }
            picker.show(supportFragmentManager, "tag_picker")
        }
    }

    private fun checkOverlayPermission() {
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.parse(
            "package:$packageName"))
        startActivityForResult(intent, 0)
    }

    private fun getAlarmInfoPendingIntent(requestCode: Int): PendingIntent {
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
        return PendingIntent.getActivity(this, requestCode, intent, PendingIntent.FLAG_UPDATE_CURRENT)
    }
    private fun getAlarmActionPendingIntent(requestCode: Int): PendingIntent {
        val intent = Intent(this, AlarmActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
        return PendingIntent.getActivity(this, requestCode, intent, PendingIntent.FLAG_UPDATE_CURRENT)
    }
}
