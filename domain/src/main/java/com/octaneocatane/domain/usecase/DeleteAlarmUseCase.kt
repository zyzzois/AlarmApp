package com.octaneocatane.domain.usecase

import com.octaneocatane.domain.entity.AlarmEntity
import com.octaneocatane.domain.repository.AlarmRepository
import javax.inject.Inject

class DeleteAlarmUseCase @Inject constructor(private val repository: AlarmRepository) {
    operator fun invoke(alarm: AlarmEntity) {
        repository.deleteAlarmUseCase(alarm)
    }
}