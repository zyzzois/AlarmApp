package com.octaneocatane.domain.usecase

import com.octaneocatane.domain.entity.AlarmEntity
import com.octaneocatane.domain.repository.AlarmRepository
import javax.inject.Inject

class GetAlarmUseCase @Inject constructor(private val repository: AlarmRepository) {
    operator fun invoke(alarmId: Int): AlarmEntity {
        return repository.getAlarm(alarmId)
    }
}