package com.octaneocatane.data.repository

import androidx.lifecycle.LiveData
import com.octaneocatane.data.database.AlarmListDao
import com.octaneocatane.domain.entity.AlarmEntity
import com.octaneocatane.domain.repository.AlarmRepository
import javax.inject.Inject

class AlarmRepositoryImpl @Inject constructor(
    private val alarmListDao: AlarmListDao
): AlarmRepository {

    override fun getAlarmListUseCase(): LiveData<List<AlarmEntity>> {
        TODO("Not yet implemented")
    }

    override fun addAlarmUseCase(alarm: AlarmEntity) {
        TODO("Not yet implemented")
    }

    override fun deleteAlarmUseCase(alarm: AlarmEntity) {
        TODO("Not yet implemented")
    }

    override fun editAlarmUseCase(alarm: AlarmEntity) {
        TODO("Not yet implemented")
    }

    override fun getAlarmUseCase(alarmId: Int): AlarmEntity {
        TODO("Not yet implemented")
    }


}