package com.octaneocatane.domain.usecase

import androidx.lifecycle.LiveData
import com.octaneocatane.domain.entity.AlarmEntity
import com.octaneocatane.domain.repository.AlarmRepository
import javax.inject.Inject

class GetAlarmListUseCase @Inject constructor(private val repository: AlarmRepository) {
    operator fun invoke(): LiveData<List<AlarmEntity>> {
        return repository.getAlarmListUseCase()
    }
}