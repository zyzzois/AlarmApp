package com.octaneocatane.alarmapp

import android.media.Ringtone
import android.media.RingtoneManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.octaneocatane.alarmapp.databinding.ActivityAlarmBinding

class AlarmActivity : AppCompatActivity() {
    private lateinit var ringtone: Ringtone
    private val binding by lazy {
        ActivityAlarmBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val notificationUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM)
        ringtone = RingtoneManager.getRingtone(this, notificationUri)
        ringtone.play()
    }

    override fun onDestroy() {
        if (ringtone.isPlaying) {
            ringtone.stop()
        }
        super.onDestroy()
    }
}