package com.octaneocatane.data.mapper

import com.octaneocatane.data.database.AlarmItemDbModel
import com.octaneocatane.domain.entity.AlarmEntity
import javax.inject.Inject

class AlarmListMapper @Inject constructor() {
    fun mapEntityToDbModel(alarmEntity: AlarmEntity) = AlarmItemDbModel(
        id = alarmEntity.id,
        time = alarmEntity.time,
        description = alarmEntity.description
    )

    fun mapDbModelToEntity(alarmItemDbModel: AlarmItemDbModel) = AlarmEntity(
        id = alarmItemDbModel.id,
        time = alarmItemDbModel.time,
        description = alarmItemDbModel.description
    )

    fun mapListDbModelToList(list: List<AlarmItemDbModel>) = list.map {
        mapDbModelToEntity(it)
    }
}