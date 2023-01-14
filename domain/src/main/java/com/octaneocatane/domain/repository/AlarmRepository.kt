package com.octaneocatane.domain.repository

import androidx.lifecycle.LiveData
import com.octaneocatane.domain.entity.AlarmEntity

interface AlarmRepository {
    fun getAlarmListUseCase(): LiveData<List<AlarmEntity>>
    fun addAlarmUseCase(alarm: AlarmEntity)
    fun deleteAlarmUseCase(alarm: AlarmEntity)
    fun editAlarmUseCase(alarm: AlarmEntity)
    fun getAlarmUseCase(alarmId: Int): AlarmEntity
}